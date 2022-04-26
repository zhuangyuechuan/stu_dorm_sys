package com.dorm.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dorm.bean.User;
import com.dorm.service.UserService;
import com.dorm.service.impl.UserServiceImpl;
import com.dorm.util.CookieUtil;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
      
    }

    
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("======登录=======");
		
		//解决POST请求乱码问题
		request.setCharacterEncoding("utf-8");
		
		//根据输入框标签的name属性值去获取对应用户输入的值：登录名、密码
		String stuCode = request.getParameter("stuCode");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		
		System.out.println("stuCode:"+stuCode+"password:"+password+"remember:"+remember);
		
		UserService userService = new UserServiceImpl();
		//去查询用户输入的登录名和密码是否正确
		User user = userService.findByStuCodeAndPass(stuCode,password);
		
		System.out.println("user:"+user);
		
		if(user == null) {
			//用户输入的学号或密码错误，跳转到登录页面，并给予提示信息
			request.setAttribute("error", "您输入的学号或密码错误!");
			
			//请求链未断开的跳转，可以在下一个servlet或jsp中，获取保存在request中的数据
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}else {
			//输入正确，登录成功，跳转到主页面
			//保存在session中的数据，默认是30分钟内有效
			request.getSession().setAttribute("session_user", user);
			
			if(remember != null && remember.equals("remember-me")) {
				//记住密码一周
				CookieUtil.addCookie("cookie_name_pass",7*24*60*60,request,response,URLEncoder.encode(stuCode, "utf-8"),URLEncoder.encode(password, "utf-8"));
				
			}
			
			System.out.println("======跳转到主页面=======");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}
		
		
		
	}

}
