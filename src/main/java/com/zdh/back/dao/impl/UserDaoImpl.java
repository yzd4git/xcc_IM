package com.zdh.back.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zdh.back.dao.IUserDao;
import com.zdh.back.entities.AppUserInfo;
import com.zdh.core.base.dao.impl.BaseDaoImpl;
@Repository
public class UserDaoImpl extends BaseDaoImpl<AppUserInfo> implements IUserDao {

	@Override
	public Map getCustomerCode(String phone, String code) {
		String sql="SELECT phone,verification_code,DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%s')create_time FROM `t_app_verfication_code` where phone='"+phone+"' and verification_code='"+code+"'";
		return this.findForMapBySql(sql);
	}

}
