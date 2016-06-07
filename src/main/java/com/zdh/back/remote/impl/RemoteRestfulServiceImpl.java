package com.zdh.back.remote.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.zdh.back.dao.FeedBackDao;
import com.zdh.back.dao.IUserDao;
import com.zdh.back.entities.AppFeedback;
import com.zdh.back.entities.AppUserInfo;
import com.zdh.back.remote.IRemoteRestfulService;
import com.zdh.core.base.util.QueryResult;
import com.zdh.core.util.AgeUtil;
import com.zdh.core.util.Base64Util;
import com.zdh.core.util.CheckPhoneCode;
import com.zdh.core.util.Cn2Spell;
import com.zdh.core.util.HuanXinUtils;
import com.zdh.core.util.JUHEMessageUtil;
import com.zdh.core.util.MD5Util;
import com.zdh.core.util.MyStringUtil;
import com.zdh.core.util.RegexUtils;
import com.zdh.core.util.TextUtils;

@Component
@Scope("request")
@Path("/remoteApi")
@Service
public class RemoteRestfulServiceImpl implements IRemoteRestfulService {
//	private static final Logger logger=  (Logger) LogFactory.getLog(RemoteRestfulServiceImpl.class) ;
	  private static final Log log = LogFactory.getLog(RemoteRestfulServiceImpl.class);
	@Autowired
	private IUserDao userDao;
	@Autowired
	private FeedBackDao feedBackDao;
	@Autowired
	private HttpServletRequest request;
	
	
	
