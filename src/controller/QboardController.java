package controller;

import java.util.Scanner;

import service.QboardService;

public class QboardController {
	QboardService qboard = new QboardService();
	Scanner sc = new Scanner(System.in);
	public void admin_start() {
		a: while(true) {
			qboard.show();
			System.out.println("--------------------관리자 공지게시판-------------------");
			System.out.println("[1] 삽입");
			System.out.println("[2] 조회");
			System.out.println("[3] 수정");
			System.out.println("[4] 삭제");
			System.out.println("[0] 나가기");
			
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
			case 0:
				break a;
				
			}
		}
	}
	
	public void normal_start() {
		
		qboard.selectshow();
		
	}
}
