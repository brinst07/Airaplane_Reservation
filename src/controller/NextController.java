package controller;

import java.util.Scanner;

import data.Session;
import service.BoardService;
import service.CityService;
import service.CleardelayService;
import service.CountryService;
import service.ReservationTicketService;
import service.TicketService;
import service.UserService;
import vo.UserVO;

public class NextController {
	Scanner sc = new Scanner(System.in);
	BoardController bc = new BoardController();
	UserService userservice = UserService.getInstance();
	QboardController qbc = new QboardController();
	CityService city = CityService.getInstance();
	CountryService country = CountryService.getInstance();
	CleardelayService cds = new CleardelayService();
	ReservationTicketService rt = new ReservationTicketService();

	void mainmenu() { // 로그인 후 들어오는 메인 화면
		UserVO user = Session.LoginUser;
		boolean root = false; // true일 경우 관리자로 로그인 한 것.
		
		TicketService ts = new TicketService();

		a: while (true) {	
			cds.Clear();
			System.out.println("\t\t\t\t\t\t\t\t" + user.getName() + "님 환영합니다.");
			if (user.getName().equals("관리자") && user.getId().equals("admin")) {
				root = true;
			}			
			System.out.println("┌─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─[메뉴]─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─┐");
			if (!root) {
				System.out.print("│ [1] 비행기 예약  | ");
				System.out.print("[2] 예약티켓확인  | ");
				System.out.print("[3] 회원정보수정  | ");
				System.out.print("[4] 게시판  | ");
				System.out.println("[5] 문의게시판\t│");
				System.out.println("│ [0] 로그아웃\t\t\t\t\t\t\t\t\t│");
			} else {				
				System.out.print("│ [1] 회원목록   │  ");
				System.out.print("[2] 티켓관리   │  ");
				System.out.print("[3] 도시추가   │  ");
				System.out.print("[4] 도시삭제   │  ");
				System.out.println("[5] 관리자게시판\t│");
				System.out.println("│ [0] 로그아웃\t\t\t\t\t\t\t\t\t│");								
			}
			System.out.println("└─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─┘");			
			System.out.print(">> ");
			int cho = 0;
			try {
				cho = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				continue;
			}

			switch (cho) {
			case 0:
				System.out.println("로그아웃 하였습니다.");
				break a;
			case 1:
				if (!root) {
					rt.start(); // 비행기 예약화면 이동

				} else {
					userservice.userList();
				}
				break;

			case 2:
				if (!root) {
					try {
						ts.airticketList();
					} catch (Exception e) {
						System.out.println("예약된 티켓이 없습니다.");
					}

				} else {
					try {
						ts.rootticket();
					} catch (Exception e) {
						System.out.println("저장된 티켓이 없습니다.");
					}
				}
				break;

			case 3:
				if (!root) {
					userservice.userpwInfo(); // 회원정보수정
				} else {
					country.showcountry();
					System.out.print("\n나라를 선택해주세요 >> ");
					try {
						int cho2 = Integer.parseInt(sc.nextLine());
						city.insertCity(cho2);
					} catch (Exception e) {

					}
				}
				break;

			case 4:
				if(root) {
					country.showcountry();
					System.out.print("\n나라를 선택해주세요 >> ");
					try {
						int cho1 = Integer.parseInt(sc.nextLine());
						city.deleteCity(cho1);
					} catch (Exception e) {
						System.out.println("잘못입력하셨습니다.");
					}
				}
				else {
					bc.start();
				}
				break;

			case 5:
				if(root) {
					qbc.admin_start();
				}
				else {
					qbc.normal_start();
				}
				break;

			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
}