<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Happiness Blogs</title>
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${webRoot}plug/js/jquery.cookie.js"></script>
<script type="text/javascript">
	$(function(){
		alert($.cookie("user"));
	});
</script>

</head>
<body>
	<form action="${webRoot}login/doLogin" method="post">
		<input type="text" name="name" /> <br/><br/>
		<input type="password" name="password" /> <br/><br/>
		<input type="checkbox" name="isSave" value="true" />保存一周 <br/><br/>
		<input type="submit" value="Login" />
	</form>
</body>
</html>