package dao;

import entity.User;

public interface UserDAO {
	/**
	 * 
	 * @param user ��ӵ�user
	 * @return int 0��ʾʧ��,1��ʾ��ӳɹ�
	 */
	int addUser(User user);
	
	
	/**
	 * �ı��û�״̬�������û�
	 * @param �û���id
	 * @return 0ʧ�ܣ�1�ɹ�
	 */
	int deleteUser(int user_id);
	
	int UpdateUser(int id,) 
	

}
