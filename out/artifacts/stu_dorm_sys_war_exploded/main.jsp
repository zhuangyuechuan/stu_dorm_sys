<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
  
  	<title>标题</title>
	
	<style type="text/css">
    
		body{
			background-color:#fff;
		}
		
		.head {
		background-color:#4488BB;
			/*background-color:rgba(0,0,0,0.2);*/
			/* text-align: left; */
			height: 80px;
			width: 100%;
			font-size: 40px;
			color:black;
			line-height: 80px;
		}
		
		.left {
			margin-top:100px;
			margin-left:10px;
			padding:0px;
			/* background-color:rgba(0,0,0,0.55); */
			float:left;
			width:268px;
			/* line-height: 20px; */
			font-size: 20px;
			text-align: left;
			text-indent:2em; 
			border: 2px solid black;
			border-radius:20px; 
			overflow:hidden;
			
			
		}
		
		ul {
			padding:0px;
			margin:0px;
			list-style:none;
			
			
		}
	
		.hr {
			width:270px;
			border-top:2px solid black;
			
		}
		
		ul li:nth-child(n):hover{
		background-color:#4488BB;
		
		}
		ul span:nth-child(n){
		padding-right:30px;
		
		}
		
		a {
			color:black;
			line-height:60px;
			text-decoration:none;
			/* display:inline-block; */
			/* height:inherit;  */
			
		}	
				
		.content {
			margin-left:300px;
		}
		
		.jiantou {
			float: right;
			
			
		}
		.footer {
			background-color:#4488BB;
			/*background-color:rgba(0,0,0,0.2);*/
			/* text-align: left; */
			height: 80px;
			width: 100%;
			font-size: 40px;
			color:black;
			line-height: 80px;
			margin-top:10px;
		}
		
	</style>
	
  </head>
  
  
  
  
  <body>
	<div style="width:100%; margin:0px auto;">
		
		<div class="head">
			<div style="float:left;">&nbsp;&nbsp;宿舍管理系统</div>
			<div style="float:right; padding-top:66px; padding-right:20px; font-size:16px;">当前用户:&nbsp;<font color="blue">${session_user.name} </font></div>
		</div>
		<div style="overflow:hidden">
		<div class="left">
			
            <ul>
            
            <!-- 超级管理员 -->
            <c:if test="${session_user.roleId == 0}">
               <a href="index.action"><li><!-- <img src="image/home.png" alt=""> -->首页<span class="jiantou">></span></li></a>
               <a href="dormManager.action?action=list"><li class="hr">宿舍管理员管理<span class="jiantou">></span></li></a>
               <a href="student.action?action=list"><li class="hr">学生管理<span class="jiantou">></span></li></a>
               <a href="dormBuild.action?action=list"><li class="hr">宿舍楼管理<span class="jiantou">></span></li></a>
               <a href="absenceManage.action?action=list"><li class="hr">缺勤记录<span class="jiantou">></span></li></a>
               <a href="password.action?action=preChange"><li class="hr">修改密码<span class="jiantou">></span></li></a>
               <a href="#"><li class="hr">报备<span class="jiantou">></span></li></a>
               <a href="loginOut.action"><li class="hr">退出系统<span class="jiantou">></span></li></a>
               <a href="#"><li class="hr">其他<span class="jiantou">></span></li></a>
            </c:if>
            
            <!-- 宿舍管理员 -->
            <c:if test="${session_user.roleId == 1}">
               <a href="index.action"><li>首页</li></a>
               <a href="student.action?action=list"><li class="hr">学生管理</li></a>
               <a href="dormBuild.action?action=list"><li class="hr">缺勤记录</li></a>
               <a href="password.action?action=preChange"><li class="hr">修改密码</li></a>
               <a href="#"><li class="hr">报备</li></a>
               <a href="loginOut.action"><li class="hr">退出系统</li></a>
               <a href="#"><li class="hr">其他</li></a>
            </c:if>   
               
            <!-- 学生 -->
            <c:if test="${session_user.roleId == 2}">
               <a href="index.action"><li><!-- <img src="image/home.png" alt=""> -->首页</li></a>
               <a href="#"><li class="hr">缺勤记录</li></a>
               <a href="password.action?action=preChange"><li class="hr">修改密码</li></a>
               <a href="#"><li class="hr">报备</li></a>
               <a href="loginOut.action"><li class="hr">退出系统</li></a>
               <a href="#"><li class="hr">其他</li></a>
            </c:if>   
               
               
            </ul>
         
		</div>
		
		<div class="content">
			<!-- 右侧内容 -->
			<jsp:include page="${mainRight==null ? 'blank.jsp' : mainRight}"></jsp:include>
		</div>
		</div>
		<div class="footer"></div>
		
		
		
	</div>
		
  </body>
  
</html>
