<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/js/test/js/uploadify.css" rel="stylesheet">
<script type="text/ecmascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script> 
<script type="text/ecmascript" src="${pageContext.request.contextPath }/js/test/js/jquery.uploadify.min.js"></script> 
</head>
<body>

<center> <h1>测试上传</h1></center>
<hr />

<div id="fileQueue"></div>  
    <input type="file" name="uploadify" id="uploadify" />  
    <p>  
         <a href="javascript:$('#uploadify').uploadify('upload','*')">上传</a>| 
         <a href="javascript:$('#uploadify').uploadify('cancel','*')">取消上传</a>  
    </p> 
<script type="text/ecmascript" src="${pageContext.request.contextPath }/js/test/js/yzd.js"></script> 
<script type="text/javascript">  
$(document).ready(function() {  
    $("#uploadify").uploadify({  
        'swf'       : '${pageContext.request.contextPath }/js/test/js/uploadify.swf',  
        'uploader'  : '${pageContext.request.contextPath }/test/uploadMultipleFile',    
        'folder'         : '/upload',  
        'queueID'        : 'fileQueue',
        'cancelImg'      : '${pageContext.request.contextPath }/js/test/img/uploadify-cancel.png',
        'buttonText'     : '上传文件',
        'auto'           : false, //设置true 自动上传 设置false还需要手动点击按钮 
        'multi'          : true,  
        'wmode'          : 'transparent',  
        'simUploadLimit' : 999,  
        'fileTypeExts'        : '*.*',  
        'fileTypeDesc'       : 'All Files'
    });  
});  

</script>   
</html>