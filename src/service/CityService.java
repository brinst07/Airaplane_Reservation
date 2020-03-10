package service;//

import java.util.Scanner;

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
	Scanner sc = new Scanner(System.in);
	
	//city를 출력해주는 메소드
	public void showCity(int cho) {
		for(int i=0; i<city.citylist().size(); i++) {
			CityVO excity = city.citylist().get(i);
			if(excity.getCoun_num()==cho) {
				System.out.println(excity.getCity_name());
				
			}
		}
	}
	
	//city를 추가해주는 메소드
	public void insertCity(int cho) {
		System.out.println("도시이름을 입력해주세요");
		String cityname = sc.nextLine();
		cityvo.setCity_name(cityname);
		cityvo.setCoun_num(cho);
		city.insertcity(cityvo);
	}
	
	//city를 삭제해주는 메소드
	public void deleteCity(int cho) {
		System.out.println("도시이름을 입력해주세요");
		String cityname = sc.nextLine();
		for(int i=0; i<city.citylist().size(); i++) {
			CityVO dcity = city.citylist().get(i);
			if(dcity.getCity_name().equals(cityname)) {
				city.citylist().remove(i);
				System.out.println("성공적으로 삭제되었습니다.!");
			}
		}
	}
}
