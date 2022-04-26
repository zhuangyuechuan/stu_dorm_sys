package com.dorm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dorm.bean.DormBuild;
import com.dorm.bean.User;
import com.dorm.service.DormBuildService;
import com.dorm.service.impl.DormBuildServiceImpl;
import com.dorm.service.UserService;
import com.dorm.service.impl.UserServiceImpl;
import com.dorm.util.PageModel;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/student.action")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
       
    }

    
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("=============student.action============");
		
		//解决传递过来的中文乱码问题
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		
		//获取学生id
		String id = request.getParameter("id");
		String disabled = request.getParameter("disabled");
		
		//获取当前登录的用户
		User user = (User) request.getSession().getAttribute("session_user");
		Integer roleId = user.getRoleId();
				
		DormBuildService buildService = new DormBuildServiceImpl();
		UserService userService = new UserServiceImpl();
		
		
		List<DormBuild> builds = new ArrayList<DormBuild>();
		if (roleId.equals(0)) {
			//如当前用户为超级管理员，他能查询到所有的宿舍楼,还能添加学生到任何宿舍楼
			builds = buildService.findAll();
			
		}else if(roleId.equals(1)) {
			//如当前用户为宿舍管理员，他只能添加学生到其管理的宿舍楼
			builds = buildService.findByUserId(user.getId());
		}
		
//		System.out.println("builds: "+builds);
		request.setAttribute("builds",builds);
		
		
		if(action != null & action.equals("list")) {
			//查询学生在右侧展示
			String dormBuildId = request.getParameter("dormBuildId");
			String searchType = request.getParameter("searchType");
			String keyword = request.getParameter("keyword");
			
			//当前要查询的页面
			String pageIndex = request.getParameter("pageIndex");
			
//			System.out.println(" dormBuildId:"+dormBuildId+" searchType:"+searchType+" keyword:"+keyword);
			
			//默认查询第一页，需两个参数，--> 当前页码pageIndex\每页展示的条数
			PageModel pageModel = new PageModel();
			
			if(pageIndex != null && !pageIndex.equals("")) {
				pageModel.setPageIndex(Integer.parseInt(pageIndex));	
			}
			
			List<User> students = userService.findStudent(dormBuildId,searchType,keyword,user,pageModel);
			
			//获取查询处理的总数量
			Integer totalNum = userService.findTotalNum(dormBuildId,searchType,keyword,user);
//			System.out.println("totalNum: "+totalNum);
			
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("pageIndex", pageModel.getPageIndex());
			
			request.setAttribute("dormBuildId", dormBuildId);
			request.setAttribute("searchType", searchType);
			request.setAttribute("keyword", keyword);
			
			request.setAttribute("students", students);
			request.setAttribute("mainRight", "studentList.jsp");
			request.getRequestDispatcher("main.jsp").forward(request, response);
			
		}else if(action != null & action.equals("preAdd")){
			
			//根据用户角色查询宿舍楼进行学生添加，
			
			
			//跳转到学生的添加页面
			request.setAttribute("mainRight", "studentAddOrUpdate.jsp");
			request.getRequestDispatcher("main.jsp").forward(request, response);
			
		}else if(action != null & action.equals("save")) {
			//保存学生
			String stuCode = request.getParameter("stuCode");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String tel = request.getParameter("tel");
			String passWord = request.getParameter("passWord");
			String dormBuildId = request.getParameter("dormBuildId");
			String dormCode = request.getParameter("dormCode");
			
			/*
			 * System.out.println(" stuCode: "+stuCode+" name: "+name+
			 * " sex: "+sex+" tel: "+tel+"  passWord: "+passWord+
			 * " dormBuildId: "+dormBuildId+" dormCode: "+dormCode);
			 */
			
			
			//未存在，则保存
			User student = userService.findByStuCode(stuCode);
			
			if(id != null && !id.equals("")) {
				
				//更新之前，查询数据库是否已经存在当前学号的学生，如已存在，则跳转到添加页面
				if(student != null && !student.getId().equals(Integer.parseInt(id))) {
//					System.out.println("student:"+student);
					//当前学号的学生已存在，请重新修改，跳转到该学生的修改页面
					request.getRequestDispatcher("/student.action?action=preUpdate&id="+id).forward(request, response);
					
				}else {
					//更新   改
					User studentUpdate = userService.findById(Integer.parseInt(id));
					studentUpdate.setStuCode(stuCode);
					studentUpdate.setSex(sex);
					studentUpdate.setTel(tel);
					studentUpdate.setName(name);
					studentUpdate.setPassWord(passWord);
					studentUpdate.setDormBuildId(Integer.parseInt(dormBuildId));
					studentUpdate.setDormCode(dormCode);
					
					userService.updateStudent(studentUpdate);
					response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
				}
				
						
			}else {
				//保存   增
				if(student != null) {
					//在保存之前，查询数据库是否已经存在当前学号的学生，如已存在，则跳转到添加页面
					response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=preAdd");
					
				}else {
					
					User user2 = new User();
					user2.setStuCode(stuCode);
					user2.setSex(sex);
					user2.setTel(tel);
					user2.setName(name);
					user2.setPassWord(passWord);
					user2.setDormBuildId(Integer.parseInt(dormBuildId));
					user2.setDormCode(dormCode);
					user2.setRoleId(2);
					user2.setCreateUserId(user.getId());
					
					userService.saveStudent(user2);
					
					response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
				}
			}
			
			
		}else if(action != null & action.equals("preUpdate")) {
			//通过学生id查找学生并保存，以便在页面展示
			User userUpdate = userService.findById(Integer.parseInt(id));
			System.out.println("userUpdate: "+userUpdate);
			
			//判断当前登录的用户是否有修改该学生的权限,如没有，则跳转到学生管理的列表页；有则跳转到修改页面
			User user2 = userService.findByUserIdAndManager(userUpdate.getId(),user);
			System.out.println("查询管理权限user2："+user2);
			
			if(user2 != null) {
				//表示有权限
				request.setAttribute("userUpdate", userUpdate);
				//跳转到学生管理的修改页面
				request.setAttribute("mainRight", "studentAddOrUpdate.jsp");
				request.getRequestDispatcher("main.jsp").forward(request, response);
			}else {
				//无修改权限，跳转到学生管理的列表页
				response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
			}
			
	
		}else if(action != null & action.equals("deleteOrActive")) {
			//删除或激活
			User studentUpdate = userService.findById(Integer.parseInt(id));
			studentUpdate.setDisabled(Integer.parseInt(disabled));
			
			//判断当前登录的用户是否有删除或激活该学生的权限,如没有，则跳转到学生管理的列表页；
			User user2 = userService.findByUserIdAndManager(studentUpdate.getId(),user);
			System.out.println("查询删除激活权限user2："+user2);
			
			if(user2 != null) {
				//表示有权限
				userService.updateStudent(studentUpdate);
				response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
			}else {
				//无修改权限，跳转到学生管理的列表页
				response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
			}
	
		}
		
	}

}
