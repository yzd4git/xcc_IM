function encryption(){
//	alert();
	$.ajax({
		url : 'getKey',
		contentType    : 'application/x-www-form-urlencoded; charset=utf-8' , 
		data:{	},
		type:'post',
		dataType:'json',
		success:function(data){
			//alert("取消");
			if(data == true){
				location.reload();
			}else{
				alert("取消失败");
			}
		}
	})
}
















//==================================
function dosomething() {
	alert()
}

function selectOne() {
	var type = $("#type option:selected");
	if (type.val() == "Ios") {
		$("#Android").hide();
		$("#Ios").show();
	}
	if (type.val() == "Android") {
		$("#Android").show();
		$("#Ios").hide();
	}
}

function EnterPress(e) { // 传入 event
	var e = e || window.event;
	if (e.keyCode == 13) {
		alert("Hello!")
	}
}


//function Enter() {
//	alert(event.keyCode);
//	if (event.keyCode == 13) {
//		alert('click enter');
//	}
//}