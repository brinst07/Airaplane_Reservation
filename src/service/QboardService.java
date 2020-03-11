package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dao.BoardDao;
import dao.QboardDao;
import data.Database;
import data.Session;
import vo.BoardVO;
import vo.QboardVO;

public class QboardService {
	Scanner sc = new Scanner(System.in);
	QboardDao qboarddao = QboardDao.getInstance();
	CleardelayService cds = new CleardelayService();

	// 게시판 삽입메소드
	public void insert() {
		System.out.print("제목을 입력해주세요\n>> ");
		String title = sc.nextLine();
		System.out.print("본문을 입력해주세요\n>> ");
		String text = sc.nextLine();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		String userid = Session.LoginUser.getId();

		QboardVO board = new QboardVO();
		board.setTitle(title);
		board.setText(text);
		board.setDate(date);
		board.setUserid(userid);
		board.setIndex(Database.index++);
		qboarddao.insertboard(board);
	}

	// 게시판 조회반복메소드
	public void show() {
		cds.Clear();
		System.out.println("---------------------게시판--------------------");
		System.out.println("  번호\t     제목          \t작성자\t     작성일");
		System.out.println("----------------------------------------------");
		for (int i = qboarddao.qboard().size() - 1; i >= 0; i--) {
			QboardVO qboard = qboarddao.qboard().get(i);
			System.out.printf("[%d]   \t%s\t\t%s\t%s\n", i, qboard.getTitle(), qboard.getUserid(), qboard.getDate());
			System.out.println("-------------------------------------------------");
			if (qboard.getUseridQ() != null) {
				System.out.printf("\t질문 : %s\n", qboard.getUseridQ());
				System.out.println("-------------------------------------------------");
			}
			if (qboard.getUseridA() != null) {
				System.out.printf("\t\t답글 : %s\n", qboard.getUseridA());
				System.out.println("-------------------------------------------------");
			}
		}
	}

	// 게시판 본문 조회, 질문 메소드
	public void selectshow() {
		show();
		System.out.print("조회하고 싶은 게시물의 번호를 입력해주세요\n>> ");
		int temp = Integer.parseInt(sc.nextLine());
		if (temp > qboarddao.qboard().size() - 1) {
			System.out.println("※[SYSTEM] : 선택하신 게시물이 존재하지 않습니다.");
			cds.pause();
		} else {
			QboardVO board = qboarddao.qboard().get(temp);
			cds.Clear();
			System.out.println("---------------------본문---------------------");
			System.out.println(board.getText());
			System.out.println("---------------------------------------------");
			if (qboarddao.qboard().get(temp).getUseridQ() == null) {
				System.out.println("문의하시겠습니까?");
				System.out.print("[1] YES\t[2] NO\n>>");
				int temp1 = 0;
				try {
					temp1 = Integer.parseInt(sc.nextLine());
				} catch (Exception e) {
					System.out.println("※[SYSTEM] : 잘못 입력하셨습니다.");
					cds.pause();
					return;
				}
				switch (temp1) {
				case 1:
					System.out.print("질문 내용을 입력해주세요\n>> ");
					String q = sc.nextLine();
					qboarddao.qboard().get(temp).setUseridQ(q);
					System.out.println("입력되었습니다.");
					cds.pause();
					break;
				case 2:
					System.out.println("취소하였습니다.");
					cds.pause();
					break;
				default:
					System.out.println("※[SYSTEM] : 잘못입력하셨습니다! 초기화면으로 나갑니다");
					cds.pause();
				}
			} else {
				System.out.println("기존 질문을 삭제하고 새로 질문 하시겠습니까?");
				System.out.print("[1] YES\t[2] NO\n>>");
				int temp1 = 0;
				try {
					temp1 = Integer.parseInt(sc.nextLine());
				} catch (Exception e) {
					System.out.println("※[SYSTEM] : 잘못 입력하셨습니다.");
					cds.pause();
					return;
				}
				switch (temp1) {
				case 1:
					System.out.print("질문 내용을 입력해주세요\n>> ");
					String q = sc.nextLine();
					qboarddao.qboard().get(temp).setUseridQ(q);
					System.out.println("입력되었습니다.");

				case 2:
					System.out.println("취소하였습니다.");
					break;
				}
			}
		}
	}

	// 게시판 조회, 답글 메소드
	public void admin_selectshow() {
		show();
		int temp = 0;
		System.out.print("조회하고 싶은 게시물의 번호를 입력해주세요\n>> ");
		try {
			temp = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("※[SYSTEM] : 잘못 입력하셨습니다.");
			cds.pause();
			return;
		}
		if (temp > qboarddao.qboard().size() - 1) {
			System.out.println("선택하신 게시물이 존재하지 않습니다.");
			cds.pause();
			return;
		} else {
			QboardVO board = qboarddao.qboard().get(temp);
			cds.Clear();
			System.out.println("--------------------본문-----------------------");
			System.out.println(board.getText());
			System.out.println("---------------------------------------------");
			cds.pause();
			// 만약 질문이 있을 경우에 답글 달수 있는 메뉴가 뜨게 조건을 걸어줌
			if (board.getUseridQ() != null) {
				System.out.println("답변을 작성하시겠습니까?");
				System.out.print("[1] YES\t[2] NO\n>> ");

				int temp1 = Integer.parseInt(sc.nextLine());

				switch (temp1) {
				case 1:
					System.out.print("답글 내용을 입력해주세요\n>> ");
					String a = sc.nextLine();
					qboarddao.qboard().get(temp).setUseridA(a);
					System.out.println("입력되었습니다.");
					break;
				case 2:
					System.out.println("취소하였습니다.");
					break;
				}
			}

		}
	}

	// 게시판 수정 메소드
	public void modify() {
		show();
		System.out.print("수정하고 싶은 게시물의 번호를 입력해주세요\n>> ");
		QboardVO board = qboarddao.qboard().get(Integer.parseInt(sc.nextLine()));
		cds.Clear();
		System.out.println("수정하고 싶은 부분의 번호를 입력해주세요");
		System.out.print("[1] 제목 [2] 본문\n>> ");
		int temp = 0;
		try {
			temp = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("※[SYSTEM] : 잘못 입력하셨습니다.");
			cds.pause();
			return;
		}
		switch (temp) {
		case 1:
			cds.Clear();
			System.out.print("제목을 입력해주세요\n>> ");
			String title = sc.nextLine();
			board.setTitle(title);
			System.out.println("수정되었습니다.");
			break;
		case 2:
			cds.Clear();
			System.out.print("본문을 입력해주세요\n>> ");
			String text = sc.nextLine();
			board.setText(text);
			System.out.println("수정되었습니다.");
			break;
		}

	}

	// 게시판 삭제 메소드
	public void drop() {
		show();
		System.out.print("삭제하고 싶은 게시물의 번호를 입력해주세요\n>> ");
		try {
			qboarddao.qboard().remove(Integer.parseInt(sc.nextLine()));
		} catch (Exception e) {
			System.out.println("※[SYSTEM] : 잘못 입력하셨습니다.");
			cds.pause();
			return;
		}
		System.out.println("삭제되었습니다.");
		cds.pause();
	}

}
