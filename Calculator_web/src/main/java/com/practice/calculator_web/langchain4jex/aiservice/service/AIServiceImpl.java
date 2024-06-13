package com.practice.calculator_web.langchain4jex.aiservice.service;

import com.practice.calculator_web.langchain4jex.aiservice.dto.ChatRequest;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AIServiceImpl implements AIService{

    private final Assistant assistant;

    public AIServiceImpl(Assistant assistant) {
        this.assistant = assistant;
    }

//    @Override
    public String getResponseSimple(ChatRequest request) {

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(SystemMessage.systemMessage("Response in the words mixed between Korean and English"));
        messages.add(UserMessage.userMessage(request.getQuestion()));

        OpenAiChatModel model = OpenAiChatModel
                .builder()
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_3_5_TURBO)
                .build();

//public interface ChatLanguageModel - Interface ->
//    default String generate(String userMessage) {
//        return ((AiMessage)this.generate(UserMessage.from(userMessage)).content()).text();
//    }
        // 매개변수로 String을 넣으면 위에 메소드를 사용하게됨.

//      return  model.generate(request.getQuestion());
        return  model.generate(messages).content().text();

    }

    @Override
    public String getResponse(ChatRequest request) {

        return assistant.chat(request.getUserId(), request.getQuestion());

    }
}
