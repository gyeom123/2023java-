package com.koreaIT.java.BAM.controller;

import com.koreaIT.java.BAM.dto.Member;

public abstract class Controller {
	public abstract void doAction(String cmd, String methodName);
	
	public static Member loginedMember;//사용자의 로그인 정보를 가지고 있는 변수

	// 사용자가 로그인 상태를 확인해주는 함수
	public static boolean isLoqined() {
			// loginedMember에 null이 아니라면 로그인을 한 경우
			return loginedMember != null;
		}
	
	public abstract void makeTestData();

}
