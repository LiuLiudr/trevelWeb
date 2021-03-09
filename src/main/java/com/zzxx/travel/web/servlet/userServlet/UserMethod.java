package com.zzxx.travel.web.servlet.userServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzxx.travel.domain.ResultInfo;
import com.zzxx.travel.domain.User;
import com.zzxx.travel.service.UserService;
import com.zzxx.travel.service.impl.UserServiceImpl;
import com.zzxx.travel.util.LoginException;
import com.zzxx.travel.util.ResponseInfo;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/UserMethod/*")
public class UserMethod extends BassUserServlet {
    private UserService userService = new UserServiceImpl();

    public void UsnameServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        System.out.println("用户名"+username);

        boolean isExists = userService.checkUser(username);

        ResponseInfo info = new ResponseInfo();
        info.setFlag(!isExists);

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper =new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void registServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =new User();
        try {
            BeanUtils.populate(user,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用服务器注册。并且将结果封装成json对象
        Boolean isteue = userService.registUser(user);
        //结果判断
        ResponseInfo info = new ResponseInfo();
        info.setFlag(isteue);
        if(!isteue){
            info.setErrorMsg("注册失败！");
        }
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void LoginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String check_code=request.getParameter("check");
        String checkcode =  (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        ResponseInfo info = new ResponseInfo();
        if(checkcode!=null && checkcode.equalsIgnoreCase(check_code)){
            info.setFlag(true);
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try{
                User user = userService.login(username, password);
                request.getSession().setAttribute("user",user);//登陆成功将user设置到域中
            }catch (LoginException e){
                info.setErrorMsg(e.getMessage());
            }
        }else {
            info.setFlag(false);
        }
        ObjectMapper mapper= new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void FindoneServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object user = request.getSession().getAttribute("user");
        ResultInfo info = new ResultInfo();
        info.setData(user);

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接受请求参数
        String code = request.getParameter("code");
        //调用service激活
        UserService us=new UserServiceImpl();
        Boolean flag=us.active(code);
        response.setContentType("text/html;charset=utf-8");
        if(flag){
            response.sendRedirect(request.getContextPath()+"/login.html");
        }else {
            response.getWriter().write("邮箱激活失败！联系管理员");
        }
    }
   public void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
       response.sendRedirect(request.getContextPath() + "/login.html");
   }
}
