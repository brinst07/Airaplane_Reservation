package service;

import java.util.Scanner;

import data.Session;
import vo.UserVO;

public class ReservationTicket {
	UserService userService = UserService.getInstance();
	CityService city = CityService.getInstance();
	
	public void start() {
		UserVO user = Session.LoginUser; // 로그인 된 유저정보 확인
		
		userService.showcountry();
				
		Scanner sc = new Scanner(System.in); System.out.print("\n나라를 선택해주세요 >> ");
		int cho = Integer.parseInt(sc.nextLine());
		 
		city.showCity(cho); 
		
	}

}
