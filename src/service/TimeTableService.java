package service;

import dao.TimeTableDao;
import vo.TimeTableVO;

public class TimeTableService {
	
private static TimeTableService instance;
	
	private TimeTableService() {}
	
	public static TimeTableService getInstance() {
		if(instance == null) {
			instance = new TimeTableService();
		}
		return instance;
	}
	
	TimeTableDao time = TimeTableDao.getInstance();
	TimeTableVO timevo = new TimeTableVO();
	
	public void showTimeTable(int cho) {
		for(int i=0; i<5; i++) {
			TimeTableVO extime = time.TimeTableList().get(i);
			if(extime.getCity_num()==cho) {
				System.out.println("[" + (i+1) + "] " + extime.getStarttime() + " ~ " + extime.getArrivetime());				
			}
		}
	}

}
