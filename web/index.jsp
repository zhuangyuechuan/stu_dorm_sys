<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
	<title>登录</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  	<meta name="viewport" content="width=device-width,initial-scale=1.0">
  	<script type="text/javascript" src="/stu_dorm_sys/bootstrap/js/jQuery.js"></script>
  	
	<script type="text/javascript">
		function checkForm() {
			var stuCode = document.getElementById("stuCode").value;
			var password = document.getElementById("input_password").value;
			if(stuCode == null || stuCode == ""){
				document.getElementById("error").innerHTML = "学号不能为空";
				return false;
			}
			if(password == null || password == ""){
				document.getElementById("error").innerHTML = "密码不能为空";
				return false;
			}
			return true;
		}
		
		$(document).ready(function(){
			$("#login").addClass("active");
		});  
		
	</script>
	
	<style type="text/css">
	
        * {
            margin: 0px;
            padding: 0px;
        }
    
        body {
            width: 270px;
            height: 600px;
            font-family:"宋体";
			font-size:16px;
            font-weight: bold;
		    background-image: url(image/login_bg.jpg);  
            background-repeat: no-repeat;
            background-size: 110% 120%;
            color: gray;
        }
		
        .login {
            position: absolute;
            width: 300px;
            height: 320px;
            background-color:rgba(0,0,0,0.7); 
            /* background-color:white; */
            margin-left: 20%;
            margin-top: 150px;
            line-height: 60px;
            border: 3px solid green;
            border-radius:40px;
            text-align: center;
        }
   
        .login_title {
            font-size: 20px;
        }

		.err {
			line-height: 16px;
			height: 16px;
			
		}

		.checkbox {
			margin:0px;
			padding:0px;
		}

        #stuCode,#input_password {
            border: 1px solid rgba(0,0,0,0.45);           
            background-color: rgba(216,216,216,0.7);
            border-radius: 6px;
            padding: 7px;
        }
        
        #submit {
            text-shadow: 1px -1px 0px black;/*#5b6ddc*/ 
            border: 1px solid rgba(0,0,0.4);
            background-clip: padding-box;   /* 属性规定背景的绘制区域。背景被裁剪到内边距框。*/
            border-radius: 6px;
            cursor:pointer;  /*光标形状*/
            background-color: rgba(0,216,0,0.5);
            color: blue;
            padding: 8px 15px;
            font-size: 16px;
        }
	
	
	</style>
	
  </head>
  


	<body style="width: 100%;height: 100%;">
	
		<div class="login">
	    <form class="form_login" action="login" method="post" target="_blank" onsubmit="return checkForm()" >
	        <label class="login_title"><font color="gray">学生宿舍管理系统</font></label>
	    
	        <br />
	        <label class="username">学号:&emsp;</label>
	        <input id="stuCode" type="text" name="stuCode" placeholder="学号..." maxlength="18" />
	        <br />
	        <label class="password">密码:&emsp;</label>
	        <input id="input_password" type="password" name="password" placeholder="Password..." maxlength="18"/>
	        <br>        
	        <label class="checkbox">
	        	&nbsp;<input id="remember" name="remember" type="checkbox" value="remember-me">&nbsp;<font color="gray">记住我</font>&nbsp;   
	        </label>
	        
	        <div class="err">
	        	<font id="error" color="red">${error}</font>	
	        </div>
	        
	     	<div>
	        <button id="submit" type="submit">登&emsp;录</button>
	       </div>
		</form>
	    </div>
	     
	</body>  



</html>
































