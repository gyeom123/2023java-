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
	private Member loginedMember;

	public MemberController(Scanner sc) {
		// 외부(App)에서 만든 리모콘을 넘겨받고 조정할 수 있게
		this.members = new ArrayList<>();
		this.sc = sc;
		this.lastMemberId = 3;
		this.loginedMember = null;
	}

	@Override
	public void doAction(String cmd, String methodName) {

		switch (methodName) {
		case "join":
			dojoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "profile":
			showProfile();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}

	private void doLogin() {

		if (isLoqined()) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}

		System.out.printf("로그인 아이디  : ");
		String LoginID = sc.nextLine();
		System.out.printf("로그인 비밀번호  : ");
		String LoginPasswordheck = sc.nextLine();
		
		Member member = getMembeByLoginId(LoginID);
		
		if (member == null) {
			System.err.println("존재하지 않는 아이디 입니다.");
			return;
		}
		if (member.LoginPassword.equals(LoginPasswordheck) == false) {
			System.err.println("비밀번호를 확인해주세요");
			return;
		}

		this.loginedMember = member;

		System.err.printf("%s님 환영합니다.\n", member.name);
	}

	private void doLogout() {
		if (isLoqined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		this.loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}

	private void showProfile() {
		if (isLoqined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		System.out.println("==내 정보==");
		System.out.printf("로그인 아이디 : %s\n", this.loginedMember.LoginID);
		System.out.printf("이름 : %s\n", this.loginedMember.name);
	}

	private void dojoin() {
		
		if (isLoqined()) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
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

	private Member getMembeByLoginId(String loginID) {
		// 로그인 아이디 확인 후 해당 아이디가 맞을 경우 해당 아이디가 있는 객체를 연결한다
		for (Member member : members) {
			if (member.LoginID.equals(loginID)) {
				return member;
			}
		}
		return null;
	}

	private boolean LoginIdDupchk(String LoginID) {
		// 중복된 아이디가 있는지 검사하는 함수

		Member member = getMembeByLoginId(LoginID);

		if (member != null) {
			return false;
		}
		return true;
	}

	private boolean isLoqined() {
		//loginedMember에 null이 아니라면 로그인을 한 경우
		return loginedMember != null;
	}

	public void makeTestData() {
		System.out.println("로그인 테스트 데이터를 생성합니다.");
		members.add(new Member(1, Util.gettine(), "test1", "test1", "테스트1"));
		members.add(new Member(2, Util.gettine(), "test2", "test2", "테스트2"));
		members.add(new Member(3, Util.gettine(), "test3", "test3", "테스트3"));

	}

}
