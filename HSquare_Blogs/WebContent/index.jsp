<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Happiness Blogs</title>
<link href="static/index/css/index.css" rel="stylesheet" />
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="static/index/js/index.js"></script>
</head>
<body>
	<header id="header" class="header">
		<div class="title">
			
		</div>
		<div class="daohang">
			
		</div>
		<div class="user">
			<a href="blogs/addBlog.do" target="_blank">发布Blog</a>
		</div>
	</header>
	
	<section id="section" class="section">
		<iframe name="iframe" id="iframe" src="blogs/index.do?type=index" scrolling="no" frameborder="0" height="100%" id="mainFrame" width="100%"></iframe>
	</section>
	
	<footer id="footer" class="footer">
		
	</footer>
</body>
</html>