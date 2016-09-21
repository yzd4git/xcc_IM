<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WHAT THE FUCK!!</title>
</head>
<body>
<center><h1>Hello!</h1></center>
<br />

<div>
<textarea id="encryption">在此输入加密内容</textarea>
</div>
<input type="text" readonly="readonly" id="number"><br />
<input type="button" value="加密" onclick="encryption()">
<input type="button" value="解密">
<div>
<textarea id="decryption"></textarea>
</div>




<hr />
<br /><br /><br /><br /><br /><br /><br /><br /><br />
<select id="type" name="selse" onchange="selectOne()">
<option value="Android">Android</option>
<option value="Ios">Ios</option>
</select>
<hr />
<div id="Android">
	<form action="">
		<input type="file" name="file"><input type="submit" value="上传">
	</form>
</div>
<div id="Ios" hidden="true">
	<input type="text" name="url">
</div>
<br />

<textarea name="a" style="width:200px;height:80px;"></textarea>
<hr />
<input type="submit" value="测试" onclick="dosomething()"><br />

<br />

</body>
<script src="${pageContext.request.contextPath }/test/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/test/js/yzd.js" type="text/javascript"></script>
</html>