package com.zzxx.travel.web.servlet.userServlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BassUserServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)  {
      //接受请求，实现方法的分发：按功能调用不同方法
        //通过uri判断调用的哪个方法
        String uri = req.getRequestURI();
        String method = uri.substring(uri.lastIndexOf("/")+1);
        System.out.println(method);
        //method就是要调用的方法名
        //1.获得当前对象的字节码对象。this为当前对象。
        try {
            Method method1 = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //2.执行方法
            method1.invoke(this,req,res);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    //将制定对象序列化为json返回给客户端
    protected void writeValue(HttpServletResponse response,Object obj){

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        try {
            mapper.writeValue(response.getOutputStream(),obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
