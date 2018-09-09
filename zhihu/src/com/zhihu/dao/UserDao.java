package com.zhihu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.zhihu.dao.pojo.User;
import com.zhihu.util.SqlSendUtil;

public class UserDao {
	
	public int selectUserCount(Connection conn){
		int count = Integer.parseInt(SqlSendUtil.selectCount(conn, "SELECT count(*) FROM USER_ACCOUNT").toString());
		return count;
	}
	
	public User selectUserInfor(Connection conn , User user){
		
		StringBuffer sbsql = new StringBuffer("SELECT ");
		sbsql.append("u.user_id as userId,");
		sbsql.append("u.user_name as userName, ");
		sbsql.append("u.user_gender as userGender,");
		sbsql.append("u.user_content as userContent, ");
		sbsql.append("u.user_address as userAddress, ");
		sbsql.append("u.user_industry as userIndustry, ");
		sbsql.append("u.user_image_id as userImageId, ");
		sbsql.append("u.user_cover_id as userCoverId, ");
		sbsql.append("u.user_account as userAccount, ");
		sbsql.append("u.user_occupation as userOccupation, ");
		sbsql.append("u.user_birthday as userBirthday, ");
		sbsql.append("u.user_introduce as userIntroduce, ");
		sbsql.append("u.user_email as userEmail, ");
		sbsql.append("u.user_phone as userPhone, ");
		sbsql.append("f.file_url as imageUrl, ");
		sbsql.append("u.user_education as userEducation ");
		sbsql.append("FROM USER_ACCOUNT u left join FILETABLE f on u.user_image_id=f.file_id where 1=1 ");
	    if(user.getUserId() != null){
            sbsql.append("and user_id = ");
            sbsql.append(user.getUserId());
		}
		if(user.getUserName() != null){
            sbsql.append("and user_name = '");
            sbsql.append(user.getUserName());
            sbsql.append("' ");
		}
		if(user.getUserGender() != null){
			sbsql.append("and user_gender = ");
            sbsql.append(user.getUserGender());
		}
		if(user.getUserContent() != null){
			sbsql.append("and user_content = '");
            sbsql.append(user.getUserContent());
            sbsql.append("' ");
		}
		if(user.getUserAddress() != null){
			sbsql.append("and user_address = '");
            sbsql.append(user.getUserAddress());
            sbsql.append("' ");
		}
		if(user.getUserIndustry() != null){
			sbsql.append("and user_industry = '");
            sbsql.append(user.getUserIndustry());
            sbsql.append("' ");
		}
		if(user.getUserImageId() != null){
			sbsql.append("and user_image_id = ");
            sbsql.append(user.getUserImageId());
		}
		if(user.getUserCoverId() != null){
			sbsql.append("and user_cover_id = ");
            sbsql.append(user.getUserCoverId());
		}
		if(user.getUserAccount() != null){
			sbsql.append("and user_account = '");
            sbsql.append(user.getUserAccount());
            sbsql.append("' ");
		}
		if(user.getUserPassword() != null){
			sbsql.append("and user_password = '");
            sbsql.append(user.getUserPassword());
            sbsql.append("' ");
		}
		System.out.println(sbsql.toString());
		User user2 = (User) SqlSendUtil.selectOne(conn, sbsql.toString(), null, User.class);
		return user2;
	}
	
	public List<User> selectUserList(Connection conn ,User user){
		
		StringBuffer sbsql = new StringBuffer("SELECT ");
		sbsql.append("user_id as userId,");
		sbsql.append("user_name as userName, ");
		sbsql.append("user_gender as userGender,");
		sbsql.append("user_content as userContent, ");
		sbsql.append("user_address as userAddress, ");
		sbsql.append("user_industry as userIndustry, ");
		sbsql.append("user_image_id as userImageId, ");
		sbsql.append("user_cover_id as userCoverId, ");
		sbsql.append("user_account as userAccount, ");
		sbsql.append("user_password as userPassword ");
		sbsql.append("FROM user where 1=1 ");
		
	    if(user.getUserId() != null){
            sbsql.append("and user_id = ");
            sbsql.append(user.getUserId());
		}
		if(user.getUserName() != null){
            sbsql.append("and user_name = '");
            sbsql.append(user.getUserName());
            sbsql.append("' ");
		}
		if(user.getUserGender() != null){
			sbsql.append("and user_gender = ");
            sbsql.append(user.getUserGender());
		}
		if(user.getUserContent() != null){
			sbsql.append("and user_content = '");
            sbsql.append(user.getUserContent());
            sbsql.append("' ");
		}
		if(user.getUserAddress() != null){
			sbsql.append("and user_address = '");
            sbsql.append(user.getUserAddress());
            sbsql.append("' ");
		}
		if(user.getUserIndustry() != null){
			sbsql.append("and user_industry = '");
            sbsql.append(user.getUserIndustry());
            sbsql.append("' ");
		}
		if(user.getUserImageId() != null){
			sbsql.append("and user_image_id = ");
            sbsql.append(user.getUserImageId());
		}
		if(user.getUserCoverId() != null){
			sbsql.append("and user_cover_id = ");
            sbsql.append(user.getUserCoverId());
		}
		if(user.getUserAccount() != null){
			sbsql.append("and user_account = '");
            sbsql.append(user.getUserAccount());
            sbsql.append("' ");
		}
		if(user.getUserPassword() != null){
			sbsql.append("and user_password = '");
            sbsql.append(user.getUserPassword());
            sbsql.append("' ");
		}
		System.out.println(sbsql.toString());
		List<User> list = (List<User>) SqlSendUtil.selectList(conn, sbsql.toString(), null, User.class);
		return list;
	}
	
