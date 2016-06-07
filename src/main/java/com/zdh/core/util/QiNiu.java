package com.zdh.core.util;

import com.qiniu.util.Auth;
import java.io.IOException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;

/**
 * 七牛云存储Demo（文件的上传和下载）
 * @author yzd
 *
 */
public class QiNiu {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	final static String ACCESS_KEY = "6r3mMo8T-PZQaCh8jk1zcDqHymRQLhppyagRd8NV";
	final static String SECRET_KEY = "e5UZDjLqwaAnU_G_Y6yJ6DRZ4IZTFGeOOKLyeReW";
//	// 要上传的空间
	final static String bucketname = "xcc-im";
//	// 上传到七牛后保存的文件名
////	String key = "my-java.png";
//	// 上传文件的路径
////	String FilePath = "E:\\Iwall\\xcc_IM\\src\\main\\webapp\\upload\\image\\2aca36fa-aba8-4a19-9d81-e15931090808.png";
//
//	// 密钥配置
	static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
//	// 创建上传对象
	static UploadManager uploadManager = new UploadManager();

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken() {
		return auth.uploadToken(bucketname);
	}

	/**
	 * 七牛云存储文件上传
	 * @param key 上传到七牛后保存的文件名
	 * @param FilePath 上传的路径
	 * @throws IOException
	 */
	public static void upload(String key , String FilePath) throws IOException {
		try {
			// 调用put方法上传
			Response res = uploadManager.put(FilePath, key, getUpToken());
			// 打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}
	
	/**
	 * 文件下载
	 * @param key 下载的文件
	 * @return 路径
	 */
	public static String DownloadDemo(String key){
		 //调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
		//构造私有空间的需要生成的下载的链接
		 String URL = "http://o7npswv08.bkt.clouddn.com/" + key;
		 String downloadRUL = auth.privateDownloadUrl(URL,3600);
		 return downloadRUL;
	}
	
	
		
	
	public static void main(String[] args) {
//		try {
//			new QiNiu().upload("001.png", "E:\\Iwall\\xcc_IM\\src\\main\\webapp\\upload\\image\\2aca36fa-aba8-4a19-9d81-e15931090808.png");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		new QiNiu().DownloadDemo("001.png");
		
		
	}
}
