package vo;

import java.sql.Date;

/**
 * vo 클래스
 * - sql 데이터를 보고 작성됨
 * - 상속해야 할 이유가 없으므로 일단 final로 막아둠
 * */
public final class GuestBookVo {
	// 필드
	private Long no;
	private String name;
	private String pass;
	private String content;
	private Date date;
	
	// 생성자
	/**
	 * vo의 기본 생성자(0개 파라미터)
	 * */
	public GuestBookVo() {
		
	}
	
	/**
	 * sequence가 존재하고,
	 * Date는 현재 시간으로 넣어줄 계획이다. 
	 * no를 제외한 다른 요소를 초기화 할 수 있는 생성자(3개 파라미터)
	 * */
	public GuestBookVo(String name, String pass, String content) {
		super();
		this.name = name;
		this.pass = pass;
		this.content = content;
	}
	
	/**
	 * 전체 요소를 초기화 할 수 있는 생성자(5개 파라미터)
	 * */
	public GuestBookVo(Long no, String name, String pass, String content, Date date) {
		super();
		this.no = no;
		this.name = name;
		this.pass = pass;
		this.content = content;
		this.date = date;
	}

	// Getters / Setters
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// toString
	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", pass=" + pass + ", content=" + content + ", date=" + date
				+ "]";
	}
}
