/**
 * 用于读取cookie
 * 需要jQuery的支持
 * 需要webRoot支持
 */
$(function(){
	var user = $.cookie("user");
	//如果有cookie记录
	if(user != "" && user != undefined && user != null && user != "null"){
		var gettpl = document.getElementById('yes').innerHTML;
		laytpl(gettpl).render(user, function(html){
		    $('#userShow').html(html);
		});
	} else {
		var myObj =
	    {
	        'id': 1,        //属性名用引号括起来，属性间由逗号隔开
	        'name': 'myName'
	    };
		var gettpl = document.getElementById('no').innerHTML;
		laytpl(gettpl).render(myObj,function(html){
		    $('#userShow').html(html);
		});
	}
});