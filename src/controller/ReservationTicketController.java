package controller;

import java.util.Scanner;

import data.Session;
import service.AirportService;
import service.CityService;
import service.CountryService;
import service.TimeTableService;
import service.UserService;
import vo.UserVO;

public class ReservationTicketController {
	UserService userService = UserService.getInstance();
	CityService city = CityService.getInstance();
	CountryService country = CountryService.getInstance();
	TimeTableService time = TimeTableService.getInstance();
	AirportService airport = AirportService.getInstance();

	public void start() {
		UserVO user = Session.LoginUser; // 로그인 된 유저정보 확인

		country.showcountry();

		Scanner sc = new Scanner(System.in);
		System.out.print("\n나라를 선택해주세요 >> ");
		int cho = Integer.parseInt(sc.nextLine());

		int num = 0;
		switch (cho) {
			case 2:
				num = 2;	break;	
			case 3:
				num = 3;	break;
			case 4:
				num = 5;	break;
			case 5:
				num = 7;	break;
			case 6:
				num = 8;	break;
			case 7:
				num = 10;	break;
			case 8:
				num = 12;	break;
			case 9:
				num = 13;	break;
			case 10:
				num = 14;	break;
		}
		
		int count = city.countCity(cho);
		
		if(count==1) {
			cho=1;
			System.out.println();
		}
		else {
			city.showCity(cho);
			System.out.print("\n도시를 선택해주세요 >> ");
			cho = Integer.parseInt(sc.nextLine());
		}		
		
		System.out.print("인천 국제 공항 ---> ");
		airport.showAirport(cho+num);
		time.showTimeTable(cho+num); // 시간표 출력
	}

}
