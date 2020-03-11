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

		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setText(text);
		board.setDate(date);
		board.setUserid(userid);
		board.setIndex(Database.index++);
		boarddao.insertboard(board);
		}
	
	// 게시판 조회반복메소드
	public void show() {
		cds.Clear();
		System.out.println("---------------------게시판--------------------");
		System.out.println("  번호\t     제목          \t작성자\t     작성일");
		System.out.println("----------------------------------------------");
		for (int i = boarddao.board().size() - 1; i >= 0; i--) {
			BoardVO board = boarddao.board().get(i);
			System.out.printf(" [%d]\t%s\t\t%s\t%s\n", board.getIndex(), board.getTitle(), board.getUserid(),
					board.getDate());
		}

		System.out.println("\n----------------------------------------------");
	}

	// 게시판 본문 조회메소드
	public void selectshow() {
		
		if(boarddao.board().size()==0) {
			System.out.println("조회할 게시물이 존재하지 않습니다.");
			cds.pause();
		}else {
			show();	
		System.out.print("조회하고 싶은 게시물의 번호를 입력해주세요\n>> ");
		int temp = Integer.parseInt(sc.nextLine());
		BoardVO board = boarddao.board().get(temp);
		cds.Clear();
		System.out.println("---------------------게시판--------------------");
		System.out.print("제목 : " + board.getTitle() + "\t\t\t작성자 : " + board.getUserid());
		System.out.println("\n----------------------------------------------");
		System.out.println(board.getText());
		System.out.println("----------------------------------------------\n");
		cds.pause();
		}
	}	
	// 게시판 수정 메소드
	public void modify() {
		if(boarddao.board().size()==0) {
			System.out.println("수정할 게시물이 존재하지 않습니다.");
			cds.pause();
		}else {
		show();
		System.out.println("수정하고 싶은 게시물의 번호를 입력해주세요");
		BoardVO board = boarddao.board().get(Integer.parseInt(sc.nextLine()));
		System.out.print("수정하고 싶은 부분의 번호를 입력해주세요");
		System.out.print("[1] 제목\t");
		System.out.print("[2] 본문ln>> ");

		int temp = Integer.parseInt(sc.nextLine());
				
		switch (temp) {
		case 1:
			System.out.print("제목을 입력해주세요 >> ");
			try {
				String title = sc.nextLine();
				board.setTitle(title);
			} catch (Exception e) {
				System.out.println("※[SYSTEM] : 제목은 글자만 가능합니다.");
			}
			
			

		case 2:
			System.out.print("본문을 입력해주세요 >> ");
			
		}
		System.out.println("수정이 완료되었습니다\n");
		cds.pause();
	}
	}
	// 게시판 삭제 메소드
	public void drop() {
		if(boarddao.board().size()==0) {
			System.out.println("삭제할 게시물이 존재하지 않습니다.");
			cds.pause();
		}else {
		show();
		System.out.print("삭제하고 싶은 게시물의 번호를 입력해주세요\n>> ");
		boarddao.board().remove(Integer.parseInt(sc.nextLine()));
		System.out.println("삭제되었습니다.\n");
		cds.pause();
	}

	}
}