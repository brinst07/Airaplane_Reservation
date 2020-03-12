package service;

import dao.AirplaneDao;
import dao.AirportDao;
import vo.AirplaneVO;
import vo.AirportVO;

public class AirportService {

	private static AirportService instance;
	
	AirplaneDao airplanedao = new AirplaneDao();
	AirplaneVO airplanevo = new AirplaneVO();

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
	
	String [] date = new String[100];
	String [] time = new String[100];	
	int [] gate = new int[100];
	
	public void getticket() {
		for (int i = 0; i < airplanedao.infoList().size(); i++) {
			AirplaneVO exairplane = airplanedao.infoList().get(i);
			date[i] = exairplane.getDate();
			time[i] = exairplane.getTime();	
			gate[i] = exairplane.getGate();
		}
	}
	
	public int gate(String userdate, String usertime) {
		for(int i = 0 ; i < airplanedao.infoList().size(); i++) {
			AirplaneVO exairplane = airplanedao.infoList().get(i);
			if(date[i].equals(userdate)) {				
				if(time[i].equals(usertime)) {					
					return gate[i];
				}				
			}
		}
		return 0;
					
	}

}
