package com.zzxx.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzxx.travel.util.ResponseInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkCodeContent")
public class checkCodeContent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得客户端输入验证码
        String check_code=request.getParameter("check_code");
        //获得session
        String checkcode =  (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        //判断
        ResponseInfo info = new ResponseInfo();
        if(checkcode!=null && checkcode.equalsIgnoreCase(check_code)){
            info.setFlag(true);
        }else {
            info.setFlag(false);
        }
        //返回json给客户端
        ObjectMapper mapper= new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
