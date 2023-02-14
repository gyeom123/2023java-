import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		while (true) {

			System.out.printf("명령어 ) "); // 줄바꿈을 하지않고 한줄에 입력을 받기 위해 printf를 쓴다
			String cmd = sc.nextLine();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (cmd.equals("exit")) {
				break; // 'cmd'변수안에 있는 문장이 "exit"경우 가장 가까운 반복문을 탈출한다.
			}

			if (cmd.equals("article write")) {

				int id = lastArticleId + 1; //회원이 가지고 있는 고유번호 
				lastArticleId = id;

				System.out.printf("제목 : ");
				String title = sc.nextLine(); //제목의  변수 : title
				System.out.printf("내용 : ");
				String body = sc.nextLine(); //내용의 변수 : body

				System.out.printf("%d번게시글이 생성되었습니다\n", id);

			} else if (cmd.equals("article list")) {
				System.out.println("게시글이 없습니다");

			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}

		}

		System.out.println("==프로그램  종류==");

		sc.close();
	}

}

class Article {
	int id; // 고유번호
	String title; // 게시글 제목
	String detail; // 게시글 내용
	
	

}
