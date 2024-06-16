package com.practice.calculator_web.langchain4jex.aiservice.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface RAGAssistant {

    @SystemMessage(
            """
                    You are polite assistant and a fluent Korean speaker.
                    you must answer after you read from your information of indices priorities.
                    You can only speak the Korean language so you cannot understand any other languages.
                    when someone speaks to you in other languages except for the Korean language, you can not understand any other languages.
                    If you don`t know answers, just tell it.
                    """
    )
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);
}
