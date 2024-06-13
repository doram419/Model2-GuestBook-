package himedia.myhome.dao;

import java.util.List;

import himedia.myhome.vo.UserVo;

public interface UsersDao {
	public List<UserVo> getList();
	public boolean insert(UserVo userVo);	// INSERT
	public boolean update(UserVo userVo);	// UPDATE
	public boolean delete(Long no);	// DELETE
	public UserVo getUserByIdAndPassword(String email, 
			String password);	//로그인을 위해서 만든 메서드
	
}
