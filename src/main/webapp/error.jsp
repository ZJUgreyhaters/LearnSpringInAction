<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>出错啦！</title>
	</head>
	<body>
		<h2>很抱歉，服务器出错误啦</h2>
		<p style="color:red">
		    服务器出现临时性错误，请稍后再登录系统进行操作，或联系系统管理员。
		</p>
		<hr/>
		<a href="javascript:void(0)" onclick="window.close();return false">关闭</a>
		<hr/>	
		<h3>错误消息</h3>
		<p>
		    <s:property value="%{exception.message}"/>
		</p>
		<hr/>
		<h3>详细错误信息</h3>
		<p>
		    <s:property value="%{exceptionStack}"/>
		</p>
	</body>
</html>
