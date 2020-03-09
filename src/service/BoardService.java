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

public class BoardService {
	
	Scanner sc = new Scanner(System.in);
	BoardDao boarddao = BoardDao.getInstance();
	QboardDao qboarddao = QboardDao.getInstance();
	
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
		
		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setText(text);
		board.setDate(date);
		board.setUserid(userid);
		board.setIndex(Database.index++);
		boarddao.insertboard(board);
	}
	
	//게시판 조회반복메소드
	public void show() {
		System.out.println("--------------------게시판--------------------");
		for(int i = boarddao.board().size()-1; i>=0; i--) {
			BoardVO board = boarddao.board().get(i);
			System.out.printf("[%d]\t%s\t%s\t%s\n",board.getIndex(),board.getTitle(),board.getUserid(),board.getDate());
			System.out.println("-------------------------------------------------");
		}
	}
	
	//게시판 본문 조회메소드
	public void selectshow() {
		show();
		System.out.println("조회하고 싶은 게시물의 번호를 입력해주세요");
		int temp = Integer.parseInt(sc.nextLine());
		BoardVO board = boarddao.board().get(temp);
		System.out.println(board.getText());
		System.out.println("---------------------------------------------");
		if(qboarddao.qboard().get(temp).getUseridQ()!=null) {
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
				break;
			case 2:
				System.out.println("빠져나갑니다.");
				break;
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
	
	//게시판 수정 메소드
	public void modify() {
		show();
		System.out.println("수정하고 싶은 게시물의 번호를 입력해주세요");
		BoardVO board = boarddao.board().get(Integer.parseInt(sc.nextLine()));
		System.out.println("수정하고 싶은 부분의 번호를 입력해주세요");
		System.out.println("[1] 제목");
		System.out.println("[2] 본문");
		int temp = Integer.parseInt(sc.nextLine());
		switch(temp) {
		case 1:
			System.out.println("제목을 입력해주세요");
			String title = sc.nextLine();
			board.setTitle(title);
		
		case 2:
			System.out.println("본문을 입력해주세요");
			String text = sc.nextLine();
			board.setText(text);
		}
	
	}
	//게시판 삭제 메소드
	public void drop() {
		show();
		System.out.println("삭제하고 싶은 게시물의 번호를 입력해주세요");
		boarddao.board().remove(Integer.parseInt(sc.nextLine()));
		System.out.println("삭제되었습니다.");
		}
	
}
