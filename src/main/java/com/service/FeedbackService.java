package com.service;

import com.domain.Feedback;

public interface FeedbackService {
    /**
     * 提交反馈
     * @param feedback
     * @return
     */
    int submitFeedback(Feedback feedback);
}
