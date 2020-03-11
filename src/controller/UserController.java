package controller;

import java.util.Scanner;

import service.UserService;

public class UserController {
	UserService service = UserService.getInstance();
	
	   public void userpwInfo() { // 회원정보 수정 창
		      // 비밀번호 수정
		      // [2]비밀번호 찾기 질문 수정
		      // [3] 연락처
		   NextController nextcon = new NextController();
		   
		      while (true) {
		         Scanner s = new Scanner(System.in);

		         System.out.println("1. 비밀번호 수정");
		         System.out.println("2. 비밀번호 찾기 질문 수정");
		         System.out.println("3. 연락처 수정");
		         System.out.println("4. 계좌번호 수정");
		         System.out.println("5. 현재 회원 목록 ");
		         System.out.print("0. 이전화면 가기");
		         String voice = s.nextLine();

		         if (voice.equals("1")) {
		            service.userpwChange();
		            break;
		         }
		         if (voice.equals("2")) {
		            service.pwChange();
		           break;
		         }
		         if (voice.equals("3")) {
		            service.phonChange();
		            break;
		         }
		         if (voice.equals("4")) {
		            service.abChange();
		           break;
		         }
		         if (voice.equals("5")) {
		            service.userList();
		            break;
		         }
		         if( voice.equals("0")) {
		        	 break;
		         }
		        
		      }
		   }
}
