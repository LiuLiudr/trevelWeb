package com.zzxx.travel.web.servlet.userServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzxx.travel.domain.PageBean;
import com.zzxx.travel.domain.ResultInfo;
import com.zzxx.travel.domain.Route;
import com.zzxx.travel.domain.User;
import com.zzxx.travel.service.FavoriteService;
import com.zzxx.travel.service.RouteService;
import com.zzxx.travel.service.impl.FavoriteServiceImpl;
import com.zzxx.travel.service.impl.RouteServiceImpl;
import com.zzxx.travel.util.ResponseInfo;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RouteServlet/*")
public class RouteServlet extends BassUserServlet {
    RouteService routeService = new RouteServiceImpl();
    FavoriteService favoriteService = new FavoriteServiceImpl();
    public void queryPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.接收参数
        String _cid = request.getParameter("cid");
        System.out.println(_cid+":::"+request.getParameter("cid"));
        int cid = Integer.parseInt(_cid);
        String _currentpage = request.getParameter("currentpage");
        // 处理_currentPage
        int currentpage = 1;
        if (_currentpage != null && _currentpage.length() > 0) {
            currentpage = Integer.parseInt(_currentpage);
        }
        String _pagesize = request.getParameter("pagesize");
        // 处理_pageSize
        int pagesize = 8;
        if (_pagesize != null && _pagesize.length() > 0) {
            pagesize = Integer.parseInt(_pagesize);
        }
        // 2.调用service获得pageBean对象
        PageBean<Route> page = routeService.findRouteBypage(currentpage,pagesize,cid);
        // 3.将结果序列化为json返回
        writeValue(response, page);
    }
   public void datail(HttpServletRequest request, HttpServletResponse response){
        //接受rid
       String _rid = request.getParameter("rid");
       int rid = Integer.parseInt(_rid);
       //获得route对象
       Route oneRoute = routeService.findOneRoute(rid);
       //返回json
       writeValue(response,oneRoute);
   }
   public void ifFavorite(HttpServletRequest request, HttpServletResponse response){

        //判断有没有登录
       Object _user = request.getSession().getAttribute("user");
       ResultInfo info = new ResultInfo();
       if(_user==null){
           info.setFlag(false);
           writeValue(response,info);
           return;
       }
       // 2.如果登录了, 在判断该线路有没有被用户收藏
       String _rid = request.getParameter("rid");
       int rid = Integer.parseInt(_rid);
       int uid = ((User) _user).getUid();
       favoriteService.findByUidAndRid(rid,uid);
       if(favoriteService.findByUidAndRid(uid,rid)){
           info.setFlag(true);
       }else {
           info.setFlag(false);
       }
       writeValue(response,info);
   }
    public void favorite(HttpServletRequest request, HttpServletResponse response){
        Object user = request.getSession().getAttribute("user");
        int rid= Integer.parseInt(request.getParameter("rid"));
        if(user==null){
            return;//没登陆
        }
        int uid = ((User) user).getUid();
        favoriteService.addFavorite(rid,uid);
        writeValue(response,true);//此处返回是为了前端ajax函数能接收响应进入function
    }
}
