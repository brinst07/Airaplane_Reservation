package service;

import java.util.ArrayList;

import dao.CityDao;
import vo.CityVO;

public class CityService {
	//싱글톤
	private static CityService instance;
	
	private CityService() {}
	
	public static CityService getInstance() {
		if(instance == null) {
			instance = new CityService();
		}
		return instance;
	}
	
	CityDao city = CityDao.getInstance();
	CityVO cityvo = new CityVO();
	
	//city를 출력해주는 메소드
	public void showCity(int cho) {
//		//ArrayList<CityVO> city = citylist.
//		
//		for(int i = 0 ; i < city.size();i++) {
//			
//		}
	}
	
}
