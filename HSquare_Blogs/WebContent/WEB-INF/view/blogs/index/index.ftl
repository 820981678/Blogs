<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Happiness Blogs</title>

<!-- 本地 -->
<link href="${webRoot}static/blogs/index/css/index.css" rel="stylesheet" />
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${webRoot}static/blogs/index/js/index.js"></script>

<!-- js模板引擎 -->
<script src="${webRoot}plug/laytpl/laytpl.js"></script>

<script type="text/javascript">
	var webRoot = ${webRoot};
</script>

<!-- js模板引擎 -->
<script id="demo" type="text/html">
	{{# if(d.result.length == 0){ }}
		<div style="width:770px; height:30px; line-height:30px; text-align: center; color:#777;">
			这里什么都没有,期待您的添加!
		</div>
	{{# } }}
	{{# for(var i = 0, len = d.result.length; i < len; i++){ }}
	<div style="width:770px; height:auto; background-color: white; margin-bottom:10px; ">
		<div style="padding: 20px 15px 10px 20px;">
			<!-- title -->
			<div style="width:auto; height:30px; line-height:30px; font-size:18px; color:#1ABC9C; margin-bottom:10px;">
				<a style="position: relative; margin-right:5px; color: #fff; background-color: #d9534f; font-size: 14px; padding: 2px 2px 2px 6px; top:-2px;" href="http://www.cricode.com/category/os/android">
					{{d.result[i].btagName}}
				</a>
				<a href="{{d.result[i].template}}" style="font-weight: normal;" target="_blank" title="{{d.result[i].title}}" >{{d.result[i].title}}</a>
			</div>
			<div style="width:auto; line-height:24px; color:#777; font-family: Microsoft Yahei,Helvetica Neue,Helvetica,Arial,sans-serif; font-size: 14px; margin-bottom:10px; word-wrap:break-word; word-break:break-all;">
				{{d.result[i].overview}}
			</div>
			<div style="width:auto; height:30px; line-height:30px;  color:#999; text-align:right;">
				<span style="margin-right:15px;">发布人: <span style="color:#00a67c">{{d.result[i].userName}}</span></span>
				<span style="margin-right:15px;">时间: <span style="color:#00a67c">{{= new Date(d.result[i].createTime).format('yyyy-MM-dd hh:mm:ss') }}</span></span>
				<span style="margin-right:15px;">点击量: <span style="color:#00a67c">{{d.result[i].checkNum}}</span></span>
				<span style="margin-right:15px;">评论: <span style="color:#00a67c">100</span></span>
				<span style="margin-right:15px;">点赞: <span style="color:#00a67c">100</span></span>
			</div>
		</div>
	</div>
	{{# } }}
</script>
<script id="hotblog" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
	<li style="width:100%; height:30px; line-height:30px; padding-left:10px; border-top: 1px solid #eee; cursor:pointer; ">
		<a href="{{d[i].TEMPLATE}}" >{{d[i].TITLE}}</a>
	</li>
	{{# }}}
</script>

</head>
<body>
	<header id="header" class="header">
		<#include "/include/include_title.ftl" >
	</header>
	
	<section id="section" class="section">
		<!-- blogs list -->
		<div style="width: 770px; height: auto; float: left;">
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
					<li style="height:40px; line-height:40px; padding-left:10px; font-size:18px;">
						<strong style="color:#555">文章标签</strong>
					</li>
					<#list global_btag as btag>
						<li style="width:100%; height:30px; line-height:30px; padding-left:10px; border-top: 1px solid #eee; cursor:pointer; ">
							<a href="javascript:void(0);" onclick="queryBtag('${webRoot}blogs/query_ajax?btagId=${btag.id}')">${btag.tagName}</a>
						</li>
					</#list>
				</ul>
			</div>
			<!-- 热门文章 -->
			<div style="width: 100%; height: auto; background-color: white; margin-bottom: 10px;">
				<ul id="hotblogView" style="list-style:none; padding:0px; margin:0px; color:#1ABC9C; ">
					<li style="height:40px; line-height:40px; padding-left:10px; font-size:18px;">
						<strong style="color:#555">热门文章</strong>
					</li>
				</ul>
			</div>
			<!-- 推荐阅读 -->
			<div style="width: 100%; height: 500px; background-color: white; margin-bottom: 10px;">
				<ul style="list-style:none; padding:0px; margin:0px; color:#1ABC9C; ">
					<li style="height:40px; line-height:40px; padding-left:10px; font-size:18px;">
						<strong style="color:#555">推荐阅读</strong>
					</li>
				</ul>
			</div>
		</div>
	</section>
	
	<footer id="footer" class="footer">
		<#include "/include/include_footer.ftl" >
	</footer>
</body>
</html>