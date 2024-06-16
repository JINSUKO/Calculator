package com.practice.calculator_web.langchain4jex.aiservice.model;

import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookModel {

    @Description("this is the userId from request.")
    private Integer userId;
    private String title;
    @Description("Should be in Korean letters and less than 20 words")
    private String description;
    @Description("Should be in Korean letters")
    private String genre;
    private LocalDate dataOfPublication;
    private String author;


}
