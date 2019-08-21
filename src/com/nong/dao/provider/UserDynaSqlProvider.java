package com.nong.dao.provider;

import static com.nong.util.common.HrmConstants.USERTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.nong.entity.User;

public class UserDynaSqlProvider {

	//动态插入
	public String insertUser(User user) {
		
		return new SQL() {
			{
				INSERT_INTO(USERTABLE);
				if(user.getUsername()!=null && !"".equals(user.getUsername())) {
					VALUES("username","#{username}");
				}
				if(user.getStatus()!=null && !"".equals(user.getStatus())) {
					VALUES("status","#{status}");
				}
				if(user.getPassword()!=null && !"".equals(user.getPassword())) {
					VALUES("password","#{password}");
				}
				if(user.getLoginname()!=null && !"".equals(user.getLoginname())) {
					VALUES("loginname","#{loginname}");
				}
				
			}
			
		}.toString();
	}
	
	//动态更新
	public String updateUser(User user) {
		return new SQL() {
			{
				UPDATE(USERTABLE);
				if(user.getUsername()!=null) {
					SET(" username = #{username}");
				}
				if(user.getPassword()!=null) {
					SET(" password = #{password}");
				}
				if(user.getStatus()!=null) {
					SET(" status = #{status}");
				}
				if(user.getCreateDate()!=null) {
					SET(" createDate = #{createDate}");
				}
				WHERE (" id = #{id}");
			}
		}.toString();
	}
	//动态查询数据总量
	public String count(Map<String,Object> params) {
		
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(USERTABLE);
				if(params.get("user")!=null) {
					User user = (User)params.get("user");
					if(user.getUsername()!=null && !"".equals(user.getUsername())) {
						WHERE(" username like concat('%',#{user.username},'%')");	//like '%#{}%'
					}
					if(user.getStatus()!=null && !"".equals(user.getStatus())) {
						WHERE(" status like concat('%',#{user.status},'%')");
					}
				}
			}
		}.toString();
	}
	//分页的动态查询
	public String selectWithParam(Map<String,Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(USERTABLE);
				if(params.get("user")!=null) {
					User user = (User)params.get("user");
					if(user.getUsername()!=null && !"".equals(user.getUsername())) {
						WHERE(" username like concat('%',#{user.username},'%')");	//like '%#{}%'
					}
					if(user.getStatus()!=null && !"".equals(user.getStatus())) {
						WHERE(" status like concat('%',#{user.status},'%')");
					}
				}
			}
		}.toString();
		return sql;
	}
	
}
