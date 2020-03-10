package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.AirplaneTicketVO;
import vo.AirportVO;
import vo.BoardVO;
import vo.CityVO;
import vo.CountryVO;
import vo.DateVO;
import vo.PaymentVO;
import vo.QboardVO;
import vo.SitVO;
import vo.TimeTableVO;
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
      
      user = new UserVO();
      user.setId("a");
      user.setPw("a");
      user.setName("KimTaeJin");
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
   public ArrayList<AirplaneTicketVO> tb_airplane = new ArrayList<>();
   
   //공항 테이블
   public ArrayList<AirportVO> tb_airport = new ArrayList<>();{
	   AirportVO airport = new AirportVO();
	   airport.setAirpot_num(1);
	   airport.setCit_num(1);
	   airport.setAirport_name(" 로스 엔젤로레스 ");
	   tb_airport.add(airport);
	   
	   airport.setAirpot_num(2);
	   airport.setCit_num(2);
	   airport.setAirport_name(" 욘 F 케네디 ");
	   tb_airport.add(airport);
	   
	   airport.setAirpot_num(3);
	   airport.setCit_num(3);
	   airport.setAirport_name("홍 콩 국제공항");
	   tb_airport.add(airport);
	   
	   airport.setAirpot_num(4);
	   airport.setCit_num(4);
	   airport.setAirport_name("도 쿄 국제공항");
	   tb_airport.add(airport);
	   
	   airport.setAirpot_num(5);
	   airport.setCit_num(5);
	   airport.setAirport_name(" 간 사 이 ");
	   tb_airport.add(airport);
	   
	   airport.setAirpot_num(6);
	   airport.setCit_num(6);
	   airport.setAirport_name(" 노이바이 ");
	   tb_airport.add(airport);
	   
	   airport.setAirpot_num(7);
	   airport.setCit_num(7);
	   airport.setAirport_name(" 다낭 공항");
	   tb_airport.add(airport);
	   
	   airport.setAirpot_num(8);
	   airport.setCit_num(8);
	   airport.setAirport_name(" 니노이 아키노 ");
	   tb_airport.add(airport);
	   
	   airport.setAirpot_num(9);
	   airport.setCit_num(9);
	   airport.setAirport_name(" 수완나품 ");
	   tb_airport.add(airport);
	   
	   
	   
   }
   			
   			
   		
   //도시 테이블
   public ArrayList<CityVO> tb_city = new ArrayList<>();{
      CityVO city = new CityVO();
      city.setCoun_num(1);
      city.setCity_num(1);
      city.setCity_name("LA");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(1);
      city.setCity_num(2);
      city.setCity_name("뉴욕");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(2);
      city.setCity_num(3);
      city.setCity_name("홍콩");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(3);
      city.setCity_num(4);
      city.setCity_name("도쿄");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(3);
      city.setCity_num(5);
      city.setCity_name("오사카");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(4);
      city.setCity_num(6);
      city.setCity_name("하노이");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(4);
      city.setCity_num(7);
      city.setCity_name("다낭");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(5);
      city.setCity_num(8);
      city.setCity_name("마닐라");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(6);
      city.setCity_num(9);
      city.setCity_name("방콕");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(6);
      city.setCity_num(10);
      city.setCity_name("파타야");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(7);
      city.setCity_num(11);
      city.setCity_name("베를린");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(7);
      city.setCity_num(12);
      city.setCity_name("뮌헨");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(8);
      city.setCity_num(13);
      city.setCity_name("파리");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(9);
      city.setCity_num(14);
      city.setCity_name("런던");
      tb_city.add(city);
      
      city = new CityVO();
      city.setCoun_num(10);
      city.setCity_num(15);
      city.setCity_name("제주도");
      tb_city.add(city);
      
   }
   
   //나라 테이블
   public ArrayList<CountryVO> tb_country = new ArrayList<>();{
      CountryVO country = new CountryVO();
      country.setCoun_num(1);
      country.setCoun_name("미국");
      country.setCount_time("19");
      tb_country.add(country);
      
      country = new CountryVO();
      country.setCoun_num(2);
      country.setCoun_name("홍콩");
      country.setCount_time("8");
      tb_country.add(country);
      
      country = new CountryVO();
      country.setCoun_num(3);
      country.setCoun_name("일본");
      country.setCount_time("0");
      tb_country.add(country);
      
      country = new CountryVO();
      country.setCoun_num(4);
      country.setCoun_name("베트남");
      country.setCount_time("3");
      tb_country.add(country);
      
      country = new CountryVO();
      country.setCoun_num(5);
      country.setCoun_name("필리핀");
      country.setCount_time("4");
      tb_country.add(country);
      
      country = new CountryVO();
      country.setCoun_num(6);
      country.setCoun_name("태국");
      country.setCount_time("3");
      tb_country.add(country);
      
      country = new CountryVO();
      country.setCoun_num(7);
      country.setCoun_name("독일");
      country.setCount_time("3");
      tb_country.add(country);
      
      country = new CountryVO();
      country.setCoun_num(8);
      country.setCoun_name("프랑스");
      country.setCount_time("8");
      tb_country.add(country);
      
      country = new CountryVO();
      country.setCoun_num(9);
      country.setCoun_name("영국");
      country.setCount_time("9");
      tb_country.add(country);
      
      country = new CountryVO();
      country.setCoun_num(10);
      country.setCoun_name("제주도");
      country.setCount_time("0");
      tb_country.add(country);
   }
   
   public ArrayList<AirplaneTicketVO>tb_airplaneticket = new ArrayList<>();{ // 티켓 정보 저장
      
   }
   
   public ArrayList<TimeTableVO>tb_timetable = new ArrayList<>();{ // 시간표 정보 저장	   
	   TimeTableVO time = new TimeTableVO();
	   
	   
	   
   }
   
}