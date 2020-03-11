package controller;

import java.util.Scanner;

import service.CleardelayService;
import service.QboardService;
//
public class QboardController {
	QboardService qboard = new QboardService();
	CleardelayService cds = new CleardelayService();
	Scanner sc = new Scanner(System.in);
	public void admin_start() {
		a: while(true) {
			qboard.show();
			System.out.println("--------------------관리자 공지게시판-------------------");
			System.out.println("┌───────┐");
			System.out.println("│① 삽입\t│");
			System.out.println("│② 조회\t│");
			System.out.println("│③ 수정\t│");
			System.out.println("│④ 삭제\t│");
			System.out.println("│⑤ 나가기\t│");
			System.out.println("└───────┘");
			
			int temp = Integer.parseInt(sc.nextLine());
			
			switch(temp) {
			case 1:
				qboard.insert();
				break;
			case 2:
				qboard.admin_selectshow();
				break;
			case 3:
				qboard.modify();
				break;
			case 4:
				qboard.drop();
				break;
			case 5:
				break a;
			default :
				System.out.println("잘못입력하셨습니다.");
				cds.pause();
				
			}
		}
	}
	
	public void normal_start() {
		
		qboard.selectshow();
		
	}
}
