/**
 * 加载iframe后重新调整他的高度
 */
function initHeight(height){
	$("#iframe").height(0);
	$("#iframe").height(height);
}

/**
 * 日期格式 将long类型的日期 格式化为 format的格式
 * new Date(datetimelong).format('yyyy-MM-dd')
 */
Date.prototype.format =function(format) {
    var o = {
    "M+" : this.getMonth()+1, //month
    "d+" : this.getDate(), //day
    "h+" : this.getHours(), //hour
    "m+" : this.getMinutes(), //minute
    "s+" : this.getSeconds(), //second
    "q+" : Math.floor((this.getMonth()+3)/3), //quarter
    "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
    (this.getFullYear()+"").substr(4- RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,
    RegExp.$1.length==1? o[k] :
    ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}

/**
 * 用于分页
 */
var nextPage;
var pageSize;
var hasNext;

/**
 * 记录当前请求url, 用于分页
 */
var queryURL; 

$(function(){
	$.ajax({
		url: 'blogs/query_ajax.do',
		type: 'post',
		dataType: 'json',
		success: function(data){
			if(data.code != 0){
				$("#view").append("数据加载失败!");
				return;
			}
			view(data);
			createParentHeight();
			nextpage(data);
			
			//设置当前请求url
			queryURL = 'blogs/query_ajax.do';
		}
	});
});

//渲染js模板
function view(data){
	//获取模板渲染的数据
	var list = data.page;
	var gettpl = document.getElementById('demo').innerHTML;
	laytpl(gettpl).render(list, function(html){
	    $('#view').append(html);
	});
}

//重新计算父页面高度
function createParentHeight(){
	//重新设置父页面iframe的高度,便于自适应该页面高度
	$("#iframe",window.parent.document).height(0);
	var height = $(document).height();
	parent.initHeight(height);
}

//设置分页项
function nextpage(data){
	nextPage = data.page.nextPage;
	pageSize = data.page.pageSize;
	hasNext = data.page.hasNext;
	//验证是否还有下一页
	if(hasNext){
		$("#nextpage").css("background-color","#1ABC9C");
		$("#nextpage").html("Next Page");
	} else {
		$("#nextpage").css("background-color","#555");
		$("#nextpage").html("Not Have Next");
	}
}

$(function(){
	//注册下一页事件
	$("#nextpage").click(function(){
		//判断是否有下一页
		if(!hasNext){
			return;
		}
		$.ajax({
			url: queryURL,
			data: {'pageNo':nextPage,'pageSize':pageSize},
			type: 'post',
			dataType: 'json',
			success: function(data){
				if(data.code != 0){
					$("#view").append("数据加载失败!");
					return;
				}
				view(data);
				createParentHeight();
				nextpage(data);
			}
		});
	});
});

function queryBtag(URL){
	$.ajax({
		url: URL,
		type: 'post',
		dataType: 'json',
		success: function(data){
			if(data.code != 0){
				$("#view").append("数据加载失败!");
				return;
			}
			//清空原有的数据(重新加载根据btagid查询的数据)
			$("#view").html("");
			view(data);
			createParentHeight();
			nextpage(data);
			
			//设置当前请求url
			queryURL = URL;
		}
	});
}