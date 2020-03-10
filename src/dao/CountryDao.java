package dao;//

import java.util.ArrayList;

import data.Database;
import vo.CountryVO;

public class CountryDao {
	private static CountryDao instance;
	
	private CountryDao() {}
	
	public static CountryDao getInstance() {
		if(instance == null) {
			instance = new CountryDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	//테이블반환해주는 메소드
	
	public ArrayList<CountryVO> showcountryList(){ // 비행기 예약하는 화면에서 나라의 정보를 보여주기 위한 데이터베이스
		return database.tb_country;
	}
	
}
