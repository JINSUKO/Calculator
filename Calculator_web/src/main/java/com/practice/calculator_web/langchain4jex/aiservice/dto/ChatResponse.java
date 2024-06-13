package com.practice.calculator_web.langchain4jex.aiservice.dto;

import java.util.Objects;

public class ChatResponse{
    private final String response;

    public ChatResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatResponse that = (ChatResponse) o;
        return Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(response);
    }

    @Override
    public String toString() {
        return "ChatResponse{" + "response='" + response + '\'' + '}';
    }
}
