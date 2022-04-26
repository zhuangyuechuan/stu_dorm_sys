<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function checkForm(){
		//检查用户是否输入宿舍楼名字
		var name=document.getElementById("name").value;
		if(name==null||name==""){
			document.getElementById("error").innerHTML="名称不能为空！";
			return false;
		}
		return true;
	}
	$(document).ready(function(){
		$("#dormBuild").addClass("active");
	});	
</script>
<style>
.data_list{
margin-top:105px;
margin-left:48px;
}
textarea{
width:509px;
height:258px;
resize:none;
outline:none;
}
textarea:hover{
	border:1px solid #4488BB;
	box-shadow:0 0 5px #4488BB;
}
input{
outline:none;
}
.data_list_title{
font-size:30px;
padding-bottom:10px;
}
</style>
<div class="data_list">
		<div class="data_list_title">
			 <c:if test="${build.id == null }">
			 添加宿舍楼
			 </c:if>
			 <c:if test="${build.id != null }">
			 修改宿舍楼
			 </c:if>
			 
		</div>
		
		
		<form action="dormBuild.action?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="id" name="id" value="${build.id}"/>
				<table align="center">
					<tr>
						<td class="pd"><font color="red">*</font>名称：</td>
						<td><textarea type="text" id="name"  name="name" value="${build.name}"  style="margin-top:5px;height:30px;" ></textarea></td>
					</tr>
					<tr>
						<td>&nbsp;简介：</td>
						<td><textarea id="remark" name="remark" rows="10">${build.remark}</textarea></td>
					</tr>
				</table>
				<div align="center">
					<!--type="submit"  意味着会将表单中的内容提交到action属性值dormBuild.action?action=save的请求处理类中  -->
					<input type="submit" class="btn btn-primary" style="background-color:skyblue;border:1px solid #fff;box-shadow:0 0 5px skyblue;" value="保存"/>
					&nbsp;<button class="btn btn-primary" type="button" style="background-color:orange;border:1px solid #fff;box-shadow:0 0 5px orange;"  onclick="javascript:history.back()">返回</button>
				</div>
				<div align="center">
					<font id="error" color="red">${error}</font>
				</div>
			</div>
		</form>
</div>