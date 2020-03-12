package controller;

import java.util.Scanner;

import service.CleardelayService;
import service.UserService;

public class UserController {
	UserService service = UserService.getInstance();
	CleardelayService cds = new CleardelayService();
	public void userpwInfo() { // 회원정보 수정 창
		// 비밀번호 수정
		// [2]비밀번호 찾기 질문 수정
		// [3] 연락처
		NextController nextcon = new NextController();

		a : while (true) {
			Scanner s = new Scanner(System.in);
			cds.Clear();
			System.out.println("┌───────────────────────────────────────────────────────────────────────────────┐");
			System.out.print("│ ①비밀번호 수정 ");
			System.out.print("②비밀번호 찾기 질문 수정 ");
			System.out.print("③연락처 수정 ");
			System.out.print("④계좌번호 수정 ");
			System.out.print("⑤현재 회원 목록 ");
			System.out.println("⑥이전화면 가기\t│");
			System.out.printf("└───────────────────────────────────────────────────────────────────────────────┘\n");
			System.out.printf("원하는 메뉴를 입력해주세요 ☞ ");
			String voice = s.nextLine();

			switch(voice) {
			case "1":
				service.userpwChange();
				break;
			case "2":
				service.pwChange();
				break;
			case "3":
				service.phonChange();
				break;
			case "4":
				service.abChange();
				break;
			case "5":
				service.userList();
				break;
			case "6":
				break a;
			default :
				System.out.println("잘못입력하셨습니다.");
				cds.pause();
				break;
			}

		}
	}
}
