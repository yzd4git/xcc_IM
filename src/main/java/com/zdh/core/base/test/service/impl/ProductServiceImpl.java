package com.zdh.core.base.test.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zdh.core.base.test.dao.IProductDao;
import com.zdh.core.base.test.domain.Product;
import com.zdh.core.base.test.service.IproductService;

public class ProductServiceImpl implements IproductService {
	@Autowired
	private IProductDao  productDao ;
	@Override 
	public void save(Product entity) {
		productDao.save(entity) ;
	}
	public IProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}
	
}
