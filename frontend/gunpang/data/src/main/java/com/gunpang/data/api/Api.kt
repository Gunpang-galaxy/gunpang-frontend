package com.gunpang.data.api

import android.content.res.Resources
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.gunpang.data.DataApplication
import com.gunpang.data.repository.AuthRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

interface Api {
    companion object {
        private const val BASE_URL = "http://k9a207.p.ssafy.io:8080/api/"
        private const val LOGIN_URL = "${BASE_URL}users"
        private const val TOKEN_RECREATE_URL = "${LOGIN_URL}/jwt/recreate"
        private const val TIMEOUT_LIMIT = 60L // api 연결 시간 제한

        class GsonDateFormatAdapter : JsonSerializer<LocalDateTime?>,
            JsonDeserializer<LocalDateTime?> {
            @Synchronized
            override fun serialize(localDateTime: LocalDateTime?, type: Type?, jsonSerializationContext: JsonSerializationContext?): JsonElement {
                return JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(localDateTime))
            }
            @Synchronized
            override fun deserialize(jsonElement: JsonElement, type: Type?, jsonDeserializationContext: JsonDeserializationContext?): LocalDateTime {
                return LocalDateTime.parse(jsonElement.asString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            }
        }
        fun getInstance(): Retrofit {
            val gson = GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, GsonDateFormatAdapter()).create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkHttpClient())
                .build()
        }

        private fun getOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_LIMIT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_LIMIT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_LIMIT, TimeUnit.SECONDS)
                .addInterceptor(AccessTokenInterceptor())
                .addInterceptor(AccessTokenExpireInterceptor())
                .build()
        }

        // accessToken 탑재
        private class AccessTokenInterceptor : Interceptor {
//            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val url = chain.request().url().toString()
                Log.d("api", "url : $url, request : ${chain.request()}")
                // 재발급 경우
                if (TOKEN_RECREATE_URL == url) {
                    val refreshToken = DataApplication.prefs.getString("refreshToken", "")
                    return if ("" != refreshToken) {
                        val token = "Bearer $refreshToken"
                        val newRequest = chain.request().newBuilder()
                            .addHeader("RefreshToken", token)
                            .build()
                        chain.proceed(newRequest)
                    } else {
                        chain.proceed(chain.request())
                    }
                }
                // 앱 로그인 경우
                else if(LOGIN_URL == url) {
                    return chain.proceed(chain.request())
                }
                // 일반 요청 경우
                else {
                    val accessToken = DataApplication.prefs.getString("accessToken", "")
                    return if ("" != accessToken) {
                        val token = "Bearer $accessToken"
                        val newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", token)
                            .build()
                        chain.proceed(newRequest)
                    } else {
                        chain.proceed(chain.request())
                    }
                }
            }
        }

        // 토큰 재발급
        private class AccessTokenExpireInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val response = chain.proceed(chain.request())

                val authRepository = AuthRepository()
                // 토큰 만료
                if (response.code() == 403) {
                    try {
                        authRepository.recreate()
                        // 토큰 재발급 성공 시 기존 요청 재전송
                        val accessToken = DataApplication.prefs.getString("accessToken", "")
                        Log.d("accessToken", accessToken)
                        val token = "Bearer $accessToken"
                        val newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", token)
                            .build()
                        return chain.proceed(newRequest)
                    }
                    // refreshToken 만료
                    catch (e: Resources.NotFoundException){
                        DataApplication.prefs.setString("accessToken", "")
                        DataApplication.prefs.setString("refreshToken", "")
                    }
                }
                return response
            }
        }
    }
}