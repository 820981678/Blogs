<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Blogs</title>
<link href="${webRoot}static/blogs/css/index.css" rel="stylesheet" />

<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script src="${webRoot}laytpl/laytpl.js"></script>

<script type='text/javascript'>
	
	//全局转换 BTag
	var global_btag;
	
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
				
				global_btag = data.global_btag;
				
				//获取模板渲染的数据
				var list = data.page;
				var gettpl = document.getElementById('demo').innerHTML;
				laytpl(gettpl).render(list, function(html){
				    document.getElementById('view').innerHTML = html;
				});
				
				//重新设置父页面iframe的高度,便于自适应该页面高度
				$("#iframe",window.parent.document).height(0);
				var height = $(document).height();
				parent.initHeight(height);
			}
		})
	});
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
				<span style="margin-right:15px;">时间: <span style="color:#00a67c"></span>{{d.result[i].createTime}}</span>
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
</style>
</head>

<body>
	<!-- blogs list -->
	<div id="view" style="width: 770px; height: auto; float: left;">
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
	</div>
	<div style="width: 300px; height: 100%; float: left; margin-left: 10px;">
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
