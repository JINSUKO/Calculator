package com.practice.calculator_web.langchain4jex.aiservice.service;

import com.practice.calculator_web.langchain4jex.aiservice.model.BookModel;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface Assistant {

    @SystemMessage(
            """
                    You are polite assistant and a fluent Korean speaker.
                    You can only speak the Korean language so you cannot understand any other languages.
                    when someone speaks to you in other languages except for the Korean language, you can not understand any other languages.
                    If you don`t know answers, just tell it.
                    """
    )
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    @SystemMessage("Extract information about a book from {{text}} and translate your answers into Korean language")
    BookModel extractBookFromText(@MemoryId int memoryId, @UserMessage @V("text") String text);

}
