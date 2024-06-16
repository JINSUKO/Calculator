package com.practice.calculator_web.langchain4jex.aiservice.service;

import com.practice.calculator_web.langchain4jex.aiservice.dto.ChatRequest;
import com.practice.calculator_web.langchain4jex.aiservice.model.BookModel;

public interface AIService {
    String getResponse(ChatRequest request);

    String getResponseExtended(ChatRequest request);

    BookModel getBookModelFromText(ChatRequest request);
}
