package com.zdh.back.dao;

import java.util.Map;

import com.zdh.back.entities.AppUserInfo;
import com.zdh.core.base.dao.BaseDao;


public interface IUserDao extends BaseDao<AppUserInfo>{
	
	public Map getCustomerCode(String phone,String code);
}
