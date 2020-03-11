package dao;

import java.util.ArrayList;

import data.Database;
import vo.BoardVO;
import vo.QboardVO;

public class QboardDao {
	// 싱글톤 패턴
	private static QboardDao instance;

	private QboardDao() {
	}

	public static QboardDao getInstance() {
		if (instance == null) {
			instance = new QboardDao();
		}
		return instance;
	}

	Database database = Database.getInstance();

	// 게시판 삽입 메소드
	public void insertboard(QboardVO board) {
		database.tb_Qboard.add(board);
	}

	// 데이터베이스 리턴
	public ArrayList<QboardVO> qboard() {
		return database.tb_Qboard;
	}
}
