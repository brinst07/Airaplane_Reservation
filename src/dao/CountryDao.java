package dao;

public class CountryDao {
	private static CountryDao instance;
	
	private CountryDao() {}
	
	public static CountryDao getInstance() {
		if(instance == null) {
			instance = new CountryDao();
		}
		return instance;
	}
	
	//테이블반환해주는 메소드
	
}
