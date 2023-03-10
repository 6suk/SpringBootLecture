import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 사용자 테이블 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class UserSemiproject implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 아이디. */
	private String uid;

	/** 패스워드. */
	private String pwd;

	/** 이름. */
	private String uname;

	/** 이메일. */
	private String email;

	/** 지역. */
	private String area;

	/** 가입일. */
	private Date regdate;

	/** 탈퇴여부. */
	private Integer isdel;

	/** 동물 유치원 정보 목록. */
	private Set<BoardSemiproject> boardSet;

	/** 새 테이블 목록. */
	private Set<BookmarkSemiproject> bookmarkSet;

	/** 리뷰 댓글 테이블 목록. */
	private Set<ReviewSemiproject> reviewSet;

	/**
	 * 생성자.
	 */
	public UserSemiproject() {
		this.boardSet = new HashSet<BoardSemiproject>();
		this.bookmarkSet = new HashSet<BookmarkSemiproject>();
		this.reviewSet = new HashSet<ReviewSemiproject>();
	}

	/**
	 * 아이디을 설정합니다..
	 * 
	 * @param uid
	 *            아이디
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * 아이디을 가져옵니다..
	 * 
	 * @return 아이디
	 */
	public String getUid() {
		return this.uid;
	}

	/**
	 * 패스워드을 설정합니다..
	 * 
	 * @param pwd
	 *            패스워드
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 패스워드을 가져옵니다..
	 * 
	 * @return 패스워드
	 */
	public String getPwd() {
		return this.pwd;
	}

	/**
	 * 이름을 설정합니다..
	 * 
	 * @param uname
	 *            이름
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * 이름을 가져옵니다..
	 * 
	 * @return 이름
	 */
	public String getUname() {
		return this.uname;
	}

	/**
	 * 이메일을 설정합니다..
	 * 
	 * @param email
	 *            이메일
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 이메일을 가져옵니다..
	 * 
	 * @return 이메일
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 지역을 설정합니다..
	 * 
	 * @param area
	 *            지역
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 지역을 가져옵니다..
	 * 
	 * @return 지역
	 */
	public String getArea() {
		return this.area;
	}

	/**
	 * 가입일을 설정합니다..
	 * 
	 * @param regdate
	 *            가입일
	 */
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	/**
	 * 가입일을 가져옵니다..
	 * 
	 * @return 가입일
	 */
	public Date getRegdate() {
		return this.regdate;
	}

	/**
	 * 탈퇴여부을 설정합니다..
	 * 
	 * @param isdel
	 *            탈퇴여부
	 */
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	/**
	 * 탈퇴여부을 가져옵니다..
	 * 
	 * @return 탈퇴여부
	 */
	public Integer getIsdel() {
		return this.isdel;
	}

	/**
	 * 동물 유치원 정보 목록을 설정합니다..
	 * 
	 * @param boardSet
	 *            동물 유치원 정보 목록
	 */
	public void setBoardSet(Set<BoardSemiproject> boardSet) {
		this.boardSet = boardSet;
	}

	/**
	 * 동물 유치원 정보를 추가합니다..
	 * 
	 * @param board
	 *            동물 유치원 정보
	 */
	public void addBoard(BoardSemiproject board) {
		this.boardSet.add(board);
	}

	/**
	 * 동물 유치원 정보 목록을 가져옵니다..
	 * 
	 * @return 동물 유치원 정보 목록
	 */
	public Set<BoardSemiproject> getBoardSet() {
		return this.boardSet;
	}

	/**
	 * 새 테이블 목록을 설정합니다..
	 * 
	 * @param bookmarkSet
	 *            새 테이블 목록
	 */
	public void setBookmarkSet(Set<BookmarkSemiproject> bookmarkSet) {
		this.bookmarkSet = bookmarkSet;
	}

	/**
	 * 새 테이블를 추가합니다..
	 * 
	 * @param bookmark
	 *            새 테이블
	 */
	public void addBookmark(BookmarkSemiproject bookmark) {
		this.bookmarkSet.add(bookmark);
	}

	/**
	 * 새 테이블 목록을 가져옵니다..
	 * 
	 * @return 새 테이블 목록
	 */
	public Set<BookmarkSemiproject> getBookmarkSet() {
		return this.bookmarkSet;
	}

	/**
	 * 리뷰 댓글 테이블 목록을 설정합니다..
	 * 
	 * @param reviewSet
	 *            리뷰 댓글 테이블 목록
	 */
	public void setReviewSet(Set<ReviewSemiproject> reviewSet) {
		this.reviewSet = reviewSet;
	}

	/**
	 * 리뷰 댓글 테이블를 추가합니다..
	 * 
	 * @param review
	 *            리뷰 댓글 테이블
	 */
	public void addReview(ReviewSemiproject review) {
		this.reviewSet.add(review);
	}

	/**
	 * 리뷰 댓글 테이블 목록을 가져옵니다..
	 * 
	 * @return 리뷰 댓글 테이블 목록
	 */
	public Set<ReviewSemiproject> getReviewSet() {
		return this.reviewSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserSemiproject other = (UserSemiproject) obj;
		if (uid == null) {
			if (other.uid != null) {
				return false;
			}
		} else if (!uid.equals(other.uid)) {
			return false;
		}
		return true;
	}

}
