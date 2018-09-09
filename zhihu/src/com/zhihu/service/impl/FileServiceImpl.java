package com.zhihu.service.impl;

import com.zhihu.dao.FileDao;
import com.zhihu.dao.pojo.File;
import com.zhihu.service.FileService;
import com.zhihu.util.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017-04-19.
 */
public class FileServiceImpl implements FileService{

    Connection conn = JdbcUtils.getInstance().getDateBase();
    FileDao fileDao = FileDao.getInstance();
    @Override
    public Integer insert(File file) throws SQLException{
        return fileDao.insert(conn,file);
    }
}
