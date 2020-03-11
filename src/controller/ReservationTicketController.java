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
import service.CountryService;
import service.Sitservice;
import service.TimeTableService;
import service.UserService;
import vo.AirplaneTicketVO;
import vo.UserVO;

public class ReservationTicketController {
	UserService userService = UserService.getInstance();
	CityService city = CityService.getInstance();
	CountryService country = CountryService.getInstance();
	TimeTableService time = TimeTableService.getInstance();
	AirportService airport = AirportService.getInstance();
	AirplaneTicketVO ap = new AirplaneTicketVO();
	AirplaneTicketDao airplaneticketdao = new AirplaneTicketDao();
	Sitservice sitservice = new Sitservice();

	Database database = Database.getInstance();

	public void start() {
		UserVO user = Session.LoginUser; // 로그인 된 유저정보 확인
		Scanner sc = new Scanner(System.in);

		int gate = (int) (Math.random() * 20) + 1; // 랜덤으로 게이트 번호 지정

		String date = " ";

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // (2020-03-10) 형식으로 저장
		String styear = sdf.format(today).substring(0, 4); // 연도만 빼오기
		String stmon = sdf.format(today).substring(5, 7); // 달만 빼오기
		String stday = sdf.format(today).substring(8, 10); // 일자만 빼오기

		int year = Integer.parseInt(styear); // 올해
		int mon = Integer.parseInt(stmon); // 현재 월
		int day = Integer.parseInt(stday); // 현재 일

		while (true) {
			System.out.print("출발 날짜를 입력해주세요(yyyymmdd) : ");
			date = sc.nextLine();

			Calendar cal = Calendar.getInstance();

			if (date.length() != 8) { // 20200310이 들어오면 길이가 8개 이지만 그렇지 않을 경우 형식이 맞지 않은 것으로 간주
				System.out.println("날짜 형식에 맞게 입력해주세요.\n");
				continue;
			} else {
				String a = date.substring(0, 4); // 연
				String b = date.substring(4, 6); // 월
				String c = date.substring(6, 8); // 일

				int a1 = Integer.parseInt(a); // 인트로 형변환
				int b1 = Integer.parseInt(b);
				int c1 = Integer.parseInt(c);

				cal.set(a1, b1 - 1, c1); // 날짜형식으로 저장

				String test = sdf.format(cal.getTime()); // 20200532 처럼 존재하지 않는 날짜가 입력 되면 0601로 자동 변환 되기 때문에 그런 것을 막기 위해
															// 월을 비교하기 위함
				String test1 = test.substring(0, 4);
				String test2 = test.substring(5, 7);
				String test3 = test.substring(8, 10);

				int a2 = Integer.parseInt(test1);
				int b2 = Integer.parseInt(test2);
				int c2 = Integer.parseInt(test3);

				if (year > a2) { // 연도가 작을 경우
					System.out.println("현재 날짜보다 이전 날짜는 입력하실 수 없습니다.");
					continue;
				}

				if (year >= a2) { // 연도가 같거나 작으면서 월이 작을 경우
					if (mon > b2) {
						System.out.println("현재 날짜보다 이전 날짜는 입력하실 수 없습니다.");
						continue;
					}
				}

				if (year >= a2) { // 일자가 작을 경우
					if (mon >= b2) {
						if (day > c2) {
							System.out.println("현재 날짜보다 이전 날짜는 입력하실 수 없습니다.");
							continue;
						}
					}
				}

				if (b1 == b2) { // 날짜가 제대로 입력 된 경우
					date = sdf.format(cal.getTime());
				} else {
					System.out.println("잘못된 날짜 형식입니다.");
					continue;
				}
				break;
			}
		}

		country.showcountry(); // 나라 출력

		System.out.print("\n나라를 선택해주세요 >> ");
		int countrychoice = Integer.parseInt(sc.nextLine());

		int num = 0;
		switch (countrychoice) {
		case 2:
			num = 2;
			break;
		case 3:
			num = 3;
			break;
		case 4:
			num = 5;
			break;
		case 5:
			num = 7;
			break;
		case 6:
			num = 8;
			break;
		case 7:
			num = 10;
			break;
		case 8:
			num = 12;
			break;
		case 9:
			num = 13;
			break;
		case 10:
			num = 14;
			break;
		}

		int count = city.countCity(countrychoice); // 나라에 속한 도시의 갯수 세기. 1개일 경우 도시 선택 화면을 건너뜀

		int citychoice = 0;

		if (count == 1) { // 나라에 속한 도시의 갯수가 1개일 경우 실행
			citychoice = 1;
			System.out.println();
		} else {
			while (true) {
				city.showCity(countrychoice);
				System.out.print("\n도시를 선택해주세요 >> ");
				citychoice = Integer.parseInt(sc.nextLine());
				if (citychoice > count) {
					continue;
				} else {
					break;
				}
			}
		}

		String arriveCt = ""; // 도착할 도시 저장할 String

		if (citychoice + num == 1) {
			arriveCt = "L.A.";
		} else if (citychoice + num == 2) {
			arriveCt = "NEWYORK";
		} else if (citychoice + num == 3) {
			arriveCt = "HONGKONG";
		} else if (citychoice + num == 4) {
			arriveCt = "DOKYO";
		} else if (citychoice + num == 5) {
			arriveCt = "OSAKA";
		} else if (citychoice + num == 6) {
			arriveCt = "HANOI";
		} else if (citychoice + num == 7) {
			arriveCt = "DANANG";
		} else if (citychoice + num == 8) {
			arriveCt = "MANILA";
		} else if (citychoice + num == 9) {
			arriveCt = "BANGKOK";
		} else if (citychoice + num == 10) {
			arriveCt = "PATTAYA";
		} else if (citychoice + num == 11) {
			arriveCt = "BERLIN";
		} else if (citychoice + num == 12) {
			arriveCt = "MUNICH";
		} else if (citychoice + num == 13) {
			arriveCt = "PARIS";
		} else if (citychoice + num == 14) {
			arriveCt = "LONDON";
		} else if (citychoice + num == 15) {
			arriveCt = "JEJU";
		}

		System.out.println("[" + date + "]");
		System.out.print("인천 국제 공항 ---> ");
		airport.showAirport(citychoice + num);

		time.showTimeTable(citychoice + num); // 시간표 출력
		System.out.println("----------------------------");
		System.out.print("원하는 시간의 번호를 선택해주세요 >> ");
		int cho = Integer.parseInt(sc.nextLine());

		String startdate = time.returnstartTime(citychoice + num, cho); // 사용자가 선택한 출발 시간을 저장

		String [] sit = new String[10];
		String classsit = null;

		int people = sitservice.Human(); // 인원수 입력
		int sitclass = sitservice.classcho(); // 클래스 저장
		
		for (int i = 0; i < people; i++) { // 인원수 입력만큼 반복			
			sit[i] = sitservice.start(sitclass);

			if (sitclass == 1) {
				classsit = "First";
			} else if (sitclass == 2) {
				classsit = "Business";
			} else {
				classsit = "Economy";
			}
		}	

		for (int i = 0; i < people; i++) {
			ap.setUserid(user.getId()); // 유저 아이디 저장
			ap.setUsername(user.getName()); // 유저 이름 저장
			ap.setStartAp("SEOUL/INCHEON"); // 서울 인천 고정
			ap.setAirCompany("KOREA AIR"); // 코리아 항공 고정
			ap.setGate(gate); // 티켓에 게이트 번호 저장
			ap.setStartdate(date); // 티켓에 출발날짜 저장
			ap.setArriveAp(arriveCt); // 도착할 도시 이름 티켓에 저장
			ap.setStarttime(startdate); // 받아온 시간을 티켓에 저장
			ap.setSitNum(sit[i]); // 시트번호 저장
			ap.setSitclass(classsit); // 클래스 저장

			airplaneticketdao.insertReservation(ap);
			database.tb_airplane.add(ap); // 티켓에 add
		}
	}
}