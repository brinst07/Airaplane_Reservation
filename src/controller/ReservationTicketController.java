package controller;

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
		ap.setStartAp("SEOUL/INCHEON");		
		
		//----------------------------------------
		ap.setSitNum("15C");
		ap.setSitclass("Economy");			
		//----------------------------------------
		
		Scanner sc = new Scanner(System.in);
		
		int gate = (int)(Math.random()*20)+1; // 랜덤으로 게이트 번호 지정		
		ap.setGate(gate);
		
		System.out.print("출발 날짜를 입력해주세요 : ");
		String date = sc.nextLine();
		
		country.showcountry(); // 나라 출력
		
		ap.setStartdate(date);
		
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

		int count = city.countCity(countrychoice);
		
		int citychoice = 0;

		if (count == 1) {
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
		
		String arriveCt = "";
		
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
		ap.setArriveAp(arriveCt);
		ap.setAirCompany("KOREA AIR");
		
		System.out.print("인천 국제 공항 ---> ");
		airport.showAirport(citychoice + num);
		time.showTimeTable(citychoice + num); // 시간표 출력
		
		System.out.print("번호를 선택해주세요 >> ");
		int cho = Integer.parseInt(sc.nextLine());
		
		String startdate = time.returnstartTime(citychoice+num, cho);
		ap.setStarttime(startdate);	
		
		airplaneticketdao.insertReservation(ap);
		database.tb_airplane.add(ap);
	}

}