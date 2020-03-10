package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dao.AirplaneTicketDao;
import data.Database;
import data.Session;
import service.AirportService;
import service.CityService;
import service.CountryService;
import service.TimeTableService;
import service.UserService;
import vo.AirplaneTicketVO;
import vo.UserVO;

public class ReservationTicketController {
	UserService userService = UserService.getInstance();
	CityService city = CityService.getInstance();
	CountryService country = CountryService.getInstance();
	TimeTableService time = TimeTableService.getInstance();
	AirportService airport = AirportService.getInstance();	
	AirplaneTicketVO ap = new AirplaneTicketVO();
	AirplaneTicketDao airplaneticketdao = new AirplaneTicketDao();
	
	Database database = Database.getInstance();	
	
	public void start() {		
		UserVO user = Session.LoginUser; // 로그인 된 유저정보 확인
		
		ap.setUserid(user.getId()); // 유저 아이디 저장
		ap.setUsername(user.getName()); // 유저 이름 저장
		ap.setStartAp("SEOUL/INCHEON"); // 서울 인천 고정
		ap.setAirCompany("KOREA AIR"); // 코리아 항공 고정
		
		//----------------------------------------
		ap.setSitNum("15C");				
		//----------------------------------------
		
		Scanner sc = new Scanner(System.in);
		
		int gate = (int)(Math.random()*20)+1; // 랜덤으로 게이트 번호 지정		
		ap.setGate(gate); // 티켓에 게이트 번호 저장
		
		String date = " ";
		
		while(true) {
			System.out.print("출발 날짜를 입력해주세요(yyyymmdd) : ");
			date = sc.nextLine();
			
			if(date.length() != 8) {
				System.out.println("날짜 형식에 맞게 입력해주세요.\n");
				continue;
			}
			else {
				String a = date.substring(0,4);
				String b = date.substring(4,6);
				String c = date.substring(6,8);
				date = a + "-" + b + "-" + c;
				break;
			}
		}

		country.showcountry(); // 나라 출력
		
		ap.setStartdate(date); // 티켓에 출발날짜 저장
		
		System.out.print("\n나라를 선택해주세요 >> ");
		int countrychoice = Integer.parseInt(sc.nextLine());

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
		
		int citychoice = 0;

		if (count == 1) { // 나라에 속한 도시의 갯수가 1개일 경우 실행
			citychoice = 1;
			System.out.println();
		} else {
			while (true) {
				city.showCity(countrychoice);
				System.out.print("\n도시를 선택해주세요 >> ");
				citychoice = Integer.parseInt(sc.nextLine());
				if (citychoice > count) {
					continue;
				} else {
					break;
				}
			}
		}		
		
		String arriveCt = ""; // 도착할 도시 저장할 String
		
		if(citychoice+num == 1) {
			arriveCt = "L.A.";
		}
		else if(citychoice+num == 2) {
			arriveCt = "NEWYORK";
		}
		else if(citychoice+num == 3) {
			arriveCt = "HONGKONG";
		}
		else if(citychoice+num == 4) {
			arriveCt = "DOKYO";
		}
		else if(citychoice+num == 5) {
			arriveCt = "OSAKA";
		}
		else if(citychoice+num == 6) {
			arriveCt = "HANOI";
		}
		else if(citychoice+num == 7) {
			arriveCt = "DANANG";
		}
		else if(citychoice+num == 8) {
			arriveCt = "MANILA";
		}
		else if(citychoice+num == 9) {
			arriveCt = "BANGKOK";
		}
		else if(citychoice+num == 10) {
			arriveCt = "PATTAYA";
		}
		else if(citychoice+num == 11) {
			arriveCt = "BERLIN";
		}
		else if(citychoice+num == 12) {
			arriveCt = "MUNICH";
		}
		else if(citychoice+num == 13) {
			arriveCt = "PARIS";
		}
		else if(citychoice+num == 14) {
			arriveCt = "LONDON";
		}
		else if(citychoice+num == 15) {
			arriveCt = "JEJU";
		}
		ap.setArriveAp(arriveCt); // 도착할 도시 이름 티켓에 저장
				
		System.out.print("인천 국제 공항 ---> ");
		airport.showAirport(citychoice + num);
		
		time.showTimeTable(citychoice + num); // 시간표 출력
		System.out.println("----------------------------");
		System.out.print("원하는 시간의 번호를 선택해주세요 >> ");
		int cho = Integer.parseInt(sc.nextLine());
		
		String startdate = time.returnstartTime(citychoice+num, cho); // 사용자가 선택한 출발 시간을 저장
		ap.setStarttime(startdate);	// 받아온 시간을 티켓에 저장
		
		airplaneticketdao.insertReservation(ap);
		database.tb_airplane.add(ap); // 티켓에 add
	}

}