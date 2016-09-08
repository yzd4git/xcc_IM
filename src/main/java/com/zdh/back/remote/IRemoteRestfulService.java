package com.zdh.back.remote;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.PathParam;

import org.springframework.web.bind.annotation.PathVariable;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

public interface IRemoteRestfulService {

	
	/**
	 * 获取版本号
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getVersion(String Info) throws UnsupportedEncodingException;
	
	/**
	 * 查看手机通讯录的所有人是否注册
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String isRegister(String Info)throws UnsupportedEncodingException;
	/**
	 * 返回给APP端信息，包括个人资料，群组头像
	 * @param Info 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String backInfo(String Info) throws UnsupportedEncodingException;
	
	/**
	 * 删除用户
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String deleteUser(String Info) throws UnsupportedEncodingException;
	
	/**
	 * 获取单个群组头像
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getGroup(String Info) throws UnsupportedEncodingException;
	
	/**
	 * 获取多个群组头像
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getGroups(String Info) throws UnsupportedEncodingException;
	
	/**
	 * 群头像上传
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String setGroupImg(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,@PathParam("groupId") String groupId) throws UnsupportedEncodingException;
	
	/**
	 * 保存用户的建议
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String feedBack(String Info) throws UnsupportedEncodingException;
	
	
	/**
	 * 修改键盘小密码
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String updateNumberPasswd(String Info) throws UnsupportedEncodingException;
	
	/**
	 * 获取键盘密码
	 * @param phone
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getNumberPasswd(String Info) throws UnsupportedEncodingException ;
	
	/**
	 * 获取盐值
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getSalt(String Info) throws UnsupportedEncodingException;

	/**
	 * 头像上传
	 * 
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @param logId
	 * @return
	 */
	public String headSculpture(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @PathParam("logId") String logId);

	
//	public String headSculpture(@RequestParam MultipartFile file, @PathParam("logId") String logId) ;
	
	/**
	 * 修改个人资料
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String updateInfo(String Info) throws UnsupportedEncodingException;

	/**
	 * 获取个人资料
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String gainInfo(String Info) throws UnsupportedEncodingException;

	
	/**
	 * 禁用某个IM帐号
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String Deactivate(String Info) throws UnsupportedEncodingException;
	
	/**
	 * 解禁某个IM帐号
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String Activate(String Info) throws UnsupportedEncodingException;
	/**
	 * 根据密保问题进行修改密保
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String alterQuestion(String Info) throws UnsupportedEncodingException;

	/**
	 * 根据密保问题修改密码
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String alterPasswdByQues(String Info) throws UnsupportedEncodingException;

	/**
	 * 设置密保问题
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String setQuestion(String Info) throws UnsupportedEncodingException;
	
	
	/**
	 * 修改时对用户键入的验证码进行校验
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String UpdateVerify(String Info) throws UnsupportedEncodingException;
	 
	/**
	 * 通过短信修改密码
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String updatePasswd (String Info) throws UnsupportedEncodingException;
	
	
	/**
	 * 
	 * 给用户发送找回密码的短信
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String sendUpdateMsg(String Info) throws UnsupportedEncodingException;

	/**
	 * 修改昵称
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String alterName(String Info) throws UnsupportedEncodingException;

	
	/**
	 * 返回给APP端用户信息（单个）
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getFriend(String Info) throws UnsupportedEncodingException;
	
	
	/**
	 * 返回给 app端 用户信息
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String backApp(String Info) throws UnsupportedEncodingException;

	/**
	 * 查询出用户的好友列表
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String gainFriends(String Info) throws UnsupportedEncodingException;

	/**
	 * 获取手机端验证码
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getVerifCode(String Info) throws UnsupportedEncodingException;

	/**
	 * 添加好友
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String addFriends(String Info) throws UnsupportedEncodingException;

	/**
	 * 根据昵称查找好友
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String findByNickName(String Info) throws UnsupportedEncodingException;

	/**
	 * 查找好友
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String find(String Info) throws UnsupportedEncodingException;

	/**
	 * 用户注册
	 * 
	 * @param Info
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String userRegister(String Info) throws UnsupportedEncodingException;

	public String userLogin(String Info) throws UnsupportedEncodingException;

}
