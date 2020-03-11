package controller;

import java.util.Scanner;

import service.BoardService;

public class BoardController {
	BoardService board = new BoardService();
	Scanner sc = new Scanner(System.in);
	public void start() {
		a: while(true) {
			board.show();
			System.out.println("┌───────────────────────────────────────────┐");
			System.out.print("│ ① 작성\t│");
			System.out.print(" ② 조회\t│");
			System.out.print(" ③ 수정\t│");
			System.out.print(" ④ 삭제\t│");
			System.out.print(" ⑤ 나가기       │\n");
			System.out.print("└───────────────────────────────────────────┘\n>> ");
			
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
