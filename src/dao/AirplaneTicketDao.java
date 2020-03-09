package dao;

import java.util.ArrayList;

import data.Database;
import vo.AirplaneTicketVO;
import vo.CountryVO;

public class AirplaneTicketDao {
	
	//싱글톤패턴
	
	private static AirplaneTicketDao instance;
	
	private AirplaneTicketDao() {}
	
	public static AirplaneTicketDao getInstance() {
		if(instance == null) {
			instance = new AirplaneTicketDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	//AirplaneTicket테이블에 정보를 삽입하는 메소드
	public void insertReservation(AirplaneTicketVO airticket) {
		database.tb_airplaneticket.add(airticket);
	}
	
	public ArrayList<AirplaneTicketVO> ReservationUserList(){ // 티켓 출력을 위한 데이터베이스
		return database.tb_airplaneticket;
	}
	
	public ArrayList<CountryVO> showcountryList(){ // 비행기 예약하는 화면에서 나라의 정보를 보여주기 위한 데이터베이스
		return database.tb_country;
	}
	
}
