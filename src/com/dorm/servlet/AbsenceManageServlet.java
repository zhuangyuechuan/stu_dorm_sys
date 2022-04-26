package com.dorm.servlet;

import com.dorm.bean.DormBuild;
import com.dorm.bean.Record;
import com.dorm.bean.User;
import com.dorm.service.AbsenceManageService;
import com.dorm.service.DormBuildService;
import com.dorm.service.UserService;
import com.dorm.service.impl.AbsenceManageServiceImpl;
import com.dorm.service.impl.DormBuildServiceImpl;
import com.dorm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 缺勤管理
 */
@WebServlet("/absenceManage.action")
public class AbsenceManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AbsenceManageService amService = new AbsenceManageServiceImpl();

    public AbsenceManageServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //在Tomcat8.0中解决post请求乱码问题
        req.setCharacterEncoding("utf-8");
        //获取请求类型
        String action = req.getParameter("action");
        //宿舍管理员ID
        String id = req.getParameter("id");

        //获取当前登录的用户
        User user = (User) req.getSession().getAttribute("session_user");
        Integer roleId = user.getRoleId();
        //如果类型为list，则查询缺勤列表
        if(action != null & action.equals("list")) {
            if(roleId == 0 || roleId == 1) {
                List<Record> records = amService.findAll(roleId);
                req.setAttribute("records",records);
            }else {

            }

        }
    }

}
