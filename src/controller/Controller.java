package controller;

import java.util.Scanner;

import data.Session;
import service.UserService;

public class Controller {
	
	public static void main(String[] args) {
		/*
		 * 조 소개 > 주제 소개 > 주제 선정 배경 > 프로그램 구조 > 시연
		 * 발표자 1명, ppt 및 시연 1명
		 * 
		 * Controller : 메뉴 선택
		 * Service : 메뉴 기능 수행
		 * Dao : 데이터베이스 접속
		 * VO : 데이터를 담는 클래스
		 * 
		 * 회원가입  로그인  회원목록  ->컨트롤러
		 * 
		 * 정보입력  정보입력  정보출력 ->서비스
		 * 
		 * DB저장    DB조회  ->다오
		 * 
		 * 데이터베이스~
		 * 
		 * 
		 * test
		 */
		
		new Controller().start();
		
	}
	
	UserService userservice = UserService.getInstance();
	BoardController board = new BoardController();
	QboardController qboard = new QboardController();
	NextController nc = new NextController();
	
	public void start() {		
		Scanner sc = new Scanner(System.in);
		int menu;
		
		do {
			System.out.println("-------------메 뉴-------------");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 회원목록");
			System.out.println("0. 프로그램 종료");
			System.out.println("--------------------------------");
			System.out.print("메뉴에 해당하는 번호 입력 >> ");
			
			menu = Integer.parseInt(sc.nextLine());
			
			switch(menu) {
			case 1://회원가입
				userservice.join();
				break;
			case 2://로그인
				userservice.login();
				nc.mainmenu();
				
				break;
			case 3://회원목록
				userservice.userList();
				break;
			case 4://프로그램 종료
				System.out.println("프로그램 종료");
				break;
			
			}
		}while(menu != 0);
	}

}
