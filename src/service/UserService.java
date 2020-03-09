package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.UserController;
import dao.UserDao;
import data.Session;
import vo.AirplaneTicketVO;
import vo.UserVO;

//싱글톤 패턴
public class UserService {
	private static UserService instance;

	private UserService() {
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	// test
	UserDao userdao = UserDao.getInstance();

	// 회원가입
	public void join() {
		Scanner sc = new Scanner(System.in);
		UserVO user = new UserVO();

		System.out.println("아이디 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호 : ");
		String pw = sc.nextLine();
		System.out.println("이름 : ");
		String name = sc.nextLine();

		System.out.println("비밀번호 질문 : ");
		String pwQ = sc.nextLine();
		System.out.println("----입력 완료 -----");
		System.out.println("질문에 대한 답 입력");
		String pwA = sc.nextLine();
		System.out.println("----입력 완료 -----");

		System.out.println("핸드폰 번호 입력해주세요 ");
		String hp = sc.nextLine();

		String hpcheck = "(?i)[0-9]{3}[-]{0,1}[0-9]{3,4}[-]{0,1}[0-9]{4}";
		Pattern p = Pattern.compile(hpcheck);
		Matcher m = p.matcher(hp);
		while (true) {
			if (m.matches() == true) {
				System.out.println("----입력 완료 -----"); // 핸드폰 번호 입력

				break;
			} else if (m.matches() == false) {
				System.out.println("잘못 입력하셨습니다");
				System.out.println("다시 입력해주세요.");
				hp = sc.nextLine();
				p = Pattern.compile(hpcheck);
				m = p.matcher(hp);
			}
		}

		System.out.println("결제 할 계좌번호를 입력 해주세요");
		System.out.println("-는 없이 입력해 주세요");
		System.out.println("EX) 국민53820204170480");
		String ab = sc.nextLine();

		String abcheck = "[0-9]{1,10}[0-9]{4}";
		Pattern a = Pattern.compile(abcheck);
		Matcher b = a.matcher(ab);

		while (true) {
			if (b.matches() == false) {
				System.out.println("계좌번호를 다시 입력해주세요");
				ab = sc.nextLine();
				a = Pattern.compile(abcheck);
				b = a.matcher(ab);
			} else {
				System.out.println("----입력 완료 -----");
				break;
			}
		}

		user.setId(id);
		user.setPw(pw);
		user.setName(name);
		user.setPwq(pwQ);
		user.setPwa(pwA);
		user.setHp(hp);
		user.setAb(ab);

		userdao.insertUser(user);
		System.out.println("가입해주셔서 감사합니다.");
	}

	// 로그인
	public void login() {
		Scanner sc = new Scanner(System.in);

		System.out.println("아이디 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호 : ");
		String pw = sc.nextLine();

		HashMap<String, String> param = new HashMap<>();
		param.put("ID", id);
		param.put("PASSWORD", pw);
		UserVO user = userdao.selectUser(param);
		// 해쉬맵에 담아서 범용성 있게 만든다.

		while (true) {
			if (user == null) {
				System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
				System.out.println("1.계속 도전   2.아이디 찾기   3.비밀번호 찾기");
				String idpw = sc.nextLine();

				if (idpw.equals("1")) {
					login();
					break;
				}
				if (idpw.equals("2")) {
					findid();
					login();
					break;
				}
				if (idpw.equals("3")) {
					findpw();
					login();
					break;
				}

			} else {
				System.out.println("로그인 성공!");
				System.out.println(user.getName() + "님 환영합니다.");
				Session.LoginUser = user;
				break;

			}

		}
		// 로그인 정보를 보통 세션에 저장한다. -> 세션은 브라우저~~
	}

	public void findid() {
		// TODO Auto-generated method stub

	}

	public void findpw() { // 로그인 실패시 비밀번호 질문 답하기
		UserVO user = Session.LoginUser;
		Scanner sc = new Scanner(System.in);
		System.out.println("비밀번호 질문 : " + user.getPwq());
		String answer = sc.nextLine();
		if (answer.equals(user.getPwa())) {

		}

	}

//회원목록
	public void userList() {
		ArrayList<UserVO> userList = userdao.selectUserList();

		System.out.println("---------------------------------------");
		System.out.println("번호\t아이디\t이름");
		System.out.println("---------------------------------------");
		for (int i = userList.size() - 1; 0 <= i; i--) {
			UserVO user = userList.get(i);
			System.out.println(
					i + 1 + "\t" + user.getId() + "\t" + user.getName() + "\t" + user.getPwq() + "\t" + user.getPwa());
		}
		System.out.println("---------------------------------------");
	}

	public void userpwInfo() { // 회원정보 수정 창
		// 비밀번호 수정
		// [2]비밀번호 찾기 질문 수정
		// [3] 연락처

		while (true) {
			Scanner s = new Scanner(System.in);

			System.out.println("1. 비밀번호 수정");
			System.out.println("2. 비밀번호 찾기 질문 수정");
			System.out.println("3. 연락처 수정");
			System.out.println("4. 계좌번호 수정");
			String voice = s.nextLine();

			if (voice.equals("1")) {
				userpwChange();
				break;
			}
			if (voice.equals("2")) {
				pwChange();
				break;
			}
			if (voice.equals("3")) {
				phonChange();
				break;
			}
			if (voice.equals("4")) {
				abChange();
			}
		}
	}

	public void abChange() { // 계좌번호 수정
		UserVO user = Session.LoginUser;

		Scanner s = new Scanner(System.in);
		System.out.println("현재 계좌번호를 입력해주세요.");
		String newab = s.nextLine();

		while (true) {
			if (newab.equals(user.getAb())) {
				System.out.println("바꾸실 계좌번호를 입력해주세요.");
				newab = s.nextLine();
				System.out.println("입력 완료!");
				break;
			} else {
				System.out.println("계좌번호가 일치하지 않습니다.");
				System.out.println("다시 입력해주세요.");
				newab = s.nextLine();
			}
		}
		user.setAb(newab);

	}

	public void userpwChange() { // 회원 정보 수정
		Scanner s = new Scanner(System.in);

		System.out.println("-------비밀번호 수정---------");

		UserVO user = Session.LoginUser; // 유저정보 끌어다 쓰기

		int count = 0;
		UserVO pwcheck = null; // 비밀번호 체크
		String pw = "";
		do {

			System.out.println("현재 비밀번호를 입력해주세요");
			pw = s.nextLine();

			if (!(pw.equals(user.getPw()))) { // 현재비밀번호가 맞는지
				System.out.println("비밀번호가 틀립니다.");
				count++;
				if (count == 5) {
					System.out.println("비밀번호를 입력 횟수가 초과되었습니다");
					break;
				}
			}
		} while (!pw.equals(user.getPw()));

		System.out.println("새로운 비밀번호를 입력해주세요.");
		String newpw = s.nextLine();

		user.setPw(newpw);

	}

	public void pwChange() {
		System.out.println("-------비밀번호 찾기 질문 수정---------");
		UserVO user = Session.LoginUser;

		Scanner s = new Scanner(System.in);

		System.out.println(user.getPwq());
		System.out.println("바꾸실 비밀번호 질문을 입력해주세요.");
		String newqwQ = s.nextLine();
		user.setPwq(newqwQ);

		System.out.println("바꾸신 질문 답을 입력해주세요");
		String newqwA = s.nextLine();
		user.setPwa(newqwA);

		System.out.println(user.getPwq());
		System.out.println(user.getPwa());
		System.out.println("입력이 완료되었습니다.");

	}

	public void phonChange() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------연락처 수정---------");
		System.out.println("바꾸실 연락처를 입력해주세요. ");
		String newhp = sc.nextLine();

		String hpcheck = "(?i)[0-9]{3}[-]{0,1}[0-9]{3,4}[-]{0,1}[0-9]{4}";
		Pattern p = Pattern.compile(hpcheck);
		Matcher m = p.matcher(newhp);
		while (true) {
			if (m.matches() == true) {
				System.out.println("----입력 완료 -----"); // 핸드폰 번호 입력

				break;
			} else if (m.matches() == false) {
				System.out.println("잘못 입력하셨습니다");
				System.out.println("다시 입력해주세요.");
				newhp = sc.nextLine();
				p = Pattern.compile(hpcheck);
				m = p.matcher(newhp);
			}
		}
		UserVO user = Session.LoginUser;
		user.setHp(newhp);

	}

	public void airticketList() { // 비행기 티켓 정보를 조회할때 출력
		ArrayList<AirplaneTicketVO> airList = userdao.ReservationUserList();
		AirplaneTicketVO air = airList.get(0);

		System.out.println();
		System.out.println("┌───────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│   " + air.getAirCompany() + "\t▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ \t\t\t\t\t\t│");
		System.out.println("├─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─┤");
		System.out.print("│ NAME\t" + air.getUsername());
		System.out.print("  \t\tDATE\t" + air.getStartdate());
		System.out.println("  \tCLASS\t" + air.getSitclass() + "\t│");
		System.out.print("│ GATE\t" + air.getGate());
		System.out.print("  \t\t\tSIT\t" + air.getSitNum());
		System.out.println("  \t\tTIME\t" + air.getStarttime() + "\t\t│");
		System.out.print("│ TO\t" + air.getStartAp());
		System.out.print("  \t\tFROM\t" + air.getArriveAp() + "\t\t\t\t\t│");
		System.out.println();
		System.out.println("└───────────────────────────────────────────────────────────────────────────────┘");
	}

}
