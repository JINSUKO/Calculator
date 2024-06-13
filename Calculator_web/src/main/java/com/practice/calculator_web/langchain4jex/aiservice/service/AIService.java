package com.practice.calculator_web.langchain4jex.aiservice.service;

import com.practice.calculator_web.langchain4jex.aiservice.dto.ChatRequest;

public interface AIService {
    String getResponse(ChatRequest request);
}
