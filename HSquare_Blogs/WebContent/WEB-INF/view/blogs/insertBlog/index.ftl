<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布blog</title>

<!-- 本地 -->
<link href="${webRoot}static/blogs/insertBlog/index.css" rel="stylesheet" />
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${webRoot}static/blogs/insertBlog/index.js"></script>

<!-- cookie -->
<script type="text/javascript" src="${webRoot}plug/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${webRoot}static/global/js/global.cookie.js"></script>

<!-- icheck -->
<script type="text/javascript" src="${webRoot}plug/icheck/icheck.min.js"></script>
<link href="${webRoot}plug/icheck/green.css" rel="stylesheet" />

<!-- js模板引擎 -->
<script src="${webRoot}plug/laytpl/laytpl.js"></script>

<!-- ueditor -->
<script type="text/javascript" charset="utf-8" src="${webRoot}plug/ueditor_min/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${webRoot}plug/ueditor_min/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${webRoot}plug/ueditor_min/lang/zh-cn/zh-cn.js"></script>
<link href="${webRoot}plug/ueditor_min/themes/default/css/ueditor.css" rel="stylesheet" type="text/css" />

<!-- button css-->
<link rel="stylesheet" type="text/css" href="${webRoot}plug/button/button.css" />

</head>

<script>
	var webRoot = ${webRoot};
</script>

<body style="background: #eee;">
	<header id="header" class="header">
		<#include "/include/include_title.ftl" >
	</header>
<form id="blogForm">
	<section style="width: 1080px; height: 1060px; margin-top: 10px; margin: 10px auto;">
		
		<!-- 基本信息 -->
		<div  style="width:1080px; height:auto; margin-bottom:10px; float:left;">
			<div style="width:100%; height:200px; background: white; margin-bottom:10px;">
				<div style="width:100%; heigth:30px; line-height:30px; border-bottom: 2px solid #1ABC9C; ">
					<strong style="margin-left:10px;">文章信息</strong>
				</div>
				<input id="overview" type="hidden" name="overview" />
				<input id="state" type="hidden" name="state" />
				<div style="width:100%; height:50px; line-height:50px; padding-left:10px; margin-top:10px;">
					类型:
					<#list btype as type>
						<input class="radio_1" type="radio"  value="${type.getKey()}" name="btype" />
						<lable>${type.getName()}</lable>
					</#list>
					<span style="color:#ccc">*只有原创和翻译文章才能推荐到首页<span>
				</div>
				<div style="width:100%; height:50px; line-height:50px; padding-left:10px;">
					标签:
					<#list btag as tag>
						<input type="radio" value="${tag.id}" name="btagId" />
						<lable>${tag.tagName}</lable>
					</#list>
				</div>
				
				<div style="width:100%; height:50px; line-height:50px; padding-left:10px;">
					标题:
					<input type="text" name="title" style="height:20px; width:810px;" />
				</div>
			</div>
			
			<!-- 文章内容 -->
			<div style="width:100%; height:auto; background: white; margin-bottom:10px;">
				
				<div style="width:100%; margin-top:5px;">
					<!--编辑器-->
					<script id="editor" type="text/plain" style="width:100%;height:540px;"></script>
					<script type="text/javascript">
					    //实例化编辑器(表单提交参数名为:editorValue
					    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
					    var ue = UE.getEditor('editor');
		    		</script>
				</div>
			</div>
</form>
			<!-- 提示 -->
			<div style="width:100%; height:150px; background: white; margin-bottom:10px;">
				<div style="width:100%; heigth:30px; line-height:30px; border-bottom: 2px solid red; ">
					<strong style="margin-left:10px; color:red">提示</strong>
				</div>
				<div style="width:100%; height:35px; line-height:35px; padding-left:10px;">
					1、推荐到首页必需是高质量、对别人有帮助的文章
				</div>
				<div style="width:100%; height:35px; line-height:35px; padding-left:10px;">
					2、发布到首页后，一旦被管理员撤下，3天内将不能再发布到首页
				</div>
				<div style="width:100%; height:35px; line-height:35px; padding-left:10px;">
					3、请不要发布任何推广、广告（包括招聘）、政治、低俗等方面的内容，不要把博客当作SEO工具，否则可能会影响到您的使用。
				</div>
			</div>
			
			<!-- 操作栏 -->
			<div style="width:100%; height:50px; line-height:50px;  text-align:center;">
				<a href="javascript:void(0)" class="button_1" id="release" >发  布</a>
				<a href="javascript:void(0)" class="button_2" id="save">保存</a>
				<a href="javascript:void(0)" class="button_3" id="delete">丢弃</a>
			</div>
		</div>
	</section>
	
	<footer class="footer">
		<#include "/include/include_footer.ftl" >
	</footer>
    
</body>
</html>
