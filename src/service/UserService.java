package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.AirplaneTicketDao;
import dao.UserDao;
import data.Database;
import data.Session;
import vo.AirplaneTicketVO;
import vo.CountryVO;
import vo.UserVO;

//싱글톤 패턴
public class UserService {
   private static UserService instance;
   
   private UserService() {}
   
   public static UserService getInstance() {
      if(instance == null) {
         instance = new UserService();
      }
      return instance;
   }
   //test
   UserDao userdao  = UserDao.getInstance();
   AirplaneTicketDao airplaneticketdao = AirplaneTicketDao.getInstance(); 
   
   //회원가입
   public void join() {
      Scanner sc = new Scanner(System.in);
      UserVO user = new UserVO();
      
      System.out.print("아이디 : ");
      String id = sc.nextLine();
      System.out.print("비밀번호 : ");
      String pw = sc.nextLine();
      System.out.print("이름 : ");
      String name = sc.nextLine();
      
     
      
      System.out.print("비밀번호 질문 : "); 
      String pwQ = sc.nextLine();
      System.out.println("----입력 완료 -----");
      System.out.print("질문에 대한 답 입력\n>> ");
      String pwA = sc.nextLine();
      System.out.println("----입력 완료 -----");
      
         System.out.print("핸드폰 번호 입력해주세요 : ");
         String hp = sc.nextLine();
         
         
         String hpcheck = "(?i)[0-9]{3}[-]{0,1}[0-9]{3,4}[-]{0,1}[0-9]{4}";
         Pattern p = Pattern.compile(hpcheck);
         Matcher m = p.matcher(hp);
         while(true){
         if(m.matches()==true){
            System.out.println("----입력 완료 -----"); //핸드폰 번호 입력
            
            break;
         }else if(m.matches()==false){
            System.out.println("잘못 입력하셨습니다");
            System.out.print("다시 입력해주세요.\n>> ");
            hp =sc.nextLine();
            p = Pattern.compile(hpcheck);
            m = p.matcher(hp);   
         }
        } 
      
      System.out.println("결제 할 계좌번호를 입력 해주세요");
      System.out.println("-는 없이 입력해 주세요");
      System.out.print("EX) 국민53820204170480\n>> ");
      String ab = sc.nextLine();
      
      String abcheck = "[0-9]{1,10}[0-9]{4}"; 
      Pattern a = Pattern.compile(abcheck);
      Matcher b = a.matcher(ab);
      
      
      while(true) {
      if(b.matches()==false) {
         System.out.print("계좌번호를 다시 입력해주세요 >> ");
          ab = sc.nextLine();
          a = Pattern.compile(abcheck);
          b = a.matcher(ab);
      }else{
      System.out.println("----입력 완료 -----");
      break;
         }
      }
      
      user.setId(id);
      user.setPw(pw);
      user.setName(name);
      user.setPwq(pwQ);
      user.setPwa(pwA);
      user.setHp(hp);
      user.setAb(ab);   
      
      userdao.insertUser(user);
      System.out.println("가입해주셔서 감사합니다.");
   }
   
