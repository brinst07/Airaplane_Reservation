package controller;

import java.util.Scanner;

import service.BoardService;

public class BoardController {
	BoardService board = new BoardService();
	Scanner sc = new Scanner(System.in);
	public void start() {
		a: while(true) {
			board.show();
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
				board.insert();
				break;
			case 2:
				board.selectshow();
				break;
			case 3:
				board.modify();
				break;
			case 4:
				board.drop();
				break;
			case 5:
				break a;
				
			}
		}
	}
}
