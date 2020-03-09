package controller;

import vo.UserVO;

import java.util.Scanner;

import data.Session;
import service.ReservationTicket;

public class NextController {
	Scanner sc = new Scanner(System.in);
	BoardController bc = new BoardController();
	QboardController qbc = new QboardController();
   void mainmenu(){ // 로그인 후 들어오는 메인 화면
      UserVO user = Session.LoginUser;
      boolean root = false; // true일 경우 관리자로 로그인 한 것.
      
      ReservationTicket rst = new ReservationTicket();

      a: while (true) {
         System.out.println(user.getName() + "님 환영합니다.");
         if (user.getName().equals("관리자") && user.getId().equals("admin")) {
            root = true;
         }
         System.out.println("---------------메뉴---------------");
         if(!root){
            System.out.println("[1] 비행기 예약");
            System.out.println("[2] 예약티켓확인");
            System.out.println("[3] 회원정보수정");
            System.out.println("[4] 게시판");
            System.out.println("[5] 문의게시판 ");
            System.out.println("[0] 로그아웃");
            
         }
         //
         if (root) {
            System.out.println("2. 회원목록");
            qbc.admin_start();
            break;
         }
         System.out.println("---------------------------------");
         System.out.print(">> ");         
         int cho = 0;
         try{
            cho = Integer.parseInt(sc.nextLine());
         }catch(Exception e){
            continue;
         }
         
         switch(cho){
         case 0 : 
            System.out.println("로그아웃 하였습니다.");            
        
            break a;
         case 1 :
            if(!root){
               rst.start(); // 비행기 예약화면 이동           
               
            }else{
               System.out.println("비행기 관리자 관리");
            }
            break;
            
         case 2 :
            if(!root){
               System.out.println("예약티켓 확인 화면입니다.");
              
            }else{
               System.out.println("티켓 관리자 관리");
            }
            break;
            
         case 3:
            if(!root){
               System.out.println("회원정보 변경 화면입니다.");
            }else{
               System.out.println("회원정보 관리자 관리");
            }
            break;
            
         case 4:
            System.out.println("게시판으로 이동하는 화면입니다.");
            bc.start();
            break;
            
         case 5:
        	 System.out.println("문의게시판으로 이동하는 화면입니다.");
        	 qbc.normal_start();
        	 break;
         
         default :
            System.out.println("잘못 입력하셨습니다.");
         }
      }   
   }
}