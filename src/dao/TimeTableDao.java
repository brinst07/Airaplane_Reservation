package dao;

import java.util.ArrayList;

import data.Database;
import vo.TimeTableVO;

public class TimeTableDao {
	
	private static TimeTableDao instance;

	private TimeTableDao() {
		
	}
	public static TimeTableDao getInstance() {
		if (instance == null) {
			instance = new TimeTableDao();
		}
		return instance;
	}

	Database database = Database.getInstance();
	
	public ArrayList<TimeTableVO> TimeTableList() { // 시간표 출력을 위한 메소드
		return database.tb_timetable;
	}
}
