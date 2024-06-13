package com.practice.calculator_web.langchain4jex.aiservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ChatRequest {

    private final String question;
    private final Integer userId;

    @JsonCreator
    public ChatRequest(@JsonProperty("question") String question, @JsonProperty("userId") Integer userId) {
        this.question = question;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRequest that = (ChatRequest) o;
        return Objects.equals(question, that.question) && Objects.equals(userId, that.userId);
    }

    @Override
    public String toString() {
        return "ChatRequest{" +
                "question='" + question + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, userId);
    }


}
