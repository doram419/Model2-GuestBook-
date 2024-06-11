package oracle;

import java.util.List;

import vo.GuestBookVo;

public interface OracleDAO {

	/**
	 * List 반환
	 * */
	public List<GuestBookVo> getList();
	/**
	 * 성공/실패 여부를 알려주기 위한 boolean으로 함수 구축
	 * */
	public boolean insert(String name, String pass, String Content);
	public boolean delete(Long delNo);
}
