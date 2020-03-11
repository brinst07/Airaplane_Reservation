package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import dao.AirplaneTicketDao;
import data.Database;
import data.Session;
import service.AirportService;
import service.CityService;
import service.CleardelayService;
import service.CountryService;
import service.ReservationTicketService;
import service.Sitservice;
import service.TimeTableService;
import service.UserService;
import vo.AirplaneTicketVO;
import vo.AirplaneVO;
import vo.UserVO;

public class ReservationTicketController {
	UserService userService = UserService.getInstance();
	CityService city = CityService.getInstance();
	CountryService country = CountryService.getInstance();
	TimeTableService time = TimeTableService.getInstance();
	AirportService airportservice = AirportService.getInstance();
	AirplaneTicketVO airplaneticketvo = new AirplaneTicketVO();
	AirplaneTicketDao airplaneticketdao = new AirplaneTicketDao();
	Sitservice sitservice = new Sitservice();
	CleardelayService cds = new CleardelayService();
	ReservationTicketService rt = new ReservationTicketService();

	Database database = Database.getInstance();

	public void start() {
		UserVO user = Session.LoginUser; // 로그인 된 유저정보 확인
		Scanner sc = new Scanner(System.in);

		int gate = rt.gate(); // 게이트 저장
		String date = rt.startday(); // 출발날짜 지정

		country.showcountry(); // 나라 출력

		System.out.print("\n나라를 선택해주세요 >> ");
		int countrychoice = Integer.parseInt(sc.nextLine());

		int num = rt.Setnum(countrychoice);
		int count = city.countCity(countrychoice); // 나라에 속한 도시의 갯수 세기. 1개일 경우 도시 선택 화면을 건너뜀
		int citychoice = rt.citycho(count, countrychoice); // 카운트가 1일 경우 실행
		String arriveCt = rt.Arrivecity(citychoice, num); // 도착할 도시 저장할 String
		int cho = rt.Showtime(date, citychoice, num); // 시간표 출력
		String startdate = time.returnstartTime(citychoice + num, cho); // 사용자가 선택한 출발 시간을 저장

		cds.Clear();
		System.out.println("[좌석 및 인원 선택]");
		System.out.println("-------------------------------");

		String[] sit = new String[10];
		String classsit = null;

		int people = sitservice.Human(); // 인원수 입력
		cds.Clear();
		int sitclass = sitservice.classcho(); // 클래스 저장
		cds.Clear();

		for (int i = 0; i < people; i++) { // 인원수 입력만큼 반복
			cds.Clear();
			sit[i] = sitservice.start(sitclass);

			if (sitclass == 1) {
				classsit = "First";
			} else if (sitclass == 2) {
				classsit = "Business";
			} else {
				classsit = "Economy";
			}
		}

		boolean check2 = false;
		while (true) {
			cds.Clear();
			System.out.println("[선택 내역]");
			System.out.println("날짜 및 시간 : [" + date + " " + startdate + "]");
			System.out.print("행선지 : 인천 국제 공항 ---> ");
			airportservice.showAirport(citychoice + num);
			System.out.println("선택 인원 : " + people + "명");
			System.out.print("예약 좌석 : " + "[" + classsit + "] ");
			for (int i = 0; i < people; i++) {
				System.out.print(sit[i]);
			}
			System.out.println("\n-------------------------------");
			if (check2) {
				System.out.println("[System]잘못 입력하셨습니다.");
			}
			System.out.print("이대로 예약 하시겠습니까? (y/n)\n>> ");
			String reser = sc.nextLine();

			if (reser.equals("y")) {
				System.out.println("예약이 완료되었습니다.\n이용해주셔서 감사합니다.\n");
				break;
			} else if (reser.equals("n")) {
				System.out.println("예약이 취소되었습니다.");
				cds.pause();
				return;
			} else {
				check2 = true;
				continue;
			}
		}

		for (int i = 0; i < people; i++) {
			airplaneticketvo = new AirplaneTicketVO();
			airplaneticketvo.setUserid(user.getId()); // 유저 아이디 저장
			airplaneticketvo.setUsername(user.getName()); // 유저 이름 저장
			airplaneticketvo.setStartAp("SEOUL/INCHEON"); // 서울 인천 고정
			airplaneticketvo.setAirCompany("KOREA AIR"); // 코리아 항공 고정
			airplaneticketvo.setGate(gate); // 티켓에 게이트 번호 저장
			airplaneticketvo.setStartdate(date); // 티켓에 출발날짜 저장
			airplaneticketvo.setArriveAp(arriveCt); // 도착할 도시 이름 티켓에 저장
			airplaneticketvo.setStarttime(startdate); // 받아온 시간을 티켓에 저장
			airplaneticketvo.setSitNum(sit[i]); // 시트번호 저장
			airplaneticketvo.setSitclass(classsit); // 클래스 저장

			airplaneticketdao.insertReservation(airplaneticketvo);
			database.tb_airplane.add(airplaneticketvo); // 티켓에 add
		}
		cds.pause();
	}
}