package com.dorm.dao.impl;

import com.dorm.bean.Record;
import com.dorm.dao.AbsenceManageDao;
import com.dorm.util.ConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 缺勤管理
 */
public class AbsenceManageDaoImple implements AbsenceManageDao {
    private JdbcTemplate jdbcTemplate =  new JdbcTemplate(ConnectionFactory.getDataSource());
    /**
     * 获取缺勤列表
     * @param id
     * @return
     */
    @Override
    public List<Record> findAll(int id) {
        //日期、学号、姓名、性别、宿舍楼、寝室、备注、操作
        String sql = "select,data,remark users from tb_record ";
        jdbcTemplate.query();
        return null;
    }
}
