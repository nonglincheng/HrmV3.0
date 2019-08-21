package com.nong.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import static com.nong.util.common.HrmConstants.DOCUMENTTABLE;
import static com.nong.util.common.HrmConstants.USERTABLE;

import java.util.Map;

import com.nong.entity.Document;
import com.nong.entity.User;;

public class DocumentDynaSqlProvider {

	//动态插入
	public String insertDocument(Document document) {
		return new SQL() {
			{
				INSERT_INTO(DOCUMENTTABLE);
				if(document.getTitle()!=null && !"".equals(document.getTitle())) {
					VALUES("title","#{title}");
				}
				if(document.getFileName()!=null && !"".equals(document.getFileName())) {
					VALUES("fileName","#{fileName}");
				}
				if(document.getRemark()!=null && !"".equals(document.getRemark())) {
					VALUES("remark","#{remark}");
				}
				if(document.getUser()!=null && !"".equals(document.getUser())) {
					VALUES("user_id","#{user.id}");
				}
			}
		}.toString();
	}
	public String updateDocument(Document document) {
		return new SQL() {
			{
				UPDATE(DOCUMENTTABLE);
				if(document.getTitle()!=null) {
					SET("title=#{title}");
				}
				if(document.getFileName()!=null) {
					SET("fileName=#{fileName}");
				}
				if(document.getRemark()!=null) {
					SET("remark=#{remark}");
				}
				if(document.getUser()!=null) {
					SET("user_id=#{user.id}");
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
					Document document = (Document)params.get("document");
					if(document.getTitle()!=null && !"".equals(document.getTitle())) {
						WHERE(" username like concat('%',#{document.title},'%')");	//like '%#{}%'
					}
					if(document.getFileName()!=null && !"".equals(document.getFileName())) {
						WHERE(" status like concat('%',#{document.fileName},'%')");
					}
					if(document.getRemark()!=null && !"".equals(document.getRemark())) {
						WHERE(" username like concat('%',#{document.remark},'%')");	//like '%#{}%'
					}
					if(document.getUser()!=null && !"".equals(document.getUser())) {
						WHERE(" status like concat('%',#{user.id},'%')");
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
				FROM(DOCUMENTTABLE);
				Document document = (Document)params.get("document");
				if(document.getTitle()!=null && !"".equals(document.getTitle())) {
					WHERE(" username like concat('%',#{document.title},'%')");	//like '%#{}%'
				}
				if(document.getFileName()!=null && !"".equals(document.getFileName())) {
					WHERE(" status like concat('%',#{document.fileName},'%')");
				}
				if(document.getRemark()!=null && !"".equals(document.getRemark())) {
					WHERE(" username like concat('%',#{document.remark},'%')");	//like '%#{}%'
				}
				if(document.getUser()!=null && !"".equals(document.getUser())) {
					WHERE(" status like concat('%',#{user.id},'%')");
				}
			}
		}.toString();
		return sql;
	}
}
