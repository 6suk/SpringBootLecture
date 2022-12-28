package Semiproject;

import java.io.Serializable;

/**
 * 새 테이블 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class Bookmark implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 사용자 테이블. */
	private User user;

	/** 동물 유치원 정보. */
	private Board board;

	/**
	 * 생성자.
	 */
	public Bookmark() {
	}

	/**
	 * 사용자 테이블을 설정합니다..
	 * 
	 * @param user
	 *            사용자 테이블
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 사용자 테이블을 가져옵니다..
	 * 
	 * @return 사용자 테이블
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * 동물 유치원 정보을 설정합니다..
	 * 
	 * @param board
	 *            동물 유치원 정보
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * 동물 유치원 정보을 가져옵니다..
	 * 
	 * @return 동물 유치원 정보
	 */
	public Board getBoard() {
		return this.board;
	}


}
