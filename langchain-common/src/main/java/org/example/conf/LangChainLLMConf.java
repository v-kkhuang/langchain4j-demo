package org.example.conf;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LangChainLLMConf {



    @Bean(name = "deepseek")
    public ChatLanguageModel deepseek() {
        return OpenAiChatModel.builder().modelName("gpt-3.5-turbo").baseUrl("").apiKey("").build();
    }



}
