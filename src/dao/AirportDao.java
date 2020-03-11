package dao;

import java.util.ArrayList;

import data.Database;
import vo.AirportVO;

public class AirportDao {
	// 싱글톤싱글톤싱글싱글톤~
	private static AirportDao instance;

	private AirportDao() {
	}

	public static AirportDao getInstance() {
		if (instance == null) {
			instance = new AirportDao();
		}
		return instance;
	}

	Database databse = Database.getInstance();

	// 데이터베이스를 반환하는 메소드
	public ArrayList<AirportVO> airport() {
		return databse.tb_airport;

	}

}
