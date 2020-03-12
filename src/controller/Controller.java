package controller;

import java.util.Scanner;

import data.Session;
import service.CleardelayService;
import service.UserService;

public class Controller {

	
	public static void main(String[] args) {
		/*
		 * 조 소개 > 주제 소개 > 주제 선정 배경 > 프로그램 구조 > 시연 발표자 1명, ppt 및 시연 1명
		 * 
		 * Controller : 메뉴 선택 Service : 메뉴 기능 수행 Dao : 데이터베이스 접속 VO : 데이터를 담는 클래스
		 * 
		 * 회원가입 로그인 회원목록 ->컨트롤러
		 * 
		 * 정보입력 정보입력 정보출력 ->서비스
		 * 
		 * DB저장 DB조회 ->다오
		 */
		
		CleardelayService cds2 = new CleardelayService();

		System.out.println(
				"               AAA                 iiii                   PPPPPPPPPPPPPPPPP    lllllll                                                        ");
		cds2.delay(300);
		System.out.println(
				"              A:::A               i::::i                  P::::::::::::::::P   l:::::l                                                        ");
		cds2.delay(300);
		System.out.println(
				"             A:::::A               iiii                   P::::::PPPPPP:::::P  l:::::l                                                        ");
		cds2.delay(300);
		System.out.println(
				"            A:::::::A                                     PP:::::P     P:::::P l:::::l                                                        ");
		cds2.delay(300);
		System.out.println(
				"           A:::::::::A           iiiiiii rrrrr   rrrrrrrrr   P::::P     P:::::P l::::l   aaaaaaaaaaaaa  nnnn  nnnnnnnn        eeeeeeeeeeee    ");
		cds2.delay(300);
		System.out.println(
				"          A:::::A:::::A          i:::::i r::::rrr:::::::::r  P::::P     P:::::P l::::l   a::::::::::::a n:::nn::::::::nn    ee::::::::::::ee  ");
		cds2.delay(300);
		System.out.println(
				"         A:::::A A:::::A          i::::i r:::::::::::::::::r P::::PPPPPP:::::P  l::::l   aaaaaaaaa:::::an::::::::::::::nn  e::::::eeeee:::::ee");
		cds2.delay(300);
		System.out.println(
				"        A:::::A   A:::::A         i::::i rr::::::rrrrr::::::rP:::::::::::::PP   l::::l            a::::ann:::::::::::::::ne::::::e     e:::::e");
		cds2.delay(300);
		System.out.println(
				"       A:::::A     A:::::A        i::::i  r:::::r     r:::::rP::::PPPPPPPPP     l::::l     aaaaaaa:::::a  n:::::nnnn:::::ne:::::::eeeee::::::e");
		cds2.delay(300);
		System.out.println(
				"      A:::::AAAAAAAAA:::::A       i::::i  r:::::r     rrrrrrrP::::P             l::::l   aa::::::::::::a  n::::n    n::::ne:::::::::::::::::e ");
		cds2.delay(300);
		System.out.println(
				"     A:::::::::::::::::::::A      i::::i  r:::::r            P::::P             l::::l  a::::aaaa::::::a  n::::n    n::::ne::::::eeeeeeeeeee  ");
		cds2.delay(300);
		System.out.println(
				"    A:::::AAAAAAAAAAAAA:::::A     i::::i  r:::::r            P::::P             l::::l a::::a    a:::::a  n::::n    n::::ne:::::::e           ");
		cds2.delay(300);
		System.out.println(
				"   A:::::A             A:::::A   i::::::i r:::::r          PP::::::PP          l::::::la::::a    a:::::a  n::::n    n::::ne::::::::e          ");
		cds2.delay(300);
		System.out.println(
				"  A:::::A               A:::::A  i::::::i r:::::r          P::::::::P          l::::::la:::::aaaa::::::a  n::::n    n::::n e::::::::eeeeeeee  ");
		cds2.delay(300);
		System.out.println(
				" A:::::A                 A:::::A i::::::i r:::::r          P::::::::P          l::::::l a::::::::::aa:::a n::::n    n::::n  ee:::::::::::::e  ");
		cds2.delay(300);
		System.out.println(
				"AAAAAAA                   AAAAAAAiiiiiiii rrrrrrr          PPPPPPPPPP          llllllll  aaaaaaaaaa  aaaa nnnnnn    nnnnnn    eeeeeeeeeeeeee  ");
		cds2.delay(1000);
		new Controller().start();
	}

	UserService userservice = UserService.getInstance();
	BoardController board = new BoardController();
	QboardController qboard = new QboardController();
	NextController nc = new NextController();
	CleardelayService cds = new CleardelayService();

	public void start() {
		Scanner sc = new Scanner(System.in);
		int menu = 0;

		do {
			cds.Clear();
			System.out.println("┌─────MENU──────┐");
			System.out.println("│① 회원가입\t│");
			System.out.println("│② 로그인\t\t│");
			System.out.println("│③ 프로그램 종료\t│");
			System.out.println("└───────────────┘");
			System.out.print("메뉴에 해당하는 번호 입력 ☞ ");

			try {
				menu = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				continue;
			}

			switch (menu) {
			case 1:// 회원가입
				userservice.join();
				break;
			case 2:// 로그인
				userservice.login();
				nc.mainmenu();
				break;
			case 3:// 프로그램 종료
				System.out.println("프로그램 종료");
				break;

			}
		} while (menu != 3);
	}

}
