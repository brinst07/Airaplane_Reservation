package vo;

public class PaymentVO {
	private int tick_n;
	private String userid;
	private int price;
	private String ac;

	public int getTick_n() {
		return tick_n;
	}

	public void setTick_n(int tick_n) {
		this.tick_n = tick_n;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public String getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}

	public int getDate_num() {
		return date_num;
	}

	public void setDate_num(int date_num) {
		this.date_num = date_num;
	}

	private String buy_date;
	private int date_num;

}
