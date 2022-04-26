package com.dorm.dao;

import com.dorm.bean.Record;

import java.util.List;

public interface AbsenceManageDao {
    List<Record> findAll(int id);

}
