package com.zzxx.travel.web.servlet.userServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzxx.travel.domain.Category;
import com.zzxx.travel.domain.ResultInfo;
import com.zzxx.travel.service.CategoryService;
import com.zzxx.travel.service.UserService;
import com.zzxx.travel.service.impl.CategoryServiceImpl;
import com.zzxx.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoryServlet/*")
public class CategoryServlet extends BassUserServlet {
    UserService userService = new UserServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    public void Findall(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> categoryList = categoryService.FindallCategory();
        System.out.println(categoryList);

        ResultInfo info = new ResultInfo();
        info.setData(categoryList);
        writeValue(response, info);
    }
}
