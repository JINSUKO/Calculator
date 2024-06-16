package com.practice.calculator_web.langchain4jex.aiservice;

import com.practice.calculator_web.langchain4jex.aiservice.service.EmbeddingComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoadController {

    private final EmbeddingComponent embeddingComponent;

    public LoadController(EmbeddingComponent embeddingComponent) {
        this.embeddingComponent = embeddingComponent;
    }

    @GetMapping("/loader/single")
    public void loadSingle() {
        log.info("Loading single document start");
        embeddingComponent.loadSingleDocument();
        log.info("Loading single document end");
    }
}
