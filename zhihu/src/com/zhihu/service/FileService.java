package com.zhihu.service;

import com.zhihu.dao.pojo.File;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017-04-19.
 */
public interface FileService {

    Integer insert (File file) throws SQLException;
}
