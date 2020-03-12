package dao;

import java.util.ArrayList;

import data.Database;
import vo.AirplaneVO;

public class AirplaneDao {
	private static AirplaneDao instance;

	public AirplaneDao() {
	}

	public static AirplaneDao getInstance() {
		if (instance == null) {
			instance = new AirplaneDao();
		}
		return instance;
	}

	Database database = Database.getInstance();

	// Airplane에 정보를 삽입하는 메소드
	public void insertinfor(AirplaneVO airplane) {
		database.tb_airplane.add(airplane);
	}

	public ArrayList<AirplaneVO> infoList() { // 티켓 출력을 위한 데이터베이스
		return database.tb_airplane;
	}
}
