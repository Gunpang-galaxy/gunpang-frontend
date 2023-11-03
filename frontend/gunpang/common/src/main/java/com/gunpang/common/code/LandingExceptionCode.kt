package com.gunpang.common.code

import com.gunpang.common.R

/**
 * 앱에서 발생하는 예외 코드에 따른 화면 처리를 위한 ENUM 클래스
 */
enum class LandingExceptionCode(
    imageId: Int,
    title: String,
    description: String,
    buttonText: String
) {
    NOT_FOUND(imageId = R.drawable.gunpang_exception, title="갤럭시워치를 찾을 수 없어요", description = "기기와 갤럭시워치가 연결되어 있는지 확인해주세요", buttonText = "돌아가기"),
    NOT_INSTALL(imageId = R.drawable.gunpang_exception, title="앱 설치가 되지 않았어요", description = "갤럭시워치에 앱이 설치되어 있는지 확인해주세요", buttonText = "돌아가기"),
    NOT_LOGIN(imageId = R.drawable.gunpang_exception, title="아직 로그인을 하지 않았어요", description = "초기 화면에서 로그인을 완료해주세요", buttonText = "돌아가기"),
    NOT_CONFIG(imageId = R.drawable.gunpang_exception, title="아직 설정을 완료하지 않은 것이 있어요", description = "아바타 부화나 목표설정을 했는지 확인해주세요", buttonText = "돌아가기"),
}