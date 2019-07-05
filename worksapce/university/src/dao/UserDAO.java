package dao;

import entity.User;

public interface UserDAO {
	/**
	 * 
	 * @param user 添加的user
	 * @return int 0表示失败,1表示添加成功
	 */
	int addUser(User user);
	
	
	/**
	 * 改变用户状态来屏蔽用户
	 * @param 用户的id
	 * @return 0失败，1成功
	 */
	int deleteUser(int user_id);
	
	int UpdateUser(int id,) 
	

}
