<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/ecmascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script> 
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/js/fileinput.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/js/file.css" rel="stylesheet">
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/fileinput.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/fileinput_locale_zh.js" type="text/javascript"></script>
</head>
<body>
<center> <h1>图片上传Demo</h1></center>
<hr>
<div style="padding: 10px;margin-left: -1%;margin-right: 28%;">
					<form enctype="multipart/form-data">
						<input id="file-0a" class="file" name="file" type="file" multiple data-min-file-count="1"/> <br>
					</form>
				</div>

</body>
<script src="${pageContext.request.contextPath }/js/yzdFile.js" type="text/javascript"></script>
</html>