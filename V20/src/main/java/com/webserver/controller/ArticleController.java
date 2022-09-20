package com.webserver.controller;

import com.webserver.annotations.Controller;
import com.webserver.annotations.RequestMapping;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;
@Controller
public class ArticleController {
    @RequestMapping("/writeArticle")
    public void write(HttpServletRequest request, HttpServletResponse response){

    }
}
