package Semiproject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 동물 유치원 정보 모델 클래스.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class Board implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** bid. */
	private Integer bid;

	/** 사용자 테이블. */
	private User user;

	/** 동물 유치원 이름. */
	private String title;

	/** thum. */
	private String thum;

	/** addr. */
	private String addr;

	/** 지역. */
	private String area;

	/** 본문. */
	private String content;

	/** 연락처. */
	private String tel;

	/** tag. */
	private String tag;

	/** closeTime. */
	private String closetime;

	/** homepage. */
	private String homepage;

	/** 작성일. */
	private Date modtime;

	/** 관리자추천. */
	private Integer rec;

	/** 삭제여부. */
	private Integer isdel;

	/** 평점. */
	private Double grade;

	/** likeCnt. */
	private Integer likecnt;

	/** viewCnt. */
	private Integer viewcnt;

	/** reCnt. */
	private Integer recnt;

	/** 새 테이블 목록. */
	private Set<Bookmark> bookmarkSet;

	/** 리뷰 댓글 테이블 목록. */
	private Set<Review> reviewSet;

	/**
	 * 생성자.
	 */
	public Board() {
		this.bookmarkSet = new HashSet<Bookmark>();
		this.reviewSet = new HashSet<Review>();
	}

	/**
	 * bid을 설정합니다..
	 * 
	 * @param bid
	 *            bid
	 */
	public void setBid(Integer bid) {
		this.bid = bid;
	}

	/**
	 * bid을 가져옵니다..
	 * 
	 * @return bid
	 */
	public Integer getBid() {
		return this.bid;
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
	 * 동물 유치원 이름을 설정합니다..
	 * 
	 * @param title
	 *            동물 유치원 이름
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 동물 유치원 이름을 가져옵니다..
	 * 
	 * @return 동물 유치원 이름
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * thum을 설정합니다..
	 * 
	 * @param thum
	 *            thum
	 */
	public void setThum(String thum) {
		this.thum = thum;
	}

	/**
	 * thum을 가져옵니다..
	 * 
	 * @return thum
	 */
	public String getThum() {
		return this.thum;
	}

	/**
	 * addr을 설정합니다..
	 * 
	 * @param addr
	 *            addr
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * addr을 가져옵니다..
	 * 
	 * @return addr
	 */
	public String getAddr() {
		return this.addr;
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
	 * 본문을 설정합니다..
	 * 
	 * @param content
	 *            본문
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 본문을 가져옵니다..
	 * 
	 * @return 본문
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 연락처을 설정합니다..
	 * 
	 * @param tel
	 *            연락처
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 연락처을 가져옵니다..
	 * 
	 * @return 연락처
	 */
	public String getTel() {
		return this.tel;
	}

	/**
	 * tag을 설정합니다..
	 * 
	 * @param tag
	 *            tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * tag을 가져옵니다..
	 * 
	 * @return tag
	 */
	public String getTag() {
		return this.tag;
	}

	/**
	 * closeTime을 설정합니다..
	 * 
	 * @param closetime
	 *            closeTime
	 */
	public void setClosetime(String closetime) {
		this.closetime = closetime;
	}

	/**
	 * closeTime을 가져옵니다..
	 * 
	 * @return closeTime
	 */
	public String getClosetime() {
		return this.closetime;
	}

	/**
	 * homepage을 설정합니다..
	 * 
	 * @param homepage
	 *            homepage
	 */
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	/**
	 * homepage을 가져옵니다..
	 * 
	 * @return homepage
	 */
	public String getHomepage() {
		return this.homepage;
	}

	/**
	 * 작성일을 설정합니다..
	 * 
	 * @param modtime
	 *            작성일
	 */
	public void setModtime(Date modtime) {
		this.modtime = modtime;
	}

	/**
	 * 작성일을 가져옵니다..
	 * 
	 * @return 작성일
	 */
	public Date getModtime() {
		return this.modtime;
	}

	/**
	 * 관리자추천을 설정합니다..
	 * 
	 * @param rec
	 *            관리자추천
	 */
	public void setRec(Integer rec) {
		this.rec = rec;
	}

	/**
	 * 관리자추천을 가져옵니다..
	 * 
	 * @return 관리자추천
	 */
	public Integer getRec() {
		return this.rec;
	}

	/**
	 * 삭제여부을 설정합니다..
	 * 
	 * @param isdel
	 *            삭제여부
	 */
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	/**
	 * 삭제여부을 가져옵니다..
	 * 
	 * @return 삭제여부
	 */
	public Integer getIsdel() {
		return this.isdel;
	}

	/**
	 * 평점을 설정합니다..
	 * 
	 * @param grade
	 *            평점
	 */
	public void setGrade(Double grade) {
		this.grade = grade;
	}

	/**
	 * 평점을 가져옵니다..
	 * 
	 * @return 평점
	 */
	public Double getGrade() {
		return this.grade;
	}

	/**
	 * likeCnt을 설정합니다..
	 * 
	 * @param likecnt
	 *            likeCnt
	 */
	public void setLikecnt(Integer likecnt) {
		this.likecnt = likecnt;
	}

	/**
	 * likeCnt을 가져옵니다..
	 * 
	 * @return likeCnt
	 */
	public Integer getLikecnt() {
		return this.likecnt;
	}

	/**
	 * viewCnt을 설정합니다..
	 * 
	 * @param viewcnt
	 *            viewCnt
	 */
	public void setViewcnt(Integer viewcnt) {
		this.viewcnt = viewcnt;
	}

	/**
	 * viewCnt을 가져옵니다..
	 * 
	 * @return viewCnt
	 */
	public Integer getViewcnt() {
		return this.viewcnt;
	}

	/**
	 * reCnt을 설정합니다..
	 * 
	 * @param recnt
	 *            reCnt
	 */
	public void setRecnt(Integer recnt) {
		this.recnt = recnt;
	}

	/**
	 * reCnt을 가져옵니다..
	 * 
	 * @return reCnt
	 */
	public Integer getRecnt() {
		return this.recnt;
	}

	/**
	 * 새 테이블 목록을 설정합니다..
	 * 
	 * @param bookmarkSet
	 *            새 테이블 목록
	 */
	public void setBookmarkSet(Set<Bookmark> bookmarkSet) {
		this.bookmarkSet = bookmarkSet;
	}

	/**
	 * 새 테이블를 추가합니다..
	 * 
	 * @param bookmark
	 *            새 테이블
	 */
	public void addBookmark(Bookmark bookmark) {
		this.bookmarkSet.add(bookmark);
	}

	/**
	 * 새 테이블 목록을 가져옵니다..
	 * 
	 * @return 새 테이블 목록
	 */
	public Set<Bookmark> getBookmarkSet() {
		return this.bookmarkSet;
	}

	/**
	 * 리뷰 댓글 테이블 목록을 설정합니다..
	 * 
	 * @param reviewSet
	 *            리뷰 댓글 테이블 목록
	 */
	public void setReviewSet(Set<Review> reviewSet) {
		this.reviewSet = reviewSet;
	}

	/**
	 * 리뷰 댓글 테이블를 추가합니다..
	 * 
	 * @param review
	 *            리뷰 댓글 테이블
	 */
	public void addReview(Review review) {
		this.reviewSet.add(review);
	}

	/**
	 * 리뷰 댓글 테이블 목록을 가져옵니다..
	 * 
	 * @return 리뷰 댓글 테이블 목록
	 */
	public Set<Review> getReviewSet() {
		return this.reviewSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
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
		Board other = (Board) obj;
		if (bid == null) {
			if (other.bid != null) {
				return false;
			}
		} else if (!bid.equals(other.bid)) {
			return false;
		}
		return true;
	}

}
