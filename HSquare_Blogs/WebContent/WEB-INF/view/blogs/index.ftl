<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Blogs</title>
<link href="${webRoot}static/blogs/css/index.css" rel="stylesheet" />

<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script src="${webRoot}plug/laytpl/laytpl.js"></script>

<script type='text/javascript'>

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
			url: '${webRoot}blogs/query_ajax.do',
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
				queryURL = '${webRoot}blogs/query_ajax.do';
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
</script>
<!-- js模板引擎 -->
<script id="demo" type="text/html">
	{{# for(var i = 0, len = d.result.length; i < len; i++){ }}
	<div style="width:770px; height:auto; background-color: white; margin-bottom:10px; ">
		<div style="padding: 20px 15px 10px 20px;">
			<!-- title -->
			<div style="width:auto; height:30px; line-height:30px; font-size:18px; color:#1ABC9C; margin-bottom:10px;">
				<a style="position: relative; margin-right:5px; color: #fff; background-color: #d9534f; font-size: 14px; padding: 2px 2px 2px 2px; top:-2px;" href="http://www.cricode.com/category/os/android">
					{{d.result[i].btagName}}
				</a>
				<a href="#" style="font-weight: normal;" target="_blank" title="">{{d.result[i].title}}</a>
			</div>
			<div style="width:auto; line-height:24px; color:#777; font-family: Microsoft Yahei,Helvetica Neue,Helvetica,Arial,sans-serif; font-size: 14px; margin-bottom:10px;">
				{{d.result[i].overview}}
			</div>
			<div style="width:auto; height:30px; line-height:30px;  color:#999;">
				<span style="margin-right:15px;">发布人: <span style="color:#00a67c"></span>{{d.result[i].userName}}</span>
				<span style="margin-right:15px;">时间: <span style="color:#00a67c"></span>{{= new Date(d.result[i].createTime).format('yyyy-MM-dd hh:mm:ss') }}</span>
				<span style="margin-right:15px;">点击量: <span style="color:#00a67c"></span>{{d.result[i].checkNum}}</span>
				<span style="margin-right:15px;">评论: <span style="color:#00a67c"></span>100</span>
				<span style="margin-right:15px;">点赞: <span style="color:#00a67c"></span>100</span>
			</div>
		</div>
	</div>
	{{# } }}
</script>
<style>
	a {
		color: #00a67c;
		text-decoration: none;
	}
	
	a:hover{
		color: #d9534f;
	}
</style>
</head>

<body>
	<!-- blogs list -->
	<div style="width: 770px; height: auto; float: left;">
		<!--
		<#list page.result  as blog>
			<div style="width:770px; height:auto; background-color: white; margin-bottom:10px; ">
				<div style="padding: 20px 15px 10px 20px;">
					
					<div style="width:auto; height:30px; line-height:30px; font-size:18px; color:#1ABC9C; margin-bottom:10px;">
						<a style="position: relative; color: #fff; background-color: #d9534f; font-size: 14px; padding: 2px 4px 2px 2px; top:-2px;" href="http://www.cricode.com/category/os/android">${queryBtag(blog.btagId).getTagName()}</a>
						<a href="#" style="font-weight: normal;" target="_blank" title="网页爬虫及其用到的算法和数据结构">${blog.title}</a>
					</div>
					<div style="width:auto; line-height:24px; color:#777; font-family: Microsoft Yahei,Helvetica Neue,Helvetica,Arial,sans-serif; font-size: 14px; margin-bottom:10px;">
						${blog.overview}
					</div>
					<div style="width:auto; height:30px; line-height:30px;  color:#999;">
						<span style="margin-right:15px;">发布人: <span style="color:#00a67c">${blog.userName}</span></span>
						<span style="margin-right:15px;">时间: <span style="color:#00a67c">${blog.createTime}</span></span>
						<span style="margin-right:15px;">点击量: <span style="color:#00a67c">${blog.checkNum}</span></span>
						<span style="margin-right:15px;">评论: <span style="color:#00a67c">1000</span></span>
						<span style="margin-right:15px;">点赞: <span style="color:#00a67c">1000</span></span>
					</div>
				</div>
			</div>
		</#list>
		-->
		<!-- js模板渲染层 -->
		<div id="view" style="width: 770px; height: auto;">
		
		</div>
		<div id="nextpage" style="width:770px; height:30px; line-height:30px; background-color: #1ABC9C; color: white; text-align: center; cursor:pointer;">
			Next Page
		</div>
	</div>
	
	<div style="width: 300px; height: 100%; float: left; margin-left: 10px;">
		<!-- 导航 -->
		<div style="width: 100%; height: auto; background-color: white; margin-bottom: 10px;">
			<ul style="list-style:none; padding:0px; margin:0px; color:#1ABC9C; ">
				<li style="width:100%; height:40px; line-height:40px; padding-left:10px; font-size:18px;">
					文章标签
				</li>
				<#list global_btag as btag>
					<li style="width:100%; height:30px; line-height:30px; padding-left:10px; border-top: 1px solid #eee; cursor:pointer; ">
						<a href="javascript:void(0);" onclick="queryBtag('${webRoot}blogs/query_ajax.do?btagId=${btag.id}')">${btag.tagName}</a>
					</li>
				</#list>
			</ul>
		</div>
		<!-- 热门排行 -->
		<div style="width: 100%; height: 500px; background-color: white; margin-bottom: 10px;">
			
		</div>
		<!-- 最新发布 -->
		<div style="width: 100%; height: 300px; background-color: white; margin-bottom: 10px;">
			
		</div>
		<!-- 猜你喜欢 -->
		<div style="width: 100%; height: 300px; background-color: white; margin-bottom: 10px;">
			
		</div>
	</div>
</body>
</html>
