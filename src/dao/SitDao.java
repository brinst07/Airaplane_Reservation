package dao;

import java.util.ArrayList;

import data.Database;
import vo.SitVO;

public class SitDao {
	//싱글톤
	private static SitDao instance;
	
	private SitDao() {}
	
	public static SitDao getInstance() {
		if(instance == null) {
			instance = new SitDao();
		}
		return instance;
	}
	
	Database databse = Database.getInstance();
	
	//데이터베이스에 접근하는 것
	public ArrayList<SitVO> sit(){
		return databse.tb_sit;
	}
	
	//데이터베이스에 입력하는 것
	public void insertsit(SitVO sitvo) {
		databse.tb_sit.add(sitvo);
	}
	
	//퍼스트 클래스를 반환해준다.
	public String[][] first(){
		return Database.Firstclass;
	}
	
	//비지니스 클래스를 반환해준다.
	public String[][] business(){
		return Database.Businessclass;
	}
	
	//이코노미 클래스를 반환해준다.
	public String[][] eco(){
		return Database.Economyclass;
	}
	
	

	
	
	
	
}
