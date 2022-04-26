<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
	
  </head>
  
  <body>
	<div class="blank" style="padding-top:30px; width:600px; padding-left:40px;" >
		
		<font color="black" size="20">&nbsp;&nbsp;&nbsp;&nbsp;欢迎登录宿舍管理系统</font>
		<div style="width:100%;height:68px;"></div>
		<jsp:include page="${mainRight==null ? 'swiper.jsp' : mainRight}"></jsp:include>
		
	</div>
  </body>
</html>
