package com.practice.calculator_web.langchain4jex.aiservice;


import com.practice.calculator_web.langchain4jex.aiservice.dto.ChatRequest;
import com.practice.calculator_web.langchain4jex.aiservice.dto.ChatResponse;
import com.practice.calculator_web.langchain4jex.aiservice.model.BookModel;
import com.practice.calculator_web.langchain4jex.aiservice.service.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class AssistantController {


    private final AIService aiService;

    public AssistantController(AIService aiService) {
        this.aiService = aiService;
    }

//    @PostMapping
//    public ChatResponse getChatResponse(@RequestBody ChatRequest request) {
//        return new ChatResponse(aiService.getResponseSimple(request));
//    }

    @PostMapping
    public ChatResponse getChatResponse(@RequestBody ChatRequest request) {
        return new ChatResponse(aiService.getResponse(request));
    }

    @PostMapping("/extended")
    public ChatResponse getChatResponseExtended(@RequestBody ChatRequest request) {
        return new ChatResponse(aiService.getResponseExtended(request));
    }

    @PostMapping("/book")
    public BookModel getBookModelFromText(@RequestBody ChatRequest request) {
        return aiService.getBookModelFromText(request);
    }

}
