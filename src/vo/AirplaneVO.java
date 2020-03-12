package vo;

public class AirplaneVO {

	String date;
	String time;
	String sitclass;
	String sitnum;
	int Gate;
	
	public int getGate() {
		return Gate;
	}

	public void setGate(int gate) {
		this.Gate = gate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSitclass() {
		return sitclass;
	}

	public void setSitclass(String sitclass) {
		this.sitclass = sitclass;
	}

	public String getSitnum() {
		return sitnum;
	}

	public void setSitnum(String sitnum) {
		this.sitnum = sitnum;
	}

}
