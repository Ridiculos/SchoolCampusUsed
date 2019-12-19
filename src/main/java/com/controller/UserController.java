package com.controller;

import com.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login.do",method = {RequestMethod.POST})
    public @ResponseBody User login(@RequestBody User user){
        User userFind = userService.login(user.getUsername(), user.getPassword());
        if(userFind != null)
            return userFind;
        else
            //因为前端检验 userId 为 "" 为登录失败
            return new User();
    }

    @RequestMapping(value = "/register.do",method = {RequestMethod.POST})
    public void register(@RequestBody User user, HttpServletResponse response) throws IOException {
        int code = userService.register(user);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Integer> map = new HashMap<>();
        map.put("code",code);
        String json = objectMapper.writeValueAsString(map);
        response.getWriter().write(json);
    }

    @RequestMapping(value = "/getInfo.do",method = {RequestMethod.GET})
    public @ResponseBody User getInfo(@RequestParam String userId){
        return userService.getInfo(userId);
    }

    @RequestMapping(value = "/setInfo.do",method = {RequestMethod.POST})
    public void setInfo(@RequestBody User user,HttpServletResponse response) throws IOException {
        int code = userService.setInfo(user);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Integer> map = new HashMap<>();
        map.put("code",code);
        String json = objectMapper.writeValueAsString(map);
        response.getWriter().write(json);
    }
}
