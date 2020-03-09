package dao;

import java.util.ArrayList;

import data.Database;
import vo.CityVO;

public class CityDao {
	private static CityDao instance;
	
	private CityDao(){}
	
	public static CityDao getInstance() {
		if(instance == null) {
			instance = new CityDao();
		}
		return instance;
		
	}
	Database database = Database.getInstance();
	
	//테이블 반환해주는 메소드
	public ArrayList<CityVO> citylist(){
		return database.tb_city;
		
	}
	
	//테이블에 정보 삽입해주는 메소드
	public void insertcity(CityVO cityvo) {
		database.tb_city.add(cityvo);
	}
}
 