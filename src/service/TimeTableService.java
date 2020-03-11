package service;

import dao.AirportDao;
import dao.TimeTableDao;
import vo.AirportVO;
import vo.TimeTableVO;

public class TimeTableService {

	private static TimeTableService instance;

	private TimeTableService() {
	}

	public static TimeTableService getInstance() {
		if (instance == null) {
			instance = new TimeTableService();
		}
		return instance;
	}

	TimeTableDao time = TimeTableDao.getInstance();
	TimeTableVO timevo = new TimeTableVO();
	AirportDao airportdao = AirportDao.getInstance();

	public int showTimeTable(int cho) {
		int count = 1;
		for (int i = 0; i < time.TimeTableList().size(); i++) {
			TimeTableVO extime = time.TimeTableList().get(i);
			if (extime.getCity_num() == cho) {
				System.out.println("[" + count + "] " + extime.getStarttime() + " ~ " + extime.getArrivetime());
				count++;
			}
		}
		return count;
	}

	public String returnstartTime(int num, int cho) {
		for (int i = 0; i < time.TimeTableList().size(); i++) {
			TimeTableVO extime = time.TimeTableList().get(i);
			if (extime.getCity_num() == num) {
				if (extime.getTime_num() == cho) {
					return extime.getStarttime();
				}
			}
		}
		return "fail";
	}

}
