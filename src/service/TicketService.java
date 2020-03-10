package service;

import java.util.ArrayList;

import dao.AirplaneTicketDao;
import vo.AirplaneTicketVO;

public class TicketService {

	AirplaneTicketDao airplaneticketdao = AirplaneTicketDao.getInstance();

	public void airticketList() { // 비행기 티켓 정보를 조회할때 출력
		ArrayList<AirplaneTicketVO> airList = airplaneticketdao.ReservationUserList();
		AirplaneTicketVO air = airList.get(0);

		System.out.println();
		System.out.println("┌───────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│   " + air.getAirCompany() + "\t▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ \t\t\t\t\t\t│");
		System.out.println("├─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─┤");
		System.out.print("│ NAME\t" + air.getUsername());
		System.out.print("  \t\tDATE\t" + air.getStartdate());
		System.out.println("  \tCLASS\t" + air.getSitclass() + "\t\t│");
		System.out.print("│ GATE\t" + air.getGate());
		System.out.print("  \t\t\tSIT\t" + air.getSitNum());
		System.out.println("  \t\tTIME\t" + air.getStarttime() + "\t\t│");
		System.out.print("│ TO\t" + air.getStartAp());
		System.out.print("  \t\tFROM\t" + air.getArriveAp() + "\t\t\t\t\t│");
		System.out.println();
		System.out.println("└───────────────────────────────────────────────────────────────────────────────┘");
	}
}