	@Path("backInfo")
	@POST 
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String backInfo(String Info) throws UnsupportedEncodingException {
		JSONObject InfoParam = JSON.parseObject(Info);
		String infParam = InfoParam.getString("data");//得到的是一个Json数组
		String decode = Base64Util.decode(infParam);
		JSONObject inf = JSONObject.parseObject(decode) ;	
		
		JSONArray Uid = JSONObject.parseArray(inf.getString("Uid"));
		JSONArray Gid = JSONObject.parseArray(inf.getString("Gid"));
		String UidStr = "";
		String GidStr = "";
		for (int i = 0; i < Uid.size(); i++) {
			UidStr = UidStr + " '" + Uid.getJSONObject(i).getString("id") + "'" + ",";
		}
		for (int i = 0; i < Gid.size(); i++) {
			GidStr = GidStr + " '" + Gid.getJSONObject(i).getString("id") + "'" + ",";
		}
		JSONObject array = new JSONObject();
		List<Map<String, Object>> UList = new ArrayList<>();
		List<Map<String, Object>> Glist  = new ArrayList<>(); 
		if(!TextUtils.isEmpty(UidStr)){
			UidStr = UidStr.substring(0,UidStr.length() - 1);
			StringBuffer Usql = new StringBuffer();
			Usql.append("  SELECT id,IFNULL(nickname,'')nickname,phone,IFNULL(head_sculpture,'')headSculpture,IFNULL(sex,'')sex,IFNULL(age,'')age,IFNULL(birthday,'')birthday , pinyin , im_username imUsername FROM t_app_user_info where im_username in(")
				.append(UidStr).append(" )");
			UList.addAll(userDao.queryForMapListBySql(Usql.toString()));
			array.put("UList", UList);
		} else {
				array.put("UList", UList);
		}
		if(!TextUtils.isEmpty(GidStr)){
			GidStr = GidStr.substring(0,GidStr.length() - 1);
			StringBuffer Gsql = new StringBuffer();
			Gsql.append(" SELECT id ,IFNULL(img_path,'') imgPath FROM t_group_img WHERE id IN(").append(GidStr).append(") ");
					Glist.addAll(userDao.queryForMapListBySql(Gsql.toString()));
			array.put("GList", Glist);
		} else {
				array.put("GList", Glist);
		}
		String data = Base64Util.encode(array.toString());
		JSONObject obj = new JSONObject();
		obj.put("data", data);
		return obj.toString();
	}
	
	
	@Path("getGroup")
	@POST 
	@Produces({MediaType.APPLICATION_JSON})
	@Override
	public String getGroup(String Info) throws UnsupportedEncodingException{
		JSONObject InfoParam = JSON.parseObject(Info);
		String infParam = InfoParam.getString("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		JSONObject obj = new JSONObject();
		String groupId = inf.getString("groupId");
		StringBuffer sql = new StringBuffer();//查看群组头像
		sql.append(" SELECT id ,IFNULL(img_path,'') imgPath FROM t_group_img WHERE id = '").append(groupId).append("' ");
		List<Map<String,Object>> groups = userDao.queryForMapListBySql(sql.toString());
		if(groups.isEmpty()) {
			obj.put("success", "2");
		} else if (groups.size() == 1){
			obj.put("group", groups.get(0));
		} else {
			obj.put("success", "2");
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	/**
	 * 获取群组图片路径
	 */
	@Path("getGroups")
	@POST 
	@Produces({MediaType.APPLICATION_JSON})
	@Override
	public String getGroups(String Info) throws UnsupportedEncodingException {
		JSONObject obj = new JSONObject();
		JSONObject fromObject = JSON.parseObject(Info);
		JSONArray json = JSON.parseArray(Base64Util.decode(fromObject.getString("data")));
		if (null !=json && !json.isEmpty()) {
			String str = "";
			for (int i = 0; i < json.size(); i++) {
				try {
					str = str + " '" + json.getJSONObject(i).getString("groupId") + "'" + ",";
				} catch (Exception e) { 
					obj.put("success", "2");//系统发生未知的错误
				}
			}
			String idStr = str.substring(0, str.length() - 1);
			StringBuffer sql = new StringBuffer();//查看群组头像
			sql.append(" SELECT id ,IFNULL(img_path,'') imgPath FROM t_group_img WHERE id IN(").append(idStr).append(") ");
			List<Map<String, Object>> list = userDao.queryForMapListBySql(sql.toString());
			if(null != list){
				obj.put("success", "1");
				obj.put("groups", list);
			} else {
				obj.put("success", "2");
			}
		} else {
			obj.put("success", "3");
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	/**
	 * 设置群组的头像
	 */
	@Path("setGroupImg/{groupId}")
	@POST
	@Consumes({ "multipart/form-data" })
	@Produces({ "text/plain" })
	@Override
	public String setGroupImg(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail , @PathParam("groupId") String groupId) throws UnsupportedEncodingException {
		
		UUID uuid = UUID.randomUUID();
		JSONObject obj = new JSONObject();
		try {
			String path = this.request.getRealPath("/upload/image") + File.separator + uuid + "."
					+ fileDetail.getFileName().split("\\.")[1];
			FileOutputStream out = new FileOutputStream(path);
			byte[] date = new byte[1024];
			while (uploadedInputStream.read(date) != -1) {
				out.write(date);
			}
			out.flush();
			out.close();
			uploadedInputStream.close();                       
			String realUrl = "upload" + File.separator + "image" + File.separator + uuid + "." + fileDetail.getFileName().split("\\.")[1];
			realUrl = realUrl.replaceAll("\\\\", "/");
			StringBuffer selectSql = new StringBuffer();
			selectSql.append(" SELECT COUNT(*) FROM t_group_img WHERE id = '").append(groupId).append("' ");
			Integer state = userDao.queryForInt(selectSql.toString());
			StringBuffer ImgSql = new StringBuffer();
			if(state > 0){
				ImgSql.append(" UPDATE t_group_img SET img_path = '").append(realUrl).append("'  WHERE id='").append(groupId).append("' ");
			} else {
				ImgSql.append(" INSERT INTO t_group_img VALUES('").append(groupId).append("' , '").append(realUrl).append("' )");
			}
			int savaStatus = userDao.executeBySql(ImgSql.toString());
			if (savaStatus > 0) {
				obj.put("success", "1");//成功
				obj.put("url", realUrl);
			} else {
				obj.put("success", "2");//失败
			}
		} catch (IOException localIOException) {
			obj.put("success", "2");//系统发生未知的错误
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	/**
	 * 用户反馈消息的保存
	 */
	@Path("feedBack")
	@POST 
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String feedBack(String Info) throws UnsupportedEncodingException {
		JSONObject InfoParam = JSON.parseObject(Info);
		String infParam = InfoParam.getString("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		JSONObject obj = new JSONObject();
		//INSERT INTO `t_app_feedback` (`id`, `advice`, `time`) VALUES ('2', '2', '2016-04-20 15:39:16')
		AppFeedback fb = new AppFeedback();
		fb.setId(UUID.randomUUID().toString());
		fb.setAdvice(inf.getString("advice"));
		fb.setPhone(inf.getString("phone"));
		fb.setContact(inf.getString("contact"));
		fb.setTime(new Date());
		int state = feedBackDao.save(fb);
		if(state > 0){
			obj.put("success", "1");
		} else {
			obj.put("success", "2");
		}
		
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	
	/**
	 * 修改键盘小密码
	 */
	@Path("updateNumberPasswd")
	@POST 
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String updateNumberPasswd(String Info) throws UnsupportedEncodingException {
		JSONObject InfoParam = JSON.parseObject(Info);
		String infParam = InfoParam.getString("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		JSONObject obj = new JSONObject();
		String numberPasswd = inf.getString("numberPasswd");
		String id = inf.getString("id");
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(" SELECT number_passwd , passwd_hash from t_app_user_info WHERE id = '").append(id).append("' ");
		AppUserInfo user = userDao.queryForSql(AppUserInfo.class, selectSql.toString());
		if(TextUtils.isEmpty(user.getNumberPasswd())){
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE t_app_user_info SET number_passwd='").append(numberPasswd)
				.append("' WHERE id = '").append(id).append("' ");
			
			int state = userDao.executeBySql(sql.toString());
			if(state >0){
				obj.put("success", "1");//添加键盘密码成功
			} else {
				obj.put("success", "3");//设置失败
			}
		} else {
			try {
				String passwdHash = MD5Util.md5Encode(inf.getString("passwdHash"));
				StringBuffer sql = new StringBuffer();
				if(passwdHash.equals(user.getPasswdHash())){
					StringBuffer updateSql = new StringBuffer();
					updateSql.append(" UPDATE t_app_user_info SET number_passwd = '").append(numberPasswd).append("' WHERE id = '").append(id).append("' ");
					int state = userDao.executeBySql(updateSql.toString());
					if(state > 0){
						obj.put("success", "2");//密码修改成功
					} else {
						obj.put("success", "3");//设置失败
					}
				} else {	
					obj.put("success", "4");//密码不正确，咱不允许修改
				}
			} catch (Exception e) {
				obj.put("success", "3");//系统发生未知的错误
			}
		}
		
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	/**
	 * 获取键盘小密码
	 */
	@Path("getNumberPasswd")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String getNumberPasswd(String Info) throws UnsupportedEncodingException {
		JSONObject InfoParam = JSON.parseObject(Info);
		String infParam = InfoParam.getString("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		JSONObject obj = new JSONObject();
		String phone = inf.getString("phone");
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT number_passwd from t_app_user_info where phone = ").append(phone);
		AppUserInfo user = userDao.queryForSql(AppUserInfo.class, sql.toString());
		if(null != user.getNumberPasswd()){
			obj.put("numberPasswd", user.getNumberPasswd());
		} else {
			obj.put("numberPasswd", "");
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	/**
	 * 获取盐值
	 */
	@Path("getSalt")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String getSalt(String Info) throws UnsupportedEncodingException {
		JSONObject InfoParam =JSON.parseObject(Info);// 获取到的是加密的JSON
		String infParam = (String) InfoParam.get("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		JSONObject obj = new JSONObject();
		String phone = (String) inf.get("phone");
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT COUNT(0) FROM t_app_user_info WHERE phone = ").append(phone);
		Integer status = userDao.queryForInt(sql.toString());
		if (status > 0) {
			StringBuffer selectSql = new StringBuffer();
			// SELECT salt FROM t_app_user_info WHERE phone = '18510195274'
			selectSql.append(" SELECT salt FROM t_app_user_info WHERE phone = ").append(phone);
			AppUserInfo user = userDao.queryForSql(AppUserInfo.class, selectSql.toString());
			obj.put("salt", user.getSalt());
		} else {
			obj.put("salt", "");
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	/**
	 * 用户头像上传
	 */
	@Path("headSculpture/{logId}")
	@POST
	@Consumes({ "multipart/form-data" })
	@Produces({ "text/plain" })
	public String headSculpture(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @PathParam("logId") String logId) {
		UUID uuid = UUID.randomUUID();
		JSONObject obj = new JSONObject();
		try {
			String path = this.request.getRealPath("/upload/image") + File.separator + uuid + "."
					+ fileDetail.getFileName().split("\\.")[1];
			FileOutputStream out = new FileOutputStream(path);
			byte[] date = new byte[1024];
			while (uploadedInputStream.read(date) != -1) {
				out.write(date);
			}
			out.flush();
			out.close();
			uploadedInputStream.close();                       
			String realUrl = "upload" + File.separator + "image" + File.separator + uuid + "." + fileDetail.getFileName().split("\\.")[1];
			realUrl = realUrl.replaceAll("\\\\", "/");
			StringBuffer saveSql = new StringBuffer();
			saveSql.append(" UPDATE t_app_user_info SET head_sculpture= '").append(realUrl).append("'  WHERE (id='")
					.append(logId).append("') ");
			int savaStatus = this.userDao.executeBySql(saveSql.toString());
			if (savaStatus > 0) {
				obj.put("success", "1");//成功
				obj.put("url", realUrl);
			} else {
				obj.put("success", "2");//失败
			}
		} catch (IOException localIOException) {
			obj.put("success", "2");//系统发生未知的错误
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	
	/**
	 * 修改个人资料	
	 */
	@Path("updateInfo")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String updateInfo(String Info) throws UnsupportedEncodingException {
		JSONObject InfoParam = JSON.parseObject(Info);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String infParam = (String) InfoParam.get("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		JSONObject obj = new JSONObject();
		String id = inf.getString("id");
		String nickName  = inf.getString("nickname");
		String sex = inf.getString("sex");
		String date = inf.getString("date");
		Integer age = null;
		
		String updateSql = " UPDATE t_app_user_info SET ";
		if(null != nickName && !"".equals(nickName)){
			String pinyin = Cn2Spell.converterToSpell(nickName);
			updateSql = updateSql + "nickname ='"+ nickName + "',pinyin = '" + pinyin + "' ,";
		}
		if(null != sex && !"".equals(sex)){
			updateSql = updateSql + "sex ='"+ sex + "',";
		}
		if(null != date	&& !"".equals(date)){
			try {
				age = AgeUtil.getAgeByBirthday(sdf.parse(date));
			} catch (ParseException e) {
				obj.put("success", "2");//系统发生未知的错误
			}
			updateSql = updateSql + "birthday ='"+ date + "',age='" + age + "',";
		}
		updateSql = updateSql.substring(0, updateSql.length()-1);
		updateSql = updateSql + " WHERE id = '" + id + "' ";
		int status = userDao.executeBySql(updateSql);
		if(status > 0){
			obj.put("success", "1");//修改成功
		} else {
			obj.put("success", "2");//修改失败
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	/**
	 * 根据 ID 获取个人资料
	 */
	@Path("gainInfo")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String gainInfo(String Info) throws UnsupportedEncodingException {
		JSONObject InfoParam = JSON.parseObject(Info);
		String infParam = InfoParam.getString("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		String id= (String) inf.get("id");
		StringBuffer selectSql = new StringBuffer();
		//SELECT head_sculpture , nickname , sex , birthday , phone FROM t_app_user_info WHERE id = '2412'
		JSONObject obj = new JSONObject();
		if(null != id && !"".equals(id)){
			selectSql.append(" SELECT IFNULL(head_sculpture,'')headSculpture ,IFNULL(nickname,'')nickname ,IFNULL(sex,'')sex ,IFNULL(birthday,'')birthday ,IFNULL(age,'')age ,IFNULL(number_passwd ,'')numberPasswd ,phone FROM t_app_user_info WHERE id = '")
			   .append(id).append("' ");
			List<Map<String, Object>> users = userDao.queryForMapListBySql(selectSql.toString());
			if(users.isEmpty()){
				obj.put("success", "2");//空
			} else if (users.size() == 1){
				obj.put("user", users.get(0));
			} else {
				obj.put("success", "2");
			}
		} else {
			obj.put("success", "2");//参数错误
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}

	/**
	 * 给用户发送找回密码的信息
	 */
	@Path("sendUpdateMsg")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String sendUpdateMsg(String Info) throws UnsupportedEncodingException {
		
		JSONObject InfoParam =JSON.parseObject(Info);// 获取到的是加密的JSON
		String infParam = (String) InfoParam.get("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		JSONObject obj = new JSONObject();
		String phone = inf.getString("phone");
		String code = MyStringUtil.getRandomCode(6);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//先对这个手机号判断有没有注册过，返回值大于0的情况才会给用户发送短信
		StringBuffer selectSql = new StringBuffer();
		selectSql.append("  select count(0)  from t_app_user_info where phone = ").append(phone);
		int selectStatus = userDao.queryForInt(selectSql.toString());
		if(selectStatus > 0){
			StringBuffer updateSql = new StringBuffer();
			updateSql
					.append(" UPDATE t_app_verfication_code SET verification_code = ")
					.append(code).append(" ,")
					.append("create_time = '")
					.append(sf.format(new Date()))
					.append("' where phone = ").append(phone);
			userDao.executeBySql(updateSql.toString());
													//13723
			JUHEMessageUtil.sendVerifyCodeMsg(phone, "13723", "#code#=" + code
					+ "&#app#=IM", null);
			obj.put("success", "1");
		} else {
			obj.put("success", "2");//该手机号还未注册
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	
	/**
	 *找回密码时对验证码校验 
	 */
	@Path("updateVerify")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String UpdateVerify(String Info) throws UnsupportedEncodingException {
		JSONObject InfoParam =JSON.parseObject(Info);// 获取到的是加密的JSON
		String infParam = (String) InfoParam.get("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		JSONObject obj = new JSONObject();
		String phone = inf.getString("phone");
		String code = inf.getString("code");
		StringBuffer selectSql = new StringBuffer();
		selectSql.append("  select count(0)  from t_app_user_info where phone = ").append(phone);
		int selectStatus = userDao.queryForInt(selectSql.toString());
		if(selectStatus > 0){
			StringBuffer coreSql = new StringBuffer();//判断验证码是否存在
			coreSql.append(" SELECT count(0) FROM t_app_verfication_code where phone = ").append(phone).append(" AND ")
					.append(" verification_code=").append(code);
			int dataCore = userDao.queryForInt(coreSql.toString());
			if(dataCore > 0){
				Map<String,String> map = userDao.getCustomerCode(phone, code);
				Date date=new Date();
				SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				double timeMinute =CheckPhoneCode.getMinutesDiff(map.get("create_time")+"", sim.format(date));
				if(!(timeMinute>10)){
					//验证成功之后将对应的验证码置空
					//DELETE FROM t_app_verfication_code WHERE phone = '18510195274'
					StringBuffer deleteSql = new StringBuffer();
					deleteSql.append(" UPDATE t_app_verfication_code SET verification_code = '' WHERE phone = ").append(phone);
					userDao.executeBySql(deleteSql.toString());
					obj.put("success", "1");//验证成功
				} else {
					obj.put("success", "2");//验证码失效
				}
			} else {
				obj.put("success", "3");//验证码不正确
			}
		} else {
			obj.put("success", "4");//用户未注册
		}
		
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	
	/**
	 * 通过短信验证码进行修改密码
	 */
	@Path("updatePasswd")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String updatePasswd(String Info) throws UnsupportedEncodingException {
		JSONObject InfoParam =JSON.parseObject(Info);// 获取到的是加密的JSON
		String infParam = (String) InfoParam.get("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		JSONObject obj = new JSONObject();
		String phone = inf.getString("phone");
		String passwdHash = inf.getString("passwdHash");
		String salt = inf.getString("salt");
		StringBuffer sql = new StringBuffer();
		String testHUXINToken = HuanXinUtils.testHUXINToken();
		JSONObject parseObject = JSON.parseObject(testHUXINToken);
		String token = parseObject.getString("access_token");
		JSONObject json = null ;
		try {
			passwdHash = MD5Util.md5Encode(passwdHash);
		} catch (Exception e1) {
		}
		json = HuanXinUtils.ResetPasswd(phone, passwdHash, token);
		
		if(json != null && json.getString("success") == null){
			try {
				sql.append(" UPDATE t_app_user_info SET passwd_Hash = '").append(passwdHash).append("', salt = '")
				.append(salt).append("' WHERE phone = ").append(phone);
				int state = userDao.executeBySql(sql.toString());
				if(state >0){
					obj.put("success", "1");//成功
				} else {
					obj.put("success", "2");//失败
				}
			} catch (Exception e) {
				obj.put("success", "2");//修改失败
			}
		} else {
			obj.put("success", "2");//修改失败
		}
		
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}

	
	/**
	 * 修改密保问题
	 */
	@Path("alterQuestion")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String alterQuestion(String Info) throws UnsupportedEncodingException {
		JSONObject inf = JSON.parseObject(Info);
		JSONObject obj = new JSONObject();
		
		String id = (String) inf.get("id");
		String oldQuestion = (String)inf.get("oldQuestion");
		String  oldAnswer = (String)inf.get("oldAnswer");
		String newQuestion = (String)inf.get("newQuestion");
		String newAnswer = (String)inf.get("newAnswer");
		
		Map<String,String>map = new HashMap<String,String>();
		map.put("id", id);
		AppUserInfo user = userDao.findByProperty(AppUserInfo.class, map);
		
		if(!user.getPrivacyProblem().isEmpty()) {
			if(user.getPrivacyProblem().equals(oldQuestion) && user.getSecretAnswer().equals(oldAnswer)) {
				StringBuffer updateSql = new StringBuffer();
				//UPDATE t_app_user_info SET privacy_problem = '测试问题',secret_answer='答案测试' WHERE id ='2412'
				updateSql.append(" UPDATE t_app_user_info SET privacy_problem = '").append(newQuestion)
						          .append("' ,secret_answer='").append(newAnswer).append("' WHERE id ='").append(id).append("' ");
				int status = userDao.executeBySql(updateSql.toString());
				
				if(status > 0){
					obj.put("success", "1");//修改成功
				} else {
					obj.put("success", "2");//修改失败
				}
			}else {
				obj.put("success", "3");//密保问题和答案不匹配
			}
		} else {
			obj.put("success", "4");//该用户还没有设置密保问题
		}
		String result = obj.toString();
		return result;
	}
	
	/**
	 * 根据密保问题进行修改密码
	 */
	@Path("alterPasswdByQues")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String alterPasswdByQues(String Info) throws UnsupportedEncodingException {
		JSONObject inf = JSON.parseObject(Info) ;
		JSONObject obj = new JSONObject() ; 
		String phone = (String)inf.get("phone");
		String question = (String)inf.get("question");
		String answer = (String )inf.get("answer");
		String passwd = null;
		try {
			passwd = MD5Util.md5Encode((String)inf.get("passwd"));
		} catch (Exception e) {
			obj.put("success", "2");//系统发生未知的错误
		}
		StringBuffer selectSql = new StringBuffer();
		//SELECT COUNT(0) FROM t_app_user_info WHERE id = '2412'
		selectSql.append(" SELECT COUNT(0) FROM t_app_user_info WHERE phone = ").append(phone);
		int selectStatus = userDao.queryForInt(selectSql.toString());
		if (selectStatus > 0) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("phone", phone);
			AppUserInfo user = userDao.findByProperty(AppUserInfo.class, map);
			
			if(user.getPrivacyProblem()!=null) {
				if(user.getPrivacyProblem().equals(question)&&user.getSecretAnswer().equals(answer)) {
					StringBuffer update = new StringBuffer();
					//UPDATE t_app_user_info SET passwd_hash = '000000' WHERE phone = '15010391007'
					update.append(" UPDATE t_app_user_info SET passwd_hash = '").append(passwd).append("' WHERE phone = ").append(phone);
					int updateStatus = userDao.executeBySql(update.toString());
					if(updateStatus > 0) {
						obj.put("success", "1");//修改成功
					} else {
						 obj.put("success", "2");//修改失败
					}
				} else {
					obj.put("success", "3");//输入的密保问题和答案不匹配
				}
				
			} else {
				obj.put("success", "4");//该用户还没有设置密保问题
			}
			
		} else {
			obj.put("success", "5");//该用户还没有注册
		}
		String result = obj.toString();
		return result;
	}
	
	/**
	 * 设置密保问题
	 */
	@Path("setQuestion")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String setQuestion(String Info) throws UnsupportedEncodingException {
		
		JSONObject inf = JSON.parseObject(Info);
		JSONObject object = new JSONObject();
		String id = (String)inf.get("id");
		String question = (String)inf.get("question");
		String answer = (String)inf.get("answer");
		
		
		
		
//		Map<String, String>map = new HashMap<String,String>();
//		map.put("id", id);		
//		AppUserInfo user = userDao.findByProperty(AppUserInfo.class, map);//查看是否已经设置密保问题
//		if(user.getPrivacyProblem() == null){
//			StringBuffer setSql = new StringBuffer();
//			//UPDATE t_app_user_info SET privacy_problem = '你猜猜我是谁',secret_answer='不猜不猜就不猜' WHERE id ='2412'
//			setSql.append("UPDATE t_app_user_info SET privacy_problem = '")
//					   .append(question)
//					   .append("' ,secret_answer='")
//					   .append(answer)
//					   .append("' WHERE id ='")
//					   .append(id).append("' ");
//			
//			int saveStatus = userDao.executeBySql(setSql.toString());
//			if(saveStatus > 0) {
//				object.put("success", "1");//密保问题设置成功
//			} else {
//				object.put("suceess", "2");//密保问题设置失败
//			}
//		} else {
//			object.put("success", "3");//用户已经设置了密保问题
//		}
//		String result = object.toString();
		
		return null;
	}

	
	
	/**<暂时不用>
	 * 修改昵称
	 */
	@Path("alterName")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String alterName(String Info) throws UnsupportedEncodingException {
		JSONObject inf = JSON.parseObject(Info);
		JSONObject object = new JSONObject();
		String id = (String) inf.get("id");
		String nickName = (String) inf.get("nickName");
		if( "" != nickName ){
			StringBuffer updateSql = new StringBuffer();
			//UPDATE t_app_user_info SET nickname = 'testUpdate' WHERE id = '2412'
			updateSql.append(" UPDATE t_app_user_info SET nickname = '")
							  .append(nickName)
							  .append("' WHERE id = '")
							  .append(id)
							  .append("' ");
			
			int updateStatus = userDao.executeBySql(updateSql.toString());
			if( updateStatus > 0 ){
				object.put("success", "1");//修改成功
			} else {
				object.put("success", "2");//修改成功
			}
		} else {
			object.put("success", "3");//输入的昵称为空
		}
		
		String resultString = object.toString();
		return resultString ;
	}
	
	/**
	 * 返回单个用户信息给APP端
	 */
	@Path("getFriend")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String getFriend(String Info) throws UnsupportedEncodingException {		
		JSONObject obj = new JSONObject();
		JSONObject parseObject = JSON.parseObject(Info);
		String infParam = parseObject.getString("data");
		JSONObject inf = JSONObject.parseObject(Base64Util.decode(infParam));
		String imUserName = inf.getString("im_username");
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(" SELECT id,IFNULL(nickname,'')nickname,phone,IFNULL(head_sculpture,'')headSculpture,IFNULL(sex,'')sex,IFNULL(age,'')age,IFNULL(birthday,'')birthday , pinyin , im_username imUsername FROM t_app_user_info where im_username = '")
		 .append(imUserName).append("'");
		
//		List<Map<String,Object>> list = userDao.queryForMapListBySql(selectSql.toString());
		AppUserInfo user = userDao.queryForSql(AppUserInfo.class, selectSql.toString());
		if(null != user){
			obj.put("success", "1");
			obj.put("friend", user);
		} else {
			obj.put("success", "2");
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	
	
	/**
	 * 返回App端用户信息
	 */
	@Path("backApp")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String backApp(String Info) throws UnsupportedEncodingException {
		JSONObject obj = new JSONObject();
		JSONObject fromObject = JSON.parseObject(Info);
		JSONArray json = JSON.parseArray(Base64Util.decode(fromObject.getString("data")));
		
		if (null !=json && !json.isEmpty()) {
			String str = "";
			for (int i = 0; i < json.size(); i++) {
				try {
					str = str + " '" + json.getJSONObject(i).getString("im_username") + "'" + ",";
				} catch (Exception e) { 
					obj.put("success", "2");//系统发生未知的错误
				}
			}
			String idStr = str.substring(0, str.length() - 1);
			StringBuffer selectSql = new StringBuffer();
			//SELECT id,nickname,phone,head_sculpture,sex,age,birthday FROM t_app_user_info where im_username='bb6a80ef2eeadfa96a0a94b0cda5b4f9'
			selectSql.append(" SELECT id,IFNULL(nickname,'')nickname,phone,IFNULL(head_sculpture,'')headSculpture,IFNULL(sex,'')sex,IFNULL(age,'')age,IFNULL(birthday,'')birthday , pinyin , im_username imUsername FROM t_app_user_info where im_username in(")
					 .append(idStr).append(") ");
//			selectSql.append(" SELECT id,IFNULL(nickname,'\"\"')nickname,phone,IFNULL(head_sculpture,'\"\"')head_sculpture,IFNULL(sex,'\"\"')sex,IFNULL(birthday,'\"\"')birthday FROM t_app_user_info where im_username in(")
//					 .append(idStr).append(") ");
			List<Map<String, Object>> list = userDao.queryForMapListBySql(selectSql.toString());
			if (null != list) {
				obj.put("success", "1");
				obj.put("friends",list);
				
			} else {
				obj.put("success", "2");
			}
		} else {
			obj.put("success", "3");// 为空
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}

	/**
	 * 获取好友列表
	 */
	@Path("gainFriends")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String gainFriends(String Info) throws UnsupportedEncodingException {
		JSONObject inf = JSON.parseObject(Info);
		JSONObject obj = new JSONObject();
		String id = (String) inf.get("id");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		StringBuffer selectSql = new StringBuffer();
		selectSql
				.append(" SELECT count(0) FROM t_user_friends_info where user_id = ")
				.append(" '").append(id).append("' ");
		int selectStatus = userDao.queryForInt(selectSql.toString());
		if (selectStatus > 0) {
			obj.put("success", "1");// 查得到该好友
			// SELECT * FROM t_app_user_info where id in( select friends_id from t_user_friends_info  where user_id = '2413')
			//String sql = " select t.nickname , t.id  from t_app_user_info t WHERE t.ID IN(SELECT ID FROM t_user_friends_info f WHERE t.id = f.user_id)";
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT * FROM t_app_user_info where id in( select friends_id from t_user_friends_info  where user_id = '")
				.append(id).append(" ') ");
			List<AppUserInfo> allFriends = userDao.findAllBySql(
					AppUserInfo.class, sql.toString());
			obj.put("users", allFriends);
		} else {
			obj.put("success", "2");// 查不到该好友
		}
		String resultString = obj.toString();
		return resultString;
	}
	
	/**
	 * 添加好友（ID）
	 */
	@Path("addFriends")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String addFriends(String Info) throws UnsupportedEncodingException {
		JSONObject inf = JSON.parseObject(Info);
		String phone = (String) inf.get("phone");
		String id = (String) inf.get("id");
		JSONObject obj = new JSONObject();

		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		AppUserInfo friendUser = userDao.findByProperty(AppUserInfo.class, map);
		if (null != friendUser) {
			String friendsId = friendUser.getId();
			StringBuffer friendSql = new StringBuffer();
			//SELECT COUNT(0) FROM t_user_friends_info where user_id = '2414' AND friends_id = '2413'
			
			friendSql.append(" SELECT COUNT(0) FROM t_user_friends_info where user_id = '")
					        .append(id)
					        .append("' AND friends_id = '")
					        .append(friendsId)
					        .append("' ");
			
			int friendsStatus = userDao.queryForInt(friendSql.toString());
			if(friendsStatus == 0){
				StringBuffer sql = new StringBuffer();
				sql.append(" insert into t_user_friends_info values( ")
						.append(" '").append(id).append("' ").append(",")
						.append(" '").append(friendUser.getId()).append("' ) ");
				int insertStatus = userDao.executeBySql(sql.toString());
				if (insertStatus > 0) {
					obj.put("success", "1");// 添加成功
				} else {
					obj.put("success", "2");// 添加失败
				}
			} else {
				obj.put("success", "3");//查找到的用户已经是当前用户的好友，无需重复添加
			}
		} else {
			obj.put("success", "4");// 没有查到该用户
		}
		String result = obj.toString();

		return result;
	}
	
	/**
	 * 根据昵称查找好友(分页)
	 */
	@Path("findByNickname")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String findByNickName(String Info) throws UnsupportedEncodingException {
		JSONObject inf = JSON.parseObject(Info);
		JSONObject obj = new JSONObject();
		String nickName = (String)inf .get("nickName");
		Integer pageNo = inf.getInteger("pageNo");
		Integer pageSize = inf.getInteger("pageSize");
		Map<String,String> where = new HashMap<>();
		where.put("nickname", nickName);
		QueryResult<AppUserInfo> findByPage = userDao.findByPage(pageNo, pageSize, where);
		
		if(findByPage != null){
			obj.put("success", "1");//查到符合条件的好友
			obj.put("users", findByPage);//
		} else {
			obj.put("success", "2");//没有找到任何符合要求的好友
		}
		String result = obj.toString();
		return result;
	}
	
	/**
	 * 查找好友
	 */
	@Path("find")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String find(String Info) throws UnsupportedEncodingException {
		JSONObject obj = new JSONObject();
		JSONObject parseObject = JSON.parseObject(Info);
		String infParam = parseObject.getString("data");
		JSONObject inf = JSONObject.parseObject(Base64Util.decode(infParam));
		String query = inf.getString("query");
		String id = inf.getString("id");
		StringBuffer sql = new StringBuffer();
		if(null != query){
			if(RegexUtils.isMobileNO(query)){
				sql.append(" SELECT IFNULL(nickname,'')nickname,IFNULL(phone,'')phone,IFNULL(head_sculpture,'')headSculpture,IFNULL(sex,'')sex,age,IFNULL(birthday,'1980-01-01')birthday FROM t_app_user_info WHERE phone = ")
				.append(query);
			} else {
				sql.append(" SELECT IFNULL(nickname,'')nickname,IFNULL(phone,'')phone,IFNULL(head_sculpture,'')headSculpture,IFNULL(sex,'')sex,age,IFNULL(birthday,'1980-01-01')birthday FROM t_app_user_info WHERE im_username = '")
				.append(query).append("' ");
			}
			
			List<Map<String, Object>> friends = userDao.queryForMapListBySql(sql.toString());
			if(!friends.isEmpty()){	
				obj.put("success", "1");//获取成功
				obj.put("users", friends);
			} else {
				obj.put("success", "2");
			}
		} else {
			obj.put("success", "2");
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
///*	
//	*//**
//	 * <以后用>
//	 * 分页查找好友
//	 *//*
//	@Path("find")
//	@POST
//	@Produces({ MediaType.APPLICATION_JSON })
//	@Override
//	public String find(String Info) throws UnsupportedEncodingException {
//		com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
////		JSONObject InfoParam = JSON.parseObject(Info);//获取到的是加密的JSON
//	com.alibaba.fastjson.JSONObject InfoParam =	(com.alibaba.fastjson.JSONObject) JSON.parse(Info);
//		String infParam = (String) InfoParam.get("data");
//		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
//		String query = inf.getString("query");
//		int pageNo = inf.getInteger("pageNo");
//		int pageSize = (inf.getInteger("pageSize")==0?15:inf.getInteger("pageSize"));
//		Map<String,String> map = new HashMap<>();
//		map.put("phone", query);
//		map.put("nickname", query);
//		if(pageNo > 0 && pageSize > 0 && !query.isEmpty()){
//			QueryResult<AppUserInfo> findByPage = userDao.findByPage(pageNo, pageSize, map);
//			obj.put("page", findByPage);
//		} else {
//			obj.put("success", "2");//参数错误
//		}
//		String data = Base64Util.encode(obj.toString());
//		obj.clear();
//		obj.put("data", data);
//		return obj.toString();
//	}
//*/	
	/**
	 * 获取短信验证码
	 */
	@Path("/getVerifCode")
	@POST
	@Produces({ MediaType.APPLICATION_JSON})
	@Override
	public String getVerifCode(String  Info) throws UnsupportedEncodingException {
		JSONObject obj = new JSONObject();
		JSONObject InfoParam = JSON.parseObject(Info);
		String infParam = (String) InfoParam.get("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		String phone = (String) inf.get("phone");
		String code = MyStringUtil.getRandomCode(6);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer phoneSql = new StringBuffer();
		//SELECT COUNT(*) FROM t_app_user_info WHERE phone = '18510195274'
		phoneSql.append(" SELECT COUNT(*) FROM t_app_user_info WHERE phone = ").append(phone);
		int phoneState = userDao.queryForInt(phoneSql.toString());
		if(phoneState == 0){
			int status = 0;
			StringBuffer selecttSql = new StringBuffer();
			selecttSql
			.append(" select count(0) from t_app_verfication_code where phone = ").append(phone);
			
			int selectStatus = userDao.queryForInt(selecttSql.toString());
			
			if (selectStatus > 0) {
				// 修改语句
				StringBuffer updateSql = new StringBuffer();
				updateSql
				.append(" UPDATE t_app_verfication_code SET verification_code = ")
				.append(code).append(" , create_time = ").append("'")
				.append(sf.format(new Date())).append("'")
				.append(" where phone =").append(phone);
				status = userDao.executeBySql(updateSql.toString());
			} else {
				// 添加语句
				StringBuffer insertSql = new StringBuffer();
				insertSql
				.append(" insert into t_app_verfication_code values( ")
				.append(" '").append(phone).append("'").append(",").append("'")
				.append(code).append("'").append(",").append("'")
				.append(sf.format(new Date())).append("'").append(" )");
				status = userDao.executeBySql(insertSql.toString());
			}
			
			if (status > 0) {
				JUHEMessageUtil.sendVerifyCodeMsg(phone, "13972", "#code#=" + code
						+ "&#app#=IM", null);
				obj.put("success", "1");//获取成功
			} else {
				obj.put("success", "2");//获取失败
			}
			
		} else {
			obj.put("success", "3");//用户已经注册
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}


	/**
	 * 用户注册
	 */
	@Path("register")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String userRegister(String Info) throws UnsupportedEncodingException {
		JSONObject inf = JSON.parseObject(Info);
		String infParam = (String) inf.get("data");
		JSONObject InfoParam = JSON.parseObject(Base64Util.decode(infParam));
		String phone = InfoParam.getString("phone");
		String passwd = InfoParam.getString("passwd");
		String code = InfoParam.getString("code");	
		String salt = InfoParam.getString("salt");
		AppUserInfo user = new AppUserInfo();
		StringBuffer selectSql = new StringBuffer();
		JSONObject obj = new JSONObject();
		StringBuffer coreSql = new StringBuffer();
		coreSql.append(" SELECT count(0) FROM t_app_verfication_code where phone = ").append(phone)
				.append(" AND verification_code=").append(code);
		int dataCore = userDao.queryForInt(coreSql.toString());
		
		if (dataCore > 0) {
			Map<String,String> map = userDao.getCustomerCode(phone, code);
			Date date=new Date();
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			double timeMinute =CheckPhoneCode.getMinutesDiff(map.get("create_time")+"", sim.format(date));
			if(!(timeMinute>10)){
				selectSql.append("  select count(0)  from t_app_user_info where phone = ").append(phone);
				int selectStatus = userDao.queryForInt(selectSql.toString());
				if (selectStatus == 0) {
					String token = HuanXinUtils.testHUXINToken();//获取Token
					JSONObject tokenJson = JSON.parseObject(token);
					String tokenObject = (String) tokenJson.get("access_token");
					String IMphone = null;
					String IMpasswd = null;
					try {
						IMphone = MD5Util.md5Encode(phone);
						IMpasswd = MD5Util.md5Encode(passwd);
						
						JSONObject json = HuanXinUtils.testHUXINRegister(IMphone,
								IMpasswd, tokenObject);//在环信端进行注册
						if (json != null) {
							if (json.get("success") == null) {
								user.setId(UUID.randomUUID().toString().replaceAll("[-]", ""));
								user.setPhone(phone);
								user.setPasswdHash(IMpasswd);
								user.setImUsername(IMphone);
								user.setImPasswd(IMpasswd);
								user.setSalt(salt);
								user.setCreateTime(new Date());
								int status = userDao.save(user);
								if (status > 0) {
									//注册成功之后将对应的验证码置空
									StringBuffer deleteSql = new StringBuffer();
									deleteSql.append(" UPDATE t_app_verfication_code SET verification_code = '' WHERE phone = ").append(phone);
									userDao.executeBySql(deleteSql.toString());
									obj.put("success", "1");// 注册成功
								} else {
									obj.put("success", "2");// 注册失败
								}
							}else {
								obj.put("success", "2");// 注册失败
							}
						} else {
							obj.put("success", "2");// 注册失败
						}
					} catch (Exception e) {
						obj.put("success", "2");//系统发生位置的错误
					}
				} else {
					obj.put("success", "3");// 用户已注册
				}
			} else{
				obj.put("success", "5");//验证码失效
			}
		} else {
			obj.put("success", "4");// 输入验证码有误
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}

	// 子路径//根据phone 查询出用户，查找数据库中是否有相应的数据。
	@Path("login")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String userLogin(String Info) throws UnsupportedEncodingException {
		JSONObject obj = new JSONObject();
		JSONObject inf = JSON.parseObject(Info);//获取到的是加密的JSON
		String infParam = (String) inf.get("data");
		JSONObject InfoParam = JSON.parseObject(Base64Util.decode(infParam));
		String phone = InfoParam.getString("phone");
		try {
			String passwdHash = MD5Util.md5Encode(InfoParam.get("passwdHash").toString());
			Map<String, String> map = new HashMap<String, String>();
			map.put("phone", phone);
			AppUserInfo findUser = userDao.findByProperty(AppUserInfo.class, map);
			if (findUser != null) {
				if (passwdHash.equals(findUser.getPasswdHash())) {
					StringBuffer updateTime = new StringBuffer();
					//UPDATE t_app_user_info SET last_login_time ='' WHERE phone = ''
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					updateTime.append(" UPDATE t_app_user_info SET last_login_time ='")
										 .append(sf.format(new Date())).append("' WHERE phone = ")
										 .append(phone);
					userDao.executeBySql(updateTime.toString());//修改最后一次登录的时间
					obj.put("success", "1");// 登录成功
					obj.put("id", findUser.getId());
				} else {
					obj.put("success", "2");// 登录失败
				}
			} else {
				obj.put("success", "3");// 该用户未注册
			}
		} catch (Exception e) {	
			obj.put("success", "2");//系统发生位置的错误
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}

	
	/********************** 暂时不用 **********************************/
	
	
	/**
	 * 删除用户<暂时不用>
	 */
	@Path("deleteUser")
	@POST 
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String deleteUser(String Info) throws UnsupportedEncodingException {
		JSONObject InfoParam = JSON.parseObject(Info);
		String infParam = InfoParam.getString("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		JSONObject obj = new JSONObject();
		String phone = inf.getString("phone");
		
		StringBuffer deleteSql = new StringBuffer();
		deleteSql.append(" DELETE FROM t_app_user_info WHERE phone = ").append(phone);
		int deleteState = userDao.executeBySql(deleteSql.toString());
		if (deleteState > 0) {
			String testHUXINToken = HuanXinUtils.testHUXINToken();
			JSONObject parseObject = JSON.parseObject(testHUXINToken);
			String token = parseObject.getString("access_token");
			JSONObject json = HuanXinUtils.Deactivate(phone, token);
			if(null != json && "".equals(json)){
				obj.put("success", "1");//删除成功
			}
		} else {
			obj.put("success", "2");//失败
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	/**
	 * IM账户的禁用
	 */
	@Path("deactivate")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String Deactivate(String Info) throws UnsupportedEncodingException {
		JSONObject obj = new JSONObject();
		JSONObject InfoParam = JSON.parseObject(Info);
		String infParam = (String) InfoParam.get("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		String phone = inf.getString("phone");
		//这个时候可以针对短信验证码或者验证密保进行判断。成功之后将帐号进行禁用
		String testHUXINToken = HuanXinUtils.testHUXINToken();
		JSONObject parseObject = JSON.parseObject(testHUXINToken);
		String token = parseObject.getString("access_token");
		
		JSONObject json = HuanXinUtils.Deactivate(phone, token);
		if(json != null && json.getString("success") == null){
			obj.put("success", "1");
		} else {
			obj.put("success", "2");
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	/**
	 * IM用户解禁
	 */
	@Path("activate")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public String Activate(String Info) throws UnsupportedEncodingException {
		JSONObject obj = new JSONObject();
		JSONObject InfoParam = JSON.parseObject(Info);
		String infParam = (String) InfoParam.get("data");
		JSONObject inf = JSON.parseObject(Base64Util.decode(infParam));
		String phone = inf.getString("phone");
		//这个时候可以针对短信验证码或者验证密保进行判断。成功之后将帐号进行禁用
		String testHUXINToken = HuanXinUtils.testHUXINToken();
		JSONObject parseObject = JSON.parseObject(testHUXINToken);
		String token = parseObject.getString("access_token");
		JSONObject json = HuanXinUtils.Activate(phone, token);
		if(json != null && json.getString("success") == null){
			obj.put("success", "1");
		} else {
			obj.put("success", "2");
		}
		String data = Base64Util.encode(obj.toString());
		obj.clear();
		obj.put("data", data);
		return obj.toString();
	}
	
	
	/**************************************************************/
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}


	/********************* 头像上传《运用七牛云存储》 ************************/
//	@Path("headSculpture/{logId}")
//	@POST
//	@Consumes({ "multipart/form-data" })
//	@Produces({ "text/plain" })
//	public String headSculpture(@FormDataParam("file") InputStream uploadedInputStream,
//			@FormDataParam("file") FormDataContentDisposition fileDetail, @PathParam("logId") String logId) {
//		long l1 = System.currentTimeMillis();
//		UUID uuid = UUID.randomUUID();
//		JSONObject obj = new JSONObject();
//		try {
//			String path = this.request.getRealPath("/upload/image") + File.separator + uuid + "."
//					+ fileDetail.getFileName().split("\\.")[1];
//			System.out.println("===" + path);
//			FileOutputStream out = new FileOutputStream(path);
//			byte[] date = new byte[1024];
//			while (uploadedInputStream.read(date) != -1) {
//				out.write(date);
//			}
//			out.flush();
//			out.close();
//			uploadedInputStream.close();                       
//			String realUrl = "upload" + File.separator + "image" + File.separator + uuid + "." + fileDetail.getFileName().split("\\.")[1];
//			realUrl = realUrl.replaceAll("\\\\", "/");
//			QiNiu.upload(uuid+fileDetail.getFileName(), path);
//			DeleteFileUtil.DeleteFolder(path);
//			String downloadDemo = QiNiu.DownloadDemo(uuid+fileDetail.getFileName());
//			System.out.println("-----------" + downloadDemo);
//			StringBuffer saveSql = new StringBuffer();
//			saveSql.append(" UPDATE t_app_user_info SET head_sculpture= '").append(downloadDemo).append("'  WHERE (id='")
//					.append(logId).append("') ");
//			int savaStatus = this.userDao.executeBySql(saveSql.toString());
//			if (savaStatus > 0) {
//				obj.put("success", "1");//成功
//				obj.put("url", realUrl);
//			} else {
//				obj.put("success", "2");//失败
//			}
//		} catch (IOException localIOException) {
//			obj.put("success", "2");//系统发生未知的错误
//		}
//		String data = Base64Util.encode(obj.toString());
//		obj.clear();
//		obj.put("data", data);
//		long l2 = System.currentTimeMillis();
//		System.out.println("==========================" + (l2 - l1));
//		
//		return obj.toString();
//	}
	
}
