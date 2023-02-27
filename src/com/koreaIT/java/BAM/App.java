package com.koreaIT.java.BAM;

import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;

public class App {

	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {

			System.out.printf("명령어 ) "); // 줄바꿈을 하지않고 한줄에 입력을 받기 위해 printf를 쓴다
			String cmd = sc.nextLine().trim(); // trim() : 맨앞과 맨뒤에 있는 공백을 제거해주는 함수

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			if (cmd.equals("exit")) {
				break; // 'cmd'변수안에 있는 문장이 "exit"경우 가장 가까운 반복문을 탈출한다.
			}

			String[] cmdBits = cmd.split(" ");// article write , member join

			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}

			String controllerMessage = cmdBits[0];// article
			String methodMessage = cmdBits[1];// write

			Controller controller = null;

			if (controllerMessage.equals("article")) {
				controller = articleController;
			} else if (controllerMessage.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.\n");
				continue;
			}

			String actionName = controllerMessage + "/" + methodMessage;

			switch (actionName) {
			case "article/write":
			case "article/modify":
			case "article/delete":
			case "member/profile":
			case "member/logout":
				if (Controller.isLoqined() == false) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}
				break;
			case "member/join":
			case "member/login":
				if (Controller.isLoqined()) {
					System.out.println("로그아웃 후 이용해주세요");
					continue;
				}
				break;
			}

			controller.doAction(cmd, methodMessage);
		}

		System.out.println("==프로그램  종류==");

		sc.close();
	}
}
