package himedia.myhome.vo;

import java.util.Date;

public class UserVo {
	// 필드
	private Long no;
	private String name;
	private String pass;
	private String email;
	private Character gender;
	private Date date;
	
	// 기본 생성자
	// 그저 null 값으로 초기화만 함
	public UserVo() {
		super();
		this.no = null;
		this.name = null;
		this.pass = null;
		this.email = null;
		this.gender = null;
		this.date = null;
	}
	
	// 자동으로 생성되는 no와 Date 값을 제외한 4가지 항목만 초기화하는 함수
	public UserVo(String name, String pass, String email, Character gender) {
		super();
		this.name = name;
		this.pass = pass;
		this.email = email;
		this.gender = gender;

	}
	
	// 모든 필드를 초기화하는 생성자
	public UserVo(Long no, String name, String pass, String email, Character gender, Date date) {
		super();
		this.no = no;
		this.name = name;
		this.pass = pass;
		this.email = email;
		this.gender = gender;
		this.date = date;
	}

	
	public UserVo(String name, String pass, String email, String gender) {
		super();
		this.name = name;
		this.pass = pass;
		this.email = email;
		this.gender = gender.charAt(0);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
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
		return "UserVo [no=" + no + ", name=" + name + ", pass=" + pass + ", email=" + email + ", gender=" + gender
				+ ", date=" + date + "]";
	}
}
