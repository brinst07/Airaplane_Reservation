package service;

import dao.AirportDao;
import vo.AirportVO;
import vo.TimeTableVO;

public class AirportService {

	private static AirportService instance;

	private AirportService() {
	}

	public static AirportService getInstance() {
		if (instance == null) {
			instance = new AirportService();
		}
		return instance;
	}

	AirportDao airport = AirportDao.getInstance();
	AirportVO airportvo = new AirportVO();

	public void showAirport(int cho) { // 공항 이름 출력
		for (int i = 0; i < airport.airport().size(); i++) {
			AirportVO exairport = airport.airport().get(i);
			if (exairport.getCity_num() == cho) {
				System.out.println(exairport.getAirport_name());
			}
		}
	}

}
