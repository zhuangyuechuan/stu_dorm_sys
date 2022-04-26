package com.dorm.service;

import com.dorm.bean.Record;

import java.util.List;

public interface AbsenceManageService {
    List<Record> findAll(int id);
}
