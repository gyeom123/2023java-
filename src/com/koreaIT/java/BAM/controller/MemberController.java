package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;

	public MemberController(Scanner sc) {
		// 외부(App)에서 만든 리모콘을 넘겨받고 조정할 수 있게
		this.members = new ArrayList<>();
		this.sc = sc;
		this.lastMemberId = 0;
	}

	@Override
	public void doAction(String cmd, String methodName) {

		switch (methodName) {
		case "join":
			dojoin();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}

	private void dojoin() {
		int id = lastMemberId + 1; // 회원이 가지고 있는 고유번호
		lastMemberId = id;

		String LoginID = null;
		String LoginPassword = null;
		String LoginPasswordCheck = null;

		while (true) {
			System.out.printf("로그인 아이디  : ");
			LoginID = sc.nextLine();

			if (LoginIdDupchk(LoginID) == false) {
				System.out.printf("%s는 이미 사용중인 아이디입니다.\n", LoginID);
				continue;
			}
			System.out.printf("%s는 사용가능한 아이디입니다.\n", LoginID);
			break;
		}

		while (true) {
			System.out.printf("로그인 비밀번호  : ");
			LoginPassword = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인 : ");
			LoginPasswordCheck = sc.nextLine();

//			if (!(LoginPassword.equals(LoginPasswordCheck))) {
//				System.out.println("비밀번호가 다릅니다");
//				continue;
//			}
//			if ((LoginPassword.equals(LoginPasswordCheck))) {
//				break;
//			}
			if (LoginPassword.equals(LoginPasswordCheck) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		String get_current_date_time = Util.gettine();

		Member member = new Member(id, get_current_date_time, LoginID, LoginPassword, name);
		members.add(member);

		System.out.printf("%s회원님의 회원가입이 환영합니다.\n", name);
	}

	private boolean LoginIdDupchk(String LoginID) {

		for (Member member : members) {
			if (member.LoginID.equals(LoginID)) {
				return false;
			}
		}

		return true;
	}

}