   //로그인
   public void login() {
      Scanner sc = new Scanner(System.in);
      
      System.out.print("아이디 : ");
      String id = sc.nextLine();
      System.out.print("비밀번호 : ");
      String pw = sc.nextLine();
      
      HashMap<String, String> param = new HashMap<>();
      param.put("ID",id);
      param.put("PASSWORD",pw);
      UserVO user = userdao.selectUser(param);
      //해쉬맵에 담아서 범용성 있게 만든다.
            
      while(true) {
      if(user == null) {
         System.out.print("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
         System.out.print("1.다시 시도   2.아이디 찾기  3. 비밀번호 찾기\n>> ");
         String idpw = sc.nextLine();
         
         if(idpw.equals("1")) {
            login();
            break;
         }
         if(idpw.equals("2")) {
           findid();
           login();
           break;
         }
         if(idpw.equals("3")) {
           findpw();
           login();
           break;
         }
         
          
      }else{
         System.out.println("로그인 성공!");
         System.out.println(user.getName() + "님 환영합니다.");   
         Session.LoginUser = user;
        break;

         }
      
      }
         //로그인 정보를 보통 세션에 저장한다. -> 세션은 브라우저~~
      }
   
   

public void findid() { //아이디 찾기
   
   Scanner sc = new Scanner(System.in);
   System.out.print("이름을 입력해주세요 : ");
   String name = sc.nextLine();
   System.out.print("연락처를 입력해주세요 : ");
   String hp = sc.nextLine();
   
    Database database = Database.getInstance();
   
    String result_id = null;
    
    while(true) {
       
         for(int i=0; i< database.tb_user.size(); i++) {
            UserVO tb_user = database.tb_user.get(i);
            String tb_name = tb_user.getName();
            String tb_hp = tb_user.getHp();
            if(tb_name.equals(name) && tb_hp.equals(hp)) {
             result_id = tb_user.getId();
                break;
               }
              }
         if(result_id == null) {
            System.out.println("---------------------------");
            System.out.println("이름 혹은 휴대폰번호가 틀리셨습니다.");
            System.out.println("[1]재시도 [2] 로그인화면가기");
            System.out.println("---------------------------\n>> ");
            String answer = sc.nextLine();
            if(answer.equals("1")) {
               findid();
               break;
            }else {
               login();
               break;
            }
            
            }else {
               System.out.println("---------------------------");
               System.out.println(name +"님의 아이디 : "+result_id);
               System.out.println("---------------------------");
               break;
            }
         
   }
      
}


public void findpw() { // 로그인 실패시 비밀번호 질문 답하기
   Database database = Database.getInstance();
   
   Scanner sc = new Scanner(System.in);
   System.out.print("아이디를 입력해주세요 : ");
   String id = sc.nextLine();
   
   UserVO tb_user= new UserVO();
   String pw_result = null;
   while(true) {
      for(int i=0; i<database.tb_user.size(); i++) {
         tb_user= database.tb_user.get(i);
         String id_result = tb_user.getId();
         
         if(id_result.equals(id)) {
            System.out.println(tb_user.getPwq());
            break;
         }
      }
      System.out.print("비밀번호 답을 입력해주세요 : ");
      String pwanswer= sc.nextLine();
      if(pwanswer.equals(tb_user.getPwa())) {
         System.out.println("비밀번호 는 : "+tb_user.getPw());
         break;
      }
      else{
         System.out.println("---------------------------");
         System.out.println("아이디 또는 비밀번호가 틀리셨습니다.");
         System.out.println("[1] 재시도  [2] 로그인화면가기 ");
         System.out.print("---------------------------\n>> ");
         String answer = sc.nextLine();
         if(answer.equals("1")) {
            findpw();
            break;
         }else {
            login();
            break;
         }
      }   }
}
//회원목록
   public void userList() {
      ArrayList<UserVO> userList = userdao.selectUserList();
      
      System.out.println("---------------------------------------------------");
      System.out.println("번호\t아이디\t이름\t비밀번호 질문\t비밀번호 답\t계좌번호\t연락처");
      System.out.println("---------------------------------------------------");
      for(int i = userList.size() -1; 0 <= i; i--) {
         UserVO user  = userList.get(i);
         System.out.println(i + 1 + "\t" + user.getId() + "\t" + user.getName()+ "\t" + user.getPwq()+ "\t" + user.getPwa()
        +"\t"+user.getAb()+"\t"+user.getHp());
      }
      System.out.println("---------------------------------------");
   }
   
   public void userpwInfo(){ //회원정보 수정 창  
      //비밀번호 수정
      //[2]비밀번호 찾기 질문 수정
      //[3] 연락처
      
      while(true){
         Scanner s = new Scanner(System.in);
         
         System.out.println("1. 비밀번호 수정");
         System.out.println("2. 비밀번호 찾기 질문 수정");
         System.out.println("3. 연락처 수정");
         System.out.println("4. 계좌번호 수정");
         System.out.print("5. 현재 회원 목록\n>> ");
         String voice = s.nextLine();
         
         if(voice.equals("1")){
            userpwChange();
            break;
         }
         if(voice.equals("2")){
            pwChange();
            break;
         }
         if(voice.equals("3")){
            phonChange();
            break;
         }
         if(voice.equals("4")){
           abChange(); 
         }
         if(voice.equals("5")) {
            userList();
         }
      }
   }

   
   public void abChange() { //계좌번호 수정
      UserVO user= Session.LoginUser;
      
      
   Scanner s = new Scanner(System.in);
   System.out.print("현재 계좌번호를 입력해주세요 : ");
    String newab = s.nextLine();
    
    while(true) {
    if(newab.equals(user.getAb())) {
      System.out.print("바꾸실 계좌번호를 입력해주세요 : ");
      newab= s.nextLine();
      System.out.println("입력 완료!");   
      break;
    }else {
       System.out.println("계좌번호가 일치하지 않습니다.");
       System.out.print("다시 입력해주세요.\n>> ");
       newab = s.nextLine();
       }
    }
    user.setAb(newab);
    
}

public void userpwChange(){ //회원 정보 비밀번호 수정
      Scanner s = new Scanner(System.in);
      
      System.out.println("-------비밀번호 수정---------");
      
      UserVO user = Session.LoginUser; //유저정보 끌어다 쓰기
      
      int count=0;
      UserVO pwcheck = null; //비밀번호 체크
      String pw = "";
      do{
         
         System.out.print("현재 비밀번호를 입력해주세요 : ");
          pw = s.nextLine();
         
         if(!(pw.equals(user.getPw()))){ //현재비밀번호가 맞는지 
         System.out.println("비밀번호가 틀립니다.");
            count++;
         if(count==5){
            System.out.println("비밀번호를 입력 횟수가 초과되었습니다");
            break;
            }
         }
      }while(!pw.equals(user.getPw()));  
      
      System.out.print("새로운 비밀번호를 입력해주세요 : ");
      String newpw = s.nextLine();
      
      user.setPw(newpw);
      
   }
   
   
   public void pwChange(){
      System.out.println("-------비밀번호 찾기 질문 수정---------");
      UserVO user = Session.LoginUser;
      
      Scanner s = new Scanner(System.in);
      
      System.out.println(user.getPwq());
      System.out.print("바꾸실 비밀번호 질문을 입력해주세요 : ");
      String newqwQ = s.nextLine();
      user.setPwq(newqwQ);
      
      System.out.print("바꾸신 질문 답을 입력해주세요 : ");
       String newqwA = s.nextLine();
      user.setPwa(newqwA);
      
      System.out.println(user.getPwq());
      System.out.println(user.getPwa());
      System.out.println("입력이 완료되었습니다.");
     
   }
   
   
   
   public void phonChange(){
      UserVO user = Session.LoginUser;
      Scanner sc = new Scanner(System.in);
      System.out.println("-------연락처 수정---------");
      System.out.print("바꾸실 연락처를 입력해주세요 : ");
      String newhp = sc.nextLine();
      
      
      String hpcheck = "(?i)[0-9]{3}[-]{0,1}[0-9]{3,4}[-]{0,1}[0-9]{4}";
      Pattern p = Pattern.compile(hpcheck);
      Matcher m = p.matcher(newhp);
      while(true){
      if(m.matches()==true){
         System.out.println("----입력 완료 -----"); //핸드폰 번호 입력
         break;
      }else if(m.matches()==false){
         System.out.println("잘못 입력하셨습니다");
         System.out.print("다시 입력해주세요\n>> ");
         newhp =sc.nextLine();
         p = Pattern.compile(hpcheck);
         m = p.matcher(newhp);   
      }
      }
      
      user.setHp(newhp);
      userpwInfo();
   }
   
   public void airticketList() { // 비행기 티켓 정보를 조회할때 출력
		ArrayList<AirplaneTicketVO> airList = airplaneticketdao.ReservationUserList();
		AirplaneTicketVO air = airList.get(0);

		System.out.println();
		System.out.println("┌───────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│   " + air.getAirCompany() + "\t▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ \t\t\t\t\t\t│");
		System.out.println("├─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─┤");
		System.out.print("│ NAME\t" + air.getUsername());
		System.out.print("  \t\tDATE\t" + air.getStartdate());
		System.out.println("  \tCLASS\t" + air.getSitclass() + "\t│");
		System.out.print("│ GATE\t" + air.getGate());
		System.out.print("  \t\t\tSIT\t" + air.getSitNum());
		System.out.println("  \t\tTIME\t" + air.getStarttime() + "\t\t│");
		System.out.print("│ TO\t" + air.getStartAp());
		System.out.print("  \t\tFROM\t" + air.getArriveAp() + "\t\t\t\t\t│");
		System.out.println();
		System.out.println("└───────────────────────────────────────────────────────────────────────────────┘");
	}
	
	public void showcountry() {
		ArrayList<CountryVO> country = airplaneticketdao.showcountryList();
		
		for(int i = 0 ; i < country.size() ; i++) {
			CountryVO ct = country.get(i);
			System.out.print((i+1) + ". " + ct.getCoun_name() + "\t");
			
			if(i%5==4) {
				System.out.println();
			}
		}
	}   
}