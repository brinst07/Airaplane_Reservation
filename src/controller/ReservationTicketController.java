package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AirportDao;
import data.Session;
import service.CityService;
import service.CountryService;
import service.TimeTableService;
import service.UserService;
import vo.AirportVO;
import vo.UserVO;

public class ReservationTicketController {
	UserService userService = UserService.getInstance();
	CityService city = CityService.getInstance();
	CountryService country = CountryService.getInstance();
	TimeTableService time = TimeTableService.getInstance();

	public void start() {
		UserVO user = Session.LoginUser; // 로그인 된 유저정보 확인

		country.showcountry();

		Scanner sc = new Scanner(System.in);
		System.out.print("\n나라를 선택해주세요 >> ");
		int cho = Integer.parseInt(sc.nextLine());

		int num = 0;
		switch (cho) {
			case 2:
				num = 1;	break;	
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
		
		city.showCity(cho);
		System.out.print("\n도시를 선택해주세요 >> ");
		
	
		time.showTimeTable(cho+num);
	}

}
