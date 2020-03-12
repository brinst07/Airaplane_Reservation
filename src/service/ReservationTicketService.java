package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import dao.AirplaneDao;
import dao.AirplaneTicketDao;
import data.Database;
import data.Session;
import vo.AirplaneTicketVO;
import vo.AirplaneVO;
import vo.UserVO;

public class ReservationTicketService {
   UserService userService = UserService.getInstance();
   CityService city = CityService.getInstance();
   CountryService country = CountryService.getInstance();
   TimeTableService time = TimeTableService.getInstance();
   AirportService airportservice = AirportService.getInstance();
   AirplaneTicketVO airplaneticketvo = new AirplaneTicketVO();
   AirplaneTicketDao airplaneticketdao = new AirplaneTicketDao();
   Sitservice sitservice = new Sitservice();
   CleardelayService cds = new CleardelayService();
   CalendarService calendarservice = new CalendarService();
   AirplaneDao airplanedao = new AirplaneDao();
   AirplaneVO airplanevo = new AirplaneVO();

   Database database = Database.getInstance();

   public void start() {
      UserVO user = Session.LoginUser; // 로그인 된 유저정보 확인
      Scanner sc = new Scanner(System.in);

      int gate = (int) (Math.random() * 20) + 1; // 게이트 저장
      String date = ""; // 출발날짜 지정

      Date today = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // (2020-03-10) 형식으로 저장
      String styear = sdf.format(today).substring(0, 4); // 연도만 빼오기
      String stmon = sdf.format(today).substring(5, 7); // 달만 빼오기
      String stday = sdf.format(today).substring(8, 10); // 일자만 빼오기

      int year = Integer.parseInt(styear); // 올해
      int mon = Integer.parseInt(stmon); // 현재 월
      int day = Integer.parseInt(stday); // 현재 일

      int m;
      while (true) {
         cds.Clear();
         System.out.println("===========[날짜선택]===========\n");
         System.out.print("조회하실 月을 입력해주세요 >> ");
         try {
            m = Integer.parseInt(sc.nextLine());
         } catch (Exception e) {
            System.out.println("현재 날짜보다 이전 날짜는 입력하실 수 없습니다.");
			cds.pause();
			continue;
         }
         break;
      }

      while (true) {
         cds.Clear();
         System.out.println("===========[날짜선택]===========\n");
         calendarservice.start(m);
         System.out.print("출발 날짜를 입력해주세요(yyyymmdd) : ");
         date = sc.nextLine();

         Calendar cal = Calendar.getInstance();

         if (date.length() != 8) { // 20200310이 들어오면 길이가 8개 이지만 그렇지 않을 경우 형식이 맞지 않은 것으로 간주
            System.out.println("날짜 형식에 맞게 입력해주세요.\n");
            cds.pause();
            continue;
         } else {
            String a = date.substring(0, 4); // 연
            String b = date.substring(4, 6); // 월
            String c = date.substring(6, 8); // 일

            int a1 = Integer.parseInt(a); // 인트로 형변환
            int b1 = Integer.parseInt(b);
            int c1 = Integer.parseInt(c);

            cal.set(a1, b1 - 1, c1); // 날짜형식으로 저장

            String test = sdf.format(cal.getTime()); // 20200532 처럼 존재하지 않는 날짜가 입력 되면 0601로 자동 변환 되기 때문에 그런 것을 막기 위해
                                             // 월을 비교하기 위함
            String test1 = test.substring(0, 4);
            String test2 = test.substring(5, 7);
            String test3 = test.substring(8, 10);

            int a2 = Integer.parseInt(test1);
            int b2 = Integer.parseInt(test2);
            int c2 = Integer.parseInt(test3);

            if (year > a2) { // 연도가 작을 경우
               System.out.println("현재 날짜보다 이전 날짜는 입력하실 수 없습니다.");
               cds.pause();
               continue;
            }

            if (year >= a2) { // 연도가 같거나 작으면서 월이 작을 경우
               if (mon > b2) {
                  System.out.println("현재 날짜보다 이전 날짜는 입력하실 수 없습니다.");
                  cds.pause();
                  continue;
               }
            }

            if (year >= a2) { // 일자가 작을 경우
               if (mon >= b2) {
                  if (day > c2) {
                     System.out.println("현재 날짜보다 이전 날짜는 입력하실 수 없습니다.");
                     cds.pause();
                     continue;
                  }
               }
            }

            if (b1 == b2) { // 날짜가 제대로 입력 된 경우
               date = sdf.format(cal.getTime());
               break;
            } else {
               System.out.println("잘못된 날짜 형식입니다.");
               cds.pause();
               continue;
            }
         }
      }
      int countrychoice;
      boolean checkct = false;
      while (true) {
         cds.Clear();
         System.out.println("==================================[국가선택]==================================");
         country.showcountry(); // 나라 출력
         System.out.println("============================================================================");
         if (checkct)
            System.out.println("[System] 잘못 입력하셨습니다.");
         System.out.print("나라를 선택해주세요 >> ");
         try {
            countrychoice = Integer.parseInt(sc.nextLine());
         } catch (Exception e) {
            checkct = true;
            continue;
         }
         break;
      }

      int num = 0;
      switch (countrychoice) {
      case 2:
         num = 2;
         break;
      case 3:
         num = 3;
         break;
      case 4:
         num = 5;
         break;
      case 5:
         num = 7;
         break;
      case 6:
         num = 8;
         break;
      case 7:
         num = 10;
         break;
      case 8:
         num = 12;
         break;
      case 9:
         num = 13;
         break;
      case 10:
         num = 14;
         break;
      }

      int count = city.countCity(countrychoice); // 나라에 속한 도시의 갯수 세기. 1개일 경우 도시 선택 화면을 건너뜀

      int citychoice = 0;// 카운트가 1일 경우 실행

      String countr = country.getcountry(countrychoice);

      if (count == 1) { // 나라에 속한 도시의 갯수가 1개일 경우 실행
         citychoice = 1;
         System.out.println();
      } else {
         boolean checkcity = false;
         while (true) {
            cds.Clear();
            System.out.println("==================================[도시선택]==================================");
            System.out.println("선택한 나라 : " + countr);
            city.showCity(countrychoice);
            System.out.println("\n============================================================================");
            if (checkcity)
               System.out.println("[System] 잘못 입력하셨습니다.");
            System.out.print("도시를 선택해주세요 >> ");
            try {
               citychoice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
               checkcity = true;
               cds.Clear();
               continue;
            }
            if (citychoice > count) {
               continue;
            } else {
               System.out.println();
               break;
            }
         }
      }

      String arriveCt = "";// 도착할 도시 저장할 String
      if (citychoice + num == 1) {
         arriveCt = "L.A.";
      } else if (citychoice + num == 2) {
         arriveCt = "NEWYORK";
      } else if (citychoice + num == 3) {
         arriveCt = "HONGKONG";
      } else if (citychoice + num == 4) {
         arriveCt = "DOKYO";
      } else if (citychoice + num == 5) {
         arriveCt = "OSAKA";
      } else if (citychoice + num == 6) {
         arriveCt = "HANOI";
      } else if (citychoice + num == 7) {
         arriveCt = "DANANG";
      } else if (citychoice + num == 8) {
         arriveCt = "MANILA";
      } else if (citychoice + num == 9) {
         arriveCt = "BANGKOK";
      } else if (citychoice + num == 10) {
         arriveCt = "PATTAYA";
      } else if (citychoice + num == 11) {
         arriveCt = "BERLIN";
      } else if (citychoice + num == 12) {
         arriveCt = "MUNICH";
      } else if (citychoice + num == 13) {
         arriveCt = "PARIS";
      } else if (citychoice + num == 14) {
         arriveCt = "LONDON";
      } else if (citychoice + num == 15) {
         arriveCt = "JEJU";
      }

      int cho = 0;
      boolean check = false;

      while (true) {
         cds.Clear();
         System.out.println("[" + date + "]");
         System.out.print("인천 국제 공항 ---> ");
         airportservice.showAirport(citychoice + num);
         int timesum = time.showTimeTable(citychoice + num); // 시간표 출력
         System.out.println("----------------------------");
         if (check)
            System.out.println("[System] 존재하지 않는 시간표 입니다.");
         check = false;
         System.out.print("원하는 시간의 번호를 선택해주세요 >> ");
         try {
            cho = Integer.parseInt(sc.nextLine());
         } catch (Exception e) {
            System.out.println("[System] 잘못 입력하셨습니다.");
            cds.pause();
            continue;
         }

         if (timesum <= cho || cho < 1) {
            cds.Clear();
            check = true;
            continue;
         } else {
            break;
         }
      }

      String starttime = time.returnstartTime(citychoice + num, cho); // 사용자가 선택한 출발 시간을 저장

      cds.Clear();
      System.out.println("선택 날짜 : " + date);
      System.out.println("나라 및 도시 : " + countr + " / " + arriveCt);
      System.out.println("선택 시간 : " + starttime);
      System.out.println("==================================[인원선택]==================================");

      String[] sit = new String[10];
      String classsit = null;

      int people = sitservice.Human(); // 인원수 입력
      cds.Clear();
      int sitclass = sitservice.classcho(); // 클래스 저장
      cds.Clear();
      
      if (sitclass == 1) {
         classsit = "First";
      } else if (sitclass == 2) {
         classsit = "Business";
      } else {
         classsit = "Economy";
      }

      sitservice.pomat();
      sitservice.getticket();
      sitservice.userchoi(date, starttime, classsit);
      
      for (int i = 0; i < people; i++) { // 인원수 입력만큼 반복         
         cds.Clear();     
         
         sit[i] = sitservice.start1(sitclass);
         if(sit[i].equals("")) {
        	 i -= 1;
         }
      }   

      boolean check2 = false;
      while (true) {
         cds.Clear();
         System.out.println("[선택 내역]");
         System.out.println("날짜 및 시간 : [" + date + " " + starttime + "]");
         System.out.print("행선지 : 인천 국제 공항 ---> ");
         airportservice.showAirport(citychoice + num);
         System.out.println("선택 인원 : " + people + "명");
         System.out.print("선택 좌석 : " + "[" + classsit + "] ");
         for (int i = 0; i < people; i++) {
            System.out.print(sit[i] + "  ");
         }
         System.out.println("\n-------------------------------");
         if (check2) {
            System.out.println("[System]잘못 입력하셨습니다.");
         }
         System.out.print("이대로 예약 하시겠습니까? (y/n)\n>> ");
         String reser = sc.nextLine();

         if (reser.equals("y")) {
            System.out.println("\n예약이 완료되었습니다.\n이용해주셔서 감사합니다.\n");
            break;
         } else if (reser.equals("n")) {
            System.out.println("예약이 취소되었습니다.");
            cds.pause();
            return;
         } else {
            check2 = true;
            continue;
         }
      }

      for (int i = 0; i < people; i++) {
         airplaneticketvo = new AirplaneTicketVO();
         airplanevo = new AirplaneVO();
         
         airplaneticketvo.setUserid(user.getId()); // 유저 아이디 저장
         airplaneticketvo.setUsername(user.getName()); // 유저 이름 저장
         airplaneticketvo.setStartAp("SEOUL/INCHEON"); // 서울 인천 고정
         airplaneticketvo.setAirCompany("KOREA AIR"); // 코리아 항공 고정
         airplaneticketvo.setGate(gate); // 티켓에 게이트 번호 저장
         airplaneticketvo.setStartdate(date); // 티켓에 출발날짜 저장
         airplaneticketvo.setArriveAp(arriveCt); // 도착할 도시 이름 티켓에 저장
         airplaneticketvo.setStarttime(starttime); // 받아온 시간을 티켓에 저장
         airplaneticketvo.setSitNum(sit[i]); // 시트번호 저장
         airplaneticketvo.setSitclass(classsit); // 클래스 저장
         
         airplanevo.setDate(date);
         airplanevo.setTime(starttime);
         airplanevo.setSitnum(sit[i]);
         airplanevo.setSitclass(classsit);
         
         airplanedao.insertinfor(airplanevo);         
         airplaneticketdao.insertReservation(airplaneticketvo); // 티켓에 add
      }
      cds.pause();
   }
}