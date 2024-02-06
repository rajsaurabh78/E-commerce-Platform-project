package com.ShopifyLite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/addproduct")
    public String index1() {
        return "add";
    }
    
    @GetMapping("/getproduct")
    public String index2() {
        return "get";
    }
    
    @GetMapping("/getallproduct")
    public String index3() {
        return "getall";
    }

}
