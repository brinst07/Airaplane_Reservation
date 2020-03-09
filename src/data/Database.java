package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.AirplaneVO;
import vo.AirportVO;
import vo.BoardVO;
import vo.CityVO;
import vo.CountryVO;
import vo.DateVO;
import vo.PaymentVO;
import vo.QboardVO;
import vo.SitVO;
import vo.UserVO;

public class Database {
	
	private static Database instance;
	
	private Database() {}
	
	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;

	}
	
	public ArrayList<UserVO> tb_user = new ArrayList<>(); //���� ���̺�
	
	{
		UserVO user = new UserVO();
		user.setId("admin");
		user.setPw("admin");
		user.setName("관리자");
		tb_user.add(user);
	}
	
	//게시판 생성
	public ArrayList<BoardVO> tb_board = new ArrayList<>();
	
	public static int qindex = 0;
	public static int index = 0;
	//문의게시판 생성
	public ArrayList<QboardVO> tb_Qboard = new ArrayList<>();
	{
		QboardVO qboard = new QboardVO();
		qboard.setIndex(qindex++);
		qboard.setTitle("공지사항");
		qboard.setUserid("admin");
		qboard.setText("코로나 바이러스로 인해 운항이 지연됩니다.");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		qboard.setDate(date);
		tb_Qboard.add(qboard);
	}
	
	//payment 테이블
	public ArrayList<PaymentVO> tb_payment = new ArrayList<>();
	
	//좌석 테이블
	public ArrayList<SitVO> tb_sit = new ArrayList<>();
	
	//date 테이블
	public ArrayList<DateVO> tb_date = new ArrayList<>();
	
	//비행기 테이블
	public ArrayList<AirplaneVO> tb_airplane = new ArrayList<>();
	
	//공항 테이블
	public ArrayList<AirportVO> tb_airport = new ArrayList<>();
	
	//도시 테이블
	public ArrayList<CityVO> tb_city = new ArrayList<>();
	
	//나라 테이블
	public ArrayList<CountryVO> tb_country = new ArrayList<>();
	
	
}