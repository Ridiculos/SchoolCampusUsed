package com.controller;

import com.domain.Feedback;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    /**
     * 提交反馈
     * @param feedback
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/submitFeedback.do",method = {RequestMethod.POST})
    public void submitFeedback(@RequestBody Feedback feedback, HttpServletResponse response) throws IOException {
        int count = feedbackService.submitFeedback(feedback);
        HashMap<String, Integer> map = new HashMap<>();
        if(count == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
