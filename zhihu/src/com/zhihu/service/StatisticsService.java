package com.zhihu.service;

import com.zhihu.dao.pojo.Statistics;

import java.util.List;

public interface StatisticsService {

    List<Statistics> getStatisticsList(Object[] objects);
}
