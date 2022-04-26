package com.dorm.service.impl;

import java.util.List;

import com.dorm.bean.DormBuild;
import com.dorm.dao.*;
import com.dorm.dao.impl.DormBuildDaoImpl;
import com.dorm.service.DormBuildService;

public class DormBuildServiceImpl implements DormBuildService {
	//实现了接口，但是需要重写接口里面的方法
	//服务层service调Dao层
	private DormBuildDao dormBuildDao = new DormBuildDaoImpl();
		@Override
		public DormBuild findByName(String name) {
			return dormBuildDao.findByName(name);
		}
		
		@Override
		public void save(DormBuild build) {
			dormBuildDao.save(build);
		}
		
		@Override
		public List<DormBuild> find(){
			return dormBuildDao.find();
		}

		@Override
		public DormBuild findById(Integer id) {
			return dormBuildDao.findById(id);
		}

		@Override
		public void update(DormBuild dormBuild) {
			dormBuildDao.update(dormBuild);
		}

		@Override
		public List<DormBuild> findByUserId(Integer id) {
			
			return dormBuildDao.findByUserId(id);
		}

		@Override
		public void deleteByUserId(Integer id) {
			dormBuildDao.deleteByUserId(id);
		}

		@Override
		public void saveManagerAndBuild(Integer id, String[] dormBuildIds) {
			dormBuildDao.saveManagerAndBuild(id, dormBuildIds);
			
		}

		@Override
		public List<DormBuild> findAll() {
			
			return dormBuildDao.findAll();
		}
	}

