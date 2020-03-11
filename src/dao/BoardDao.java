package dao;

import java.util.ArrayList;

import data.Database;
import vo.BoardVO;

public class BoardDao {

	// 싱글톤 패턴
	private static BoardDao instance;

	private BoardDao() {
	}

	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}

	Database database = Database.getInstance();

	// 게시판 삽입 메소드
	public void insertboard(BoardVO board) {
		database.tb_board.add(board);
	}

	// 데이터베이스 리턴
	public ArrayList<BoardVO> board() {
		return database.tb_board;
	}
}
