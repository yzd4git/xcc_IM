function dosomething(){
	$.ajax({
		url : 'advice/insert.do',
		contentType    : 'application/x-www-form-urlencoded; charset=utf-8' , 
		data:{
		},
		type:'post',
		dataType:'json',
		success:function(data){
		}
	})
	
}

