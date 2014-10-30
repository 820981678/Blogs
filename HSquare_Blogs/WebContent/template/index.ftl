<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Happiness Blogs</title>

<!-- 本地 -->
<link href="../static/blogs/index/css/index.css" rel="stylesheet" />
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../plug/ueditor/ueditor.parse.min.js"></script>

</head>
<body>
	<header id="header" class="header">
		<div class="title">
			<div style="width:1080px; height:100%; margin:0px auto; font-size:18px; text-align:center; line-height:100px; color: white; ">
				<span style="font-size:55px;">Happiness Blog</span>
				<span style="font-size:24px;">—分享是最高尚的自私</span>
			</div>
		</div>
		<div class="daohang">
			<div style="width:1080px; height:100%; margin:0px auto; font-size:18px;">
				<ul style="list-style:none; padding:0px; margin:0px; ">
					<li style="width:auto; height:50px; float:left; line-height:50px; cursor:pointer; ">
						<a href="blogs/index.do" style="padding:13px 20px; color:white;">HOME</a>
					</li>
					<li style="width:auto; height:50px; float:left; line-height:50px; cursor:pointer; ">
						<a href="" style="padding:13px 20px; color:white;">TAG</a>
					</li>
					<li style="width:auto; height:50px; float:left; line-height:50px; cursor:pointer; ">
						<a href="" style="padding:13px 20px; color:white;">ABOUT</a>
					</li>
					<li style="width:auto; height:50px; float:left; line-height:50px; cursor:pointer; ">
						<a href="javascript:void(0);" style="padding:13px 20px; color:white; background-color: #1ABC9C"">${blog.btagName}</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="user">
			
		</div>
	</header>
	
	<section id="section" class="section">
		<div style="width: 870px; height: auto; float: left; background-color:white;">
			<!-- 文章基本信息 -->
			<div style="width:100%; height:105px; border-bottom:1px solid #eee; margin-bottom:10px;">
				<div style="padding: 2px 20px 12px;">
					<h1 style="color: #444; font-weight: normal; font-size: 24px; line-height: 60px; margin: 5px 0;">${blog.title}</h1>
					<div style="width:100%; height:20px; color:#999; ">
						<span style="margin-right:15px;">发布人: <span style="color:#00a67c">${blog.userName}</span></span>
						<span style="margin-right:15px;">时间: <span style="color:#00a67c">${blog.updateTime?string("yyyy-MM-dd HH:mm:ss")}</span></span>
						<span style="margin-right:15px;">点击量: <span style="color:#00a67c">${blog.checkNum}</span></span>
						<span style="margin-right:15px;">评论: <span style="color:#00a67c">100</span></span>
						<span style="margin-right:15px;">点赞: <span style="color:#00a67c">100</span></span>
					</div>
				</div>
			</div>
			<!-- 广告 -->
			<div style="width:100%; height:150px; ">
				
			</div>
			<!-- 博客正文 -->
			<div style="width:100%; height:auto;">
				<div id="blog" style="padding:10px;">
					${blogText}
				</div>
			</div>
			<!-- 点赞 -->
			<div style="width:100%; height:auto;">
				
			</div>
		</div>
		
		<div style="width: 200px; height: 100%; float: left; margin-left: 10px;">
			<div style="width: 100%; height: 500px; background-color: white; margin-bottom: 10px;">
				<ul style="list-style:none; padding:0px; margin:0px; color:#1ABC9C; ">
					<li style=" height:40px; line-height:40px; padding-left:10px; font-size:18px; border-bottom:1px solid #eee;">
						TA的博客
					</li>
				</ul>
			</div>		
			<!-- 阅读排行 -->
			<div style="width: 100%; height: 350px; background-color: white; margin-bottom: 10px;">
				<ul style="list-style:none; padding:0px; margin:0px; color:#1ABC9C; ">
					<li style="height:40px; line-height:40px; padding-left:10px; font-size:18px;  border-bottom:1px solid #eee;">
						阅读排行
					</li>
				</ul>
			</div>
			<!-- 猜你喜欢 -->
			<div style="width: 100%; height: 350px; background-color: white; margin-bottom: 10px;">
				<ul style="list-style:none; padding:0px; margin:0px; color:#1ABC9C; ">
					<li style="height:40px; line-height:40px; padding-left:10px; font-size:18px;  border-bottom:1px solid #eee;">
						猜你喜欢
					</li>
				</ul>
			</div>
		</div>
	</section>
	
	<footer id="footer" class="footer">
		
	</footer>
	
	<script type="text/javascript">
		uParse('#blog', {
		    rootPath: '../plug/ueditor'
		});
	</script>
</body>
</html>