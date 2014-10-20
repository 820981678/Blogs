<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Blogs</title>
<link href="${webRoot}static/blogs/css/index.css" rel="stylesheet" />
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type='text/javascript'>
	//重新设置父页面iframe的高度,便于自适应该页面高度
	$("#iframe",window.parent.document).height(0);
	$(function(){
		var height = $(document).height();
		parent.initHeight(height);
	});
</script>
</head>

<body>
	<!-- blogs list -->
	<div style="width: 770px; height: auto; float: left;">
		<div style="width:770px; height:200px; background-color: white; margin-bottom:10px; ">
			<div style="padding: 20px 15px 20px 20px;">
				<!-- title -->
				<div style="width:auto; height:30px; line-height:30px; font-size:18px; color:#1ABC9C; ">
					Java  网页爬虫及其用到的算法和数据结构
				</div>
			</div>
		</div>
		<#list [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]  as i>
		</#list>
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
