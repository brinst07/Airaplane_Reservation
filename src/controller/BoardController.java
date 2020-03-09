package controller;

import java.util.Scanner;

import service.BoardService;

public class BoardController {
	BoardService board = new BoardService();
	Scanner sc = new Scanner(System.in);
	public void start() {
		a: while(true) {
			board.show();
			System.out.println("[1] 삽입");
			System.out.println("[2] 조회");
			System.out.println("[3] 수정");
			System.out.println("[4] 삭제");
			System.out.println("[0] 나가기");
			
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
			case 0:
				break a;
				
			}
		}
	}
}
