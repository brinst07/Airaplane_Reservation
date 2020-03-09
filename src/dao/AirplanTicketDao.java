package dao;

import java.util.ArrayList;

import data.Database;
import vo.AirplaneTicketVO;

public class AirplanTicketDao {
	
	//싱글톤패턴
	
	private static AirplanTicketDao instance;
	
	private AirplanTicketDao() {}
	
	public static AirplanTicketDao getInstance() {
		if(instance == null) {
			instance = new AirplanTicketDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	//AirplaneTicket테이블에 정보를 삽입하는 메소드
	public void insertReservation(AirplaneTicketVO airticket) {
		database.tb_airplaneticket.add(airticket);
	}
	
	//테이블자체를 반환해주는 메소드
	public ArrayList<AirplaneTicketVO> ticket(){
		return database.tb_airplaneticket;
		
	}
	
	
}
