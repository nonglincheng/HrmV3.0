package com.nong.dao.provider;

import static com.nong.util.common.HrmConstants.DEPTTABLE;
import static com.nong.util.common.HrmConstants.USERTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.nong.entity.Dept;
import com.nong.entity.User;

public class DeptDynaSqlProvider {
	// 动态插入部门
	public String insertDept(Dept dept) {
		return new SQL() {
			{
				INSERT_INTO(DEPTTABLE);
				if (dept.getName() != null && !"".equals(dept.getName())) {
					VALUES("name", "#{name}");
				}
				if (dept.getRemark() != null && !"".equals(dept.getRemark())) {
					VALUES("remark", "#{remark}");
				}

			}
		}.toString();
	}

	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(DEPTTABLE);
				if (params.get("dept") != null) {
					Dept dept = (Dept) params.get("dept");
					if (dept.getName() != null && !"".equals(dept.getName())) {
						WHERE(" name like concat('%',#{dept.name},'%')"); // like '%#{}%'
					}
					if (dept.getRemark() != null && !"".equals(dept.getRemark())) {
						WHERE(" remark like concat('%',#{dept.remark},'%')");
					}
				}
			}
		}.toString();
		return sql;
	}

	public String count(Map<String, Object> params) {

		return new SQL() {
			{
				SELECT("count(*)");
				FROM(DEPTTABLE);
				if (params.get("user") != null) {
					Dept dept = (Dept) params.get("dept");
					if (dept.getName() != null && !"".equals(dept.getName())) {
						WHERE(" name like concat('%',#{dept.name},'%')"); // like '%#{}%'
					}
					if (dept.getRemark() != null && !"".equals(dept.getRemark())) {
						WHERE(" remark like concat('%',#{dept.remark},'%')");
					}
				}
			}
		}.toString();
	}
	public String updateDept(Dept dept) {
		return new SQL() {
			{
				UPDATE(DEPTTABLE);
				if (dept.getName() != null && !"".equals(dept.getName())) {
					SET("name=#{name}");
				}
				if (dept.getRemark() != null && !"".equals(dept.getRemark())) {
					SET("remark=#{remark}");
				}
				WHERE (" id = #{id}");
			}
		}.toString();
	}

}
