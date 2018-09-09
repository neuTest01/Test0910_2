package com.zhihu.service.impl;

import com.zhihu.dao.StatisticsDao;
import com.zhihu.dao.pojo.Statistics;
import com.zhihu.service.StatisticsService;
import com.zhihu.util.JdbcUtils;

import java.sql.Connection;
import java.util.List;

public class StatisticsServiceImpl implements StatisticsService{

    StatisticsDao statisticsDao = new StatisticsDao();
    Connection connection = JdbcUtils.getInstance().getDateBase();
    @Override
    public List<Statistics> getStatisticsList(Object[] objects) {
        return statisticsDao.getStatisticsList(connection,objects);
    }
}
