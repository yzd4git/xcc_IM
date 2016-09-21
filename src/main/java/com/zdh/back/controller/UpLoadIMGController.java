package com.zdh.back.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller 
@RequestMapping("test")
public class UpLoadIMGController {  
      
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> Test(
			@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
//		String path = request.getSession().getServletContext().getRealPath("upload/images"); // 获取根目录下的upload文件夹
//		if (StringUtils.isBlank(path)) {
//			path = request.getSession().getServletContext().getRealPath("/") + File.separator + "upload/images"
//					+ File.separator;
//			File fd = new File(path);
//			fd.mkdir();
//		}
//		String fileName = System.currentTimeMillis()+"";  //防止重名+时间戳
//	    File targetFile = new File(path, fileName);
//        if(!targetFile.exists()){
//           targetFile.mkdirs();
//        }
//        //保存
//        try {
//           file.transferTo(targetFile) ; //复制  
//        } catch (Exception e) {
//           e.printStackTrace();
//           return null ;
//        }
//		String Url = "upload/images" + File.separator + fileName ; 
//        Url = Url.replaceAll("\\\\", "/");
//        map.put("fileNames", fileName);
//        map.put("fileUrl", Url) ;
//        map.put("success", true) ;
	    return map ;
	}
}  