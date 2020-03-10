package controller;

import java.util.Scanner;

import data.Session;
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
	
	public void start() {
		UserVO user = Session.LoginUser; // 로그인 된 유저정보 확인
		
		country.showcountry();
				
		Scanner sc = new Scanner(System.in); System.out.print("\n나라를 선택해주세요 >> ");		
		int cho = Integer.parseInt(sc.nextLine());
		
		city.showCity(cho);
		cho = Integer.parseInt(sc.nextLine());
		
		time.showTimeTable(cho);
	}

}
