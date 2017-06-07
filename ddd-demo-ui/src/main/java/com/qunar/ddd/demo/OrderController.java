package com.qunar.ddd.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lil.yang on 2017/6/4.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    public OrderController(){
        System.out.println("dsfdfdsf");
    }

    @RequestMapping(value = "/findById/{orderId}" ,method = RequestMethod.GET)
    @ResponseBody
    public String findById(@PathVariable("orderId") String orderId){

        return "";
    }
}
