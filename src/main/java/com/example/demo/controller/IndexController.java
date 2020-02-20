package com.example.demo.controller;

import com.example.demo.dto.PageDTO;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "3") Integer size,
                        @RequestParam(name="search",required = false) String search){

        PageDTO pagedto=questionService.list(search,page,size);
        model.addAttribute("pagedto",pagedto);
        model.addAttribute("search",search);
        return "index";
    }
}
