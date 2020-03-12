package service;

import java.util.ArrayList;

import dao.CountryDao;
import vo.CountryVO;

public class CountryService {

	private static CountryService instance;

	private CountryService() {
	}

	public static CountryService getInstance() {
		if (instance == null) {
			instance = new CountryService();
		}
		return instance;
	}

	CountryDao countrydao = CountryDao.getInstance();

	// 나라를 출력해주는 메소드
	public void showcountry() {
		ArrayList<CountryVO> country = countrydao.showcountryList();

		for (int i = 0; i < country.size(); i++) {
			CountryVO ct = country.get(i);
			System.out.print((i + 1) + ". " + ct.getCoun_name() + "\t\t");

			if (i % 5 == 4) {
				System.out.println();
			}
		}
	}

	public String getcountry(int i) { // 나라 이름 출력
		ArrayList<CountryVO> country = countrydao.showcountryList();
		CountryVO ct = country.get(i - 1);
		return ct.getCoun_name();
	}
}
