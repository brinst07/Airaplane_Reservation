package service;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AirplaneTicketDao;
import data.Session;
import vo.AirplaneTicketVO;
import vo.UserVO;

public class TicketService {
	
	Scanner sc = new Scanner(System.in);
	AirplaneTicketDao airplaneticketdao = AirplaneTicketDao.getInstance();
	UserVO user = Session.LoginUser;
	ArrayList<AirplaneTicketVO> airList = airplaneticketdao.ReservationUserList();
	CleardelayService cds = new CleardelayService();
	
	public void firstticket() {
		System.out.println("--------------------예약티켓관리-------------------");
		System.out.println("┌───────┐");
		System.out.println("│① 조회\t│");
		System.out.println("│② 삭제\t│");
		System.out.println("│⑤ 나가기\t│");
		System.out.println("└───────┘");
		System.out.print("메뉴에 해당하는 번호 입력 ☞ ");
		System.out.println();
		String temp = sc.nextLine();
		switch(temp) {
		case "1" :
			airticketList();
			break;
		case "2" :
			deleteticket();
			break;
		case "3" :
			break;
		default :
			System.out.println("잘못입력하셨습니다.");
			break;
		}
	}
	
	public void airticketList() { // 비행기 티켓 정보를 조회할때 출력
		cds.Clear();		
		int count = 0;
		for (int i = 0; i < airList.size(); i++) {
			airList = airplaneticketdao.ReservationUserList();
			AirplaneTicketVO air = airList.get(i);
			if (user.getId().equals(air.getUserid())) {
				count++;
				System.out.println();
				System.out.println("┌───────────────────────────────────────────────────────────────────────────────┐");
				System.out.println("│   " + air.getAirCompany() + "\t▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ \t\t\t\t\t\t│");
				System.out.println("├─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─┤");
				System.out.print("│ NAME\t" + air.getUsername());
				System.out.print("  \t\tDATE\t" + air.getStartdate());
				if (air.getSitclass().equals("Business")) {
					System.out.println("  \tCLASS\t" + air.getSitclass() + "\t│");
				} else {
					System.out.println("  \tCLASS\t" + air.getSitclass() + "\t\t│");
				}
				System.out.print("│ GATE\t" + air.getGate());
				System.out.print("  \t\t\tSIT\t" + air.getSitNum());
				System.out.println("  \t\tTIME\t" + air.getStarttime() + "\t\t│");
				System.out.print("│ TO\t" + air.getStartAp());
				System.out.print("  \t\tFROM\t" + air.getArriveAp());
				if (air.getArriveAp().length() >= 8) {
					System.out.print("\t\t\t\t│");
				} else {
					System.out.print("\t\t\t\t\t│");
				}
				System.out.println();
				System.out.println("└───────────────────────────────────────────────────────────────────────────────┘");
				
			}	
		}
		if (count == 0) {
			System.out.println("조회된 티켓이 없습니다.\n");
		}
		cds.pause();
	}

	public void deleteticket() {
		System.out.println("티켓을 취소하시겠습니까?");
		System.out.println("① YES");
		System.out.println("② NO");
		String temp = sc.nextLine();
		switch(temp) {
		case "1":
			if(airList.size()>0) {
				for(int i = 0; i< airList.size(); i++) {
					AirplaneTicketVO air = airList.get(i);
					if(user.getId().equals(air.getUserid())) {
						airList.remove(i);
						
					}
					
				}
				deleteticket();
			}else {
				System.out.println("※[SYSTEM] : 티켓이 존재하지 않습니다.");
			}
			break;
		case "2":
			break;
		default :
			System.out.println("잘못입력하셨습니다.");
			cds.pause();
			break;
		}
		
	}
	public void rootticket() { // 관리자가 회원들의 비행기 티켓 정보를 조회할때 출력
		cds.Clear();
		if (airList.size() > 0) {
			for (int i = 0; i < airList.size(); i++) {
				airList = airplaneticketdao.ReservationUserList();
				AirplaneTicketVO air = airList.get(i);

				System.out.println();
				System.out.println("┌───────────────────────────────────────────────────────────────────────────────┐");
				System.out.println("│   " + air.getAirCompany() + "\t▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ \t\t\t\t\t\t│");
				System.out.println("├─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─┤");
				System.out.print("│ NAME\t" + air.getUsername());
				System.out.print("  \t\tDATE\t" + air.getStartdate());
				if (air.getSitclass().equals("Business")) {
					System.out.println("  \tCLASS\t" + air.getSitclass() + "\t│");
				} else {
					System.out.println("  \tCLASS\t" + air.getSitclass() + "\t\t│");
				}
				System.out.print("│ GATE\t" + air.getGate());
				System.out.print("  \t\t\tSIT\t" + air.getSitNum());
				System.out.println("  \t\tTIME\t" + air.getStarttime() + "\t\t│");
				System.out.print("│ TO\t" + air.getStartAp());
				System.out.print("  \t\tFROM\t" + air.getArriveAp());
				if (air.getArriveAp().length() >= 8) {
					System.out.print("\t\t\t\t│");
				} else {
					System.out.print("\t\t\t\t\t│");
				}
				System.out.println();
				System.out.println("└───────────────────────────────────────────────────────────────────────────────┘");
			}
			cds.pause();
		} else {
			System.out.println("저장된 티켓 정보가 존재하지 않습니다.");
			cds.pause();
		}
	}
}
