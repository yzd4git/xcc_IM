package com.zdh.back.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdh.core.base.test.domain.Product;
import com.zdh.core.base.test.service.IproductService;
import com.zdh.core.base.util.ToolUtil;

@Controller
public class TestController {
	@Autowired
	private IproductService productService ;
    @RequestMapping("/result.do") 
    public String viewUser(HttpServletRequest request, ModelMap modelMap) 
            throws Exception { 
    	System.out.println("bbbb");
    	Product p = new Product() ;
    	p.setAuthor("sss") ;
    	p.setDescription("aaa") ;
    	p.setId(ToolUtil.getUUID()) ;
    	p.setName("adgadfgadf") ;
    	p.setPrice(20.1) ;
    	p.setQuantity(1) ;
    	productService.save(p) ;
        return "result"; 
    } 
    @RequestMapping("/testRedirect.do") 
    public void testRedirect() {
    	System.out.println("aaaa");
    }
	public IproductService getProductService() {
		return productService;
	}
	public void setProductService(IproductService productService) {
		this.productService = productService;
	}
    
}
