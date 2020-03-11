package vo;

public class DateVO {
	private int date_num;
	private int Start_airport;
	private int arrive_airport;
	private String start_date;
	private String arrive_date;
	private int airplane_num;

	public int getDate_num() {
		return date_num;
	}

	public void setDate_num(int date_num) {
		this.date_num = date_num;
	}

	public int getStart_airport() {
		return Start_airport;
	}

	public void setStart_airport(int start_airport) {
		Start_airport = start_airport;
	}

	public int getArrive_airport() {
		return arrive_airport;
	}

	public void setArrive_airport(int arrive_airport) {
		this.arrive_airport = arrive_airport;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getArrive_date() {
		return arrive_date;
	}

	public void setArrive_date(String arrive_date) {
		this.arrive_date = arrive_date;
	}

	public int getAirplane_num() {
		return airplane_num;
	}

	public void setAirplane_num(int airplane_num) {
		this.airplane_num = airplane_num;
	}

}
