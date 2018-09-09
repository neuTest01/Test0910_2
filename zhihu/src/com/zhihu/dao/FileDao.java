package com.zhihu.dao;

import com.zhihu.dao.pojo.File;
import com.zhihu.dao.pojo.MyReply;
import com.zhihu.util.JdbcUtils;
import com.zhihu.util.SqlSendUtil;

import java.sql.*;
import java.util.List;

import oracle.jdbc.internal.OracleResultSet;

/**
 * Created by Administrator on 2017-04-19.
 */
public class FileDao {

    public static final FileDao getInstance(){
        return new FileDao();
    }

    private FileDao(){}

    public Integer insert (Connection connection, File file) throws SQLException{
    	Integer id = 0;
        
        String sql = "insert into FILETABLE(file_id,file_name,file_real_name,file_url,file_type,file_create_time) " +
        		"values (FILESEQUENCE.nextval,?,?,?,?,sysdate)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,file.getFileName());
        ps.setString(2,file.getFileRealName());
        ps.setString(3,file.getFileUrl());
        ps.setString(4,file.getFileType());
        ps.executeUpdate();
        //再执行查询  
        ps = connection.prepareStatement("select filesequence.currval from dual"); 
        System.out.println(ps.toString());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
        	id = rs.getInt(1);
        }
        
        return id;
    }
}
