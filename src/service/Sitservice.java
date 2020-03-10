package service;

import java.util.Scanner;

import dao.SitDao;
import vo.SitVO;

public class Sitservice {
	
	Scanner sc = new Scanner(System.in);
	//좌석을 입력받는 메소드이다.
	public SitVO insertsit() {
		System.out.println("인원수를 입력해주세요");
		SitVO sitvo = new SitVO();
		int people = Integer.parseInt(sc.nextLine());
		sitvo.setSit_num(people);
		System.out.println("좌석 클래스를 선택해주세요");
		System.out.println("[1] First Class");
		System.out.println("[2] Business Class");
		System.out.print("[3] Economy Class");
		System.out.print(">>");
		int sit = Integer.parseInt(sc.nextLine());
		switch(sit) {
		case 1:
			sitvo.setSit_class("First Class");
			break;
		case 2:
			sitvo.setSit_class("Business Class");
			break;
		case 3:
			sitvo.setSit_class("Economy Class");
			break;
		}
		return sitvo;
	
	}
}
