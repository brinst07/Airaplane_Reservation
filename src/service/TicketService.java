package service;

import java.util.ArrayList;

import dao.AirplaneTicketDao;
import data.Session;
import vo.AirplaneTicketVO;
import vo.UserVO;

public class TicketService {

	AirplaneTicketDao airplaneticketdao = AirplaneTicketDao.getInstance();
	UserVO user = Session.LoginUser;
	ArrayList<AirplaneTicketVO> airList = airplaneticketdao.ReservationUserList();

	public void airticketList() { // 비행기 티켓 정보를 조회할때 출력
		for (int i = 0; i < airList.size(); i++) {
			airList = airplaneticketdao.ReservationUserList();
			AirplaneTicketVO air = airList.get(i);
			if (user.getId().equals(air.getUserid())) {
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
	}

	public void rootticket() { // 관리자가 회원들의 비행기 티켓 정보를 조회할때 출력
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
	}
}
