package com.practice.calculator_web.langchain4jex.aiservice;

import com.practice.calculator_web.langchain4jex.aiservice.service.Assistant;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class AssistantTools {
    /**
     * This tool is available to {@link Assistant}
     */
    @Tool
    String currnetTime() {
        return LocalTime.now().toString();
    }
}