	public void updateUser(Connection conn , User user) throws SQLException{
		StringBuffer sbsql = new StringBuffer("UPDATE USER_ACCOUNT SET");
		if(user.getUserGender() != null){
			sbsql.append(" user_gender = ");
            sbsql.append(user.getUserGender());
            sbsql.append(",");
		}
		if(user.getUserContent() != null){
			sbsql.append(" user_content = '");
            sbsql.append(user.getUserContent());
            sbsql.append("' ");
            sbsql.append(",");
		}
		if(user.getUserAddress() != null){
			sbsql.append(" user_address = '");
            sbsql.append(user.getUserAddress());
            sbsql.append("' ");
            sbsql.append(",");
		}
		if(user.getUserIndustry() != null){
			sbsql.append(" user_industry = '");
            sbsql.append(user.getUserIndustry());
            sbsql.append("' ");
            sbsql.append(",");
		}
		if(user.getUserImageId() != null){
			sbsql.append(" user_image_id = ");
            sbsql.append(user.getUserImageId());
            sbsql.append(",");
		}
		if(user.getUserCoverId() != null){
			sbsql.append(" user_cover_id = ");
            sbsql.append(user.getUserCoverId());
            sbsql.append(",");
		}
		if(user.getUserOccupation() != null){
			sbsql.append(" user_occupation = '");
            sbsql.append(user.getUserOccupation());
            sbsql.append("' ");
            sbsql.append(",");
		}
		if(user.getUserEducation() != null){
			sbsql.append(" user_education = '");
            sbsql.append(user.getUserEducation());
            sbsql.append("' ");
            sbsql.append(",");
		}
		if(user.getUserBirthday() != null){
			sbsql.append(" user_birthday = '");
            sbsql.append(user.getUserBirthday());
            sbsql.append("' ");
            sbsql.append(",");
		}
		if(user.getUserIntroduce() != null){
			sbsql.append(" user_introduce = '");
            sbsql.append(user.getUserIntroduce());
            sbsql.append("' ");
            sbsql.append(",");
		}
		if(user.getUserEmail() != null){
			sbsql.append(" user_email = '");
            sbsql.append(user.getUserEmail());
            sbsql.append("' ");
            sbsql.append(",");
		}
		if(user.getUserPhone() != null){
			sbsql.append(" user_phone = '");
            sbsql.append(user.getUserPhone());
            sbsql.append("' ");
            sbsql.append(",");
		}
		sbsql.deleteCharAt(sbsql.length()-1);
		sbsql.append(" WHERE user_id = " + user.getUserId());
		SqlSendUtil.update(conn, sbsql.toString(), null);
		
	}
	
	public int insertUser(Connection conn , User user) throws SQLException{
		int result;
		Object[] param = new Object[9];
		String sbsql = "INSERT INTO USER_ACCOUNT (USER_ID,user_name,"
				+ "user_gender,user_content,user_address,user_industry,"
				+ "user_image_id,user_cover_id,user_account,user_password) VALUES (USERSEQUENCES.nextval,?,?,?,?,?,?,?,?,?)";
		
		if(user.getUserName() != null){
		    param[0] = user.getUserName();
		}
		if(user.getUserGender() != null){
			param[1] = user.getUserGender();
		}
		if(user.getUserContent() != null){
			param[2] = user.getUserContent();
		}
		if(user.getUserAddress() != null){
			param[3] = user.getUserAddress();
		}
		if(user.getUserIndustry() != null){
			param[4] = user.getUserIndustry();
		}
		if(user.getUserImageId() != null){
			param[5] = user.getUserImageId();
		}
		if(user.getUserCoverId() != null){
			param[6] = user.getUserCoverId();
		}
		if(user.getUserAccount() != null){
			param[7] = user.getUserAccount();
		}
		if(user.getUserPassword() != null){
			param[8] = user.getUserPassword();
		}
		result = SqlSendUtil.update(conn, sbsql, param);
		return result;
	}
	
