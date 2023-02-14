import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.printf("명령어를 입력하세요 : "); //줄바꿈을 하지않고 한줄에 입력을 받기 위해 printf를 쓴다
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break; // 'cmd'변수안에 있는 문장이 "exit"경우 가장 가까운 반복문을 탈출한다.
			}

		}

		System.out.println("==프로그램  종류==");

		sc.close();
	}

}
