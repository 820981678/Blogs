/**
 * 用于读取cookie
 * 需要jQuery的支持
 */
$(function(){
	var user = $.cookie("user");
	//如果有cookie记录
	if(user != "" || user != undefined){
		$("#userShow").html("");
		$("#userShow").append('<span style="color:#1ABC9C;margin-right:10px;" >欢迎您: <a href="">' + user + '</a></span>');
		$("#userShow").append('<span style="color:#1ABC9C;margin-right:10px;" ><a href="" style="text-decoration: underline;">写博客</a></span>');
	} else {
		$("#userShow").html("");
		$("#userShow").append('<span style="color:#1ABC9C;margin-right:10px;" >欢迎您: <a href="">登陆</a></span>');
	}
});