	public int delete(Connection conn , User user) throws SQLException{
		int result;
		StringBuffer sbsql = new StringBuffer("DELETE FROM USER_ACCOUNT WHERE 1 = 1");
		if(user.getUserId() != null){
            sbsql.append(" and user_id = ");
            sbsql.append(user.getUserId());
		}
		if(user.getUserName() != null){
            sbsql.append(" and user_name = '");
            sbsql.append(user.getUserName());
            sbsql.append("' ");
		}
		if(user.getUserGender() != null){
			sbsql.append(" and user_gender = ");
            sbsql.append(user.getUserGender());
		}
		if(user.getUserContent() != null){
			sbsql.append(" and user_content = '");
            sbsql.append(user.getUserContent());
            sbsql.append("' ");
		}
		if(user.getUserAddress() != null){
			sbsql.append(" and user_address = '");
            sbsql.append(user.getUserAddress());
            sbsql.append("' ");
		}
		if(user.getUserIndustry() != null){
			sbsql.append(" and user_industry = '");
            sbsql.append(user.getUserIndustry());
            sbsql.append("' ");
		}
		if(user.getUserImageId() != null){
			sbsql.append(" and user_image_id = ");
            sbsql.append(user.getUserImageId());
		}
		if(user.getUserCoverId() != null){
			sbsql.append(" and user_cover_id = ");
            sbsql.append(user.getUserCoverId());
		}
		if(user.getUserAccount() != null){
			sbsql.append(" and user_account = '");
            sbsql.append(user.getUserAccount());
            sbsql.append("' ");
		}
		if(user.getUserPassword() != null){
			sbsql.append(" and user_password = '");
            sbsql.append(user.getUserPassword());
            sbsql.append("' ");
		}
		System.out.println(sbsql.toString());
		result = SqlSendUtil.update(conn, sbsql.toString(), null);
		return result;
	}
	
	public int modifyPassword(Connection conn,User user) throws SQLException{
//		Object[] param = new Object[2];
		String sbsql = "update USER_ACCOUNT set user_password='"+user.getUserPassword()+"' where user_account='"+user.getUserAccount()+"'";
		/*param[0]=user.getUserPassword();
		param[0]=user.getUserAccount();*/
		return SqlSendUtil.update(conn, sbsql, null);
	}
	
	public User getPasswordByAccount(Connection conn,String account){
		String sbsql = "select user_password as userPassword from USER_ACCOUNT where user_account="+account;
		User user = (User) SqlSendUtil.selectOne(conn, sbsql, null, User.class);
		return user;
	}

	public void updateAttention(Connection conn,User user) throws SQLException{

		String[] topicList = user.getTopicList();
		if(topicList!=null&&topicList.length>0){
			String delsql = "delete from attention where user_id ="+user.getUserId();
			SqlSendUtil.update(conn, delsql, null);
			for (String topicId:topicList) {
				String inssql = "insert into attention(id,user_id,topic_id,creat_time) values(ATSEQUNCES.nextval,"+user.getUserId()+","+topicId+",sysdate)";
				SqlSendUtil.update(conn, inssql, null);
			}
		}
	}

	public void insertStatistics(Connection connection,Object[] objects) throws SQLException{
		String sql = "insert into login_statistics(ID,user_id,login_address,login_time) values(LOGINSEQUENCES.nextval,?,?,sysdate)";
		SqlSendUtil.update(connection,sql,objects);
	}
	
	public List<User> getUserByPage(Connection conn,int currentPage,int pageSize){
		int start = (currentPage-1)*pageSize+1;
		String sql = "select user_id as userId, user_name as userName,user_birthday as userBirthday,"+
				"user_content as userContent,user_address as userAddress,user_education as userEducation,"+
				"user_account as userAccount from USER_ACCOUNT where  ROWNUM >="+start+" and ROWNUM <="+pageSize;
		System.out.println(sql);
		List<User> list = (List<User>) SqlSendUtil.selectList(conn, sql, null, User.class);
		return list;
	}
	
	public long total(Connection conn){
		String sql = "select count(*) from USER_ACCOUNT";
		long count = Long.valueOf(String.valueOf(SqlSendUtil.selectCount(conn, sql)));
		//long count = (long)SqlSendUtil.selectCount(conn, sql);
		System.out.println("总条数"+count);
		return count;
	}
	
	public List<User> getUserListToExport(Connection conn){
		String sql = "select user_name as userName,user_birthday as userBirthday,"+
				"user_content as userContent,user_address as userAddress,user_education as userEducation,"+
				"user_account as userAccount from USER_ACCOUNT";
		List<User> list = (List<User>) SqlSendUtil.selectList(conn, sql, null, User.class);
		return list;
	}
	
}
