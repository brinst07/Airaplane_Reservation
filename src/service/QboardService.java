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
	
	//게시판 삽입메소드
	public void insert() {
		System.out.println("제목을 입력해주세요");
		String title = sc.nextLine();
		System.out.println("본문을 입력해주세요");
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
	
	//게시판 조회반복메소드
	public void show() {
		cds.Clear();
		System.out.println("---------------------게시판--------------------");
		System.out.println("  번호\t     제목          \t작성자\t     작성일");
		System.out.println("----------------------------------------------");
		for(int i = qboarddao.qboard().size()-1; i>=0; i--) {
			QboardVO qboard = qboarddao.qboard().get(i);
			System.out.printf("[%d]   \t%s\t\t%s\t%s\n",i,qboard.getTitle(),qboard.getUserid(),qboard.getDate());
			System.out.println("-------------------------------------------------");	
			if(qboard.getUseridQ()!=null) {
				System.out.printf("\t질문 : %s\n",qboard.getUseridQ());
				System.out.println("-------------------------------------------------");
			}
			if(qboard.getUseridA()!=null) {
				System.out.printf("\t\t답글 : %s\n",qboard.getUseridA()); 	
				System.out.println("-------------------------------------------------");
			}
		}
	}
	
	//게시판 본문 조회, 질문 메소드
	public void selectshow() {
		show();
		System.out.println("조회하고 싶은 게시물의 번호를 입력해주세요");
		int temp = Integer.parseInt(sc.nextLine());
		if(temp>qboarddao.qboard().size()-1) {
			System.out.println("※[SYSTEM] : 선택하신 게시물이 존재하지 않습니다.");
			cds.pause();
		}else {
		QboardVO board = qboarddao.qboard().get(temp);
		cds.Clear();
		System.out.println("---------------------본문---------------------");
		System.out.println(board.getText());
		System.out.println("---------------------------------------------");
		if(qboarddao.qboard().get(temp).getUseridQ()==null) {
			System.out.println("문의하시겠습니까?");
			System.out.println("[1] YES");
			System.out.println("[2] NO");
			int temp1 = Integer.parseInt(sc.nextLine());
			switch(temp1) {
			case 1:
				System.out.println("질문 내용을 입력해주세요");
				String q = sc.nextLine();
				qboarddao.qboard().get(temp).setUseridQ(q);
				System.out.println("입력되었습니다.");
				cds.pause();
				break;
			case 2:
				System.out.println("빠져나갑니다.");
				break;
			default :
				System.out.println("잘못입력하셨습니다! 초기화면으로 나갑니다");
				cds.pause();
		}
		}else {
			System.out.println("기존 질문을 삭제하고 새로 질문 하시겠습니까?");
			System.out.println("[1] YES");
			System.out.println("[2] NO");
			int temp1 = Integer.parseInt(sc.nextLine());
			switch(temp1) {
			case 1: 
				System.out.println("질문 내용을 입력해주세요");
				String q = sc.nextLine();
				qboarddao.qboard().get(temp).setUseridQ(q);
				System.out.println("입력되었습니다.");
			
			case 2:
				System.out.println("빠져나갑니다.");
				break;
			}
		}
	}
	}		
	//게시판 조회, 답글 메소드
	public void admin_selectshow() {
		show();
		System.out.println("조회하고 싶은 게시물의 번호를 입력해주세요");
		int temp = Integer.parseInt(sc.nextLine());
		if(temp>qboarddao.qboard().size()-1) {
			System.out.println("선택하신 게시물이 존재하지 않습니다.");
		}else {
		QboardVO board = qboarddao.qboard().get(temp);
		cds.Clear();
		System.out.println("--------------------본문-----------------------");
		System.out.println(board.getText());
		System.out.println("---------------------------------------------");
		cds.pause();
		//만약 질문이 있을 경우에 답글 달수 있는 메뉴가 뜨게 조건을 걸어줌
		if(board.getUseridQ()!=null) {
			System.out.println("답하시겠습니까?");
			System.out.println("[1] YES");
			System.out.println("[2] NO");
			
			int temp1 = Integer.parseInt(sc.nextLine());
			
			switch(temp1) {
			case 1:
				System.out.println("답글 내용을 입력해주세요");
				String a = sc.nextLine();
				qboarddao.qboard().get(temp).setUseridA(a);
				System.out.println("입력되었습니다.");
				break;
			case 2:
				System.out.println("빠져나갑니다.");
				break;
			}
		}
	
		}
	}
	//게시판 수정 메소드
	public void modify() {
		show();
		System.out.println("수정하고 싶은 게시물의 번호를 입력해주세요");
		QboardVO board = qboarddao.qboard().get(Integer.parseInt(sc.nextLine()));
		cds.Clear();
		System.out.println("수정하고 싶은 부분의 번호를 입력해주세요");
		System.out.println("[1] 제목");
		System.out.println("[2] 본문");
		int temp = Integer.parseInt(sc.nextLine());
		switch(temp) {
		case 1:
			cds.Clear();
			System.out.println("제목을 입력해주세요");
			String title = sc.nextLine();
			board.setTitle(title);
			System.out.println("수정되었습니다.");
			break;
		case 2:
			cds.Clear();
			System.out.println("본문을 입력해주세요");
			String text = sc.nextLine();
			board.setText(text);
			System.out.println("수정되었습니다.");
			break;
		}
	
	}
	//게시판 삭제 메소드
	public void drop() {
		show();
		System.out.println("삭제하고 싶은 게시물의 번호를 입력해주세요");
		qboarddao.qboard().remove(Integer.parseInt(sc.nextLine()));
		System.out.println("삭제되었습니다.");
		cds.pause();
		}
	

}
