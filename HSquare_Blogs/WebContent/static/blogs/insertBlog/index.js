$(function(){
	//注册发布按钮
	$("#release").click(function(){
		$("#state").val(1);
		sub();
	});
	//注册保存按钮
	$("#save").click(function(){
		$("#state").val(2);
		sub();
	});
});

function sub(){
	//初始化提交表单
	var arr = [];
    arr.push(UE.getEditor('editor').getContentTxt());
    var overview = arr.join();
    if(overview.length > 200){
    	overview = overview.substring(0,190);
    } 
	$("#overview").val(overview);
	
	$.ajax({
		url: webRoot + 'blogs/release',
		type:'post',
		data: $("#blogForm").serialize(),
		dataType:'json',
		success:function(data){
			if(data.code == 0){
				alert("操作成功!");
			} else{
				alert("服务器异常,请稍后再试!");
			}
		}
	});
}