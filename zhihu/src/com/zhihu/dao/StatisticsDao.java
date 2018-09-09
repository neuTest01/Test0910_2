package com.zhihu.dao;

import com.zhihu.dao.pojo.Statistics;
import com.zhihu.util.SqlSendUtil;

import java.sql.Connection;
import java.util.List;

public class StatisticsDao {

    public List<Statistics> getStatisticsList(Connection connection, Object[] objects){
        String sql  = "select login_address loginAddress,COUNT(login_address) count from login_statistics where user_id=? GROUP BY login_address";
        return  (List<Statistics>)SqlSendUtil.selectList(connection,sql,objects,Statistics.class);
    }
}
