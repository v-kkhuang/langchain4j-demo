package org.example.conf;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LangChainLLMConf {

    @Value("${deepseek.api.key:#{systemProperties['DEEPSEEK_API_KEY'] ?: systemEnvironment['DEEPSEEK_API_KEY']}}")
    private String apiKey;

    @Bean(name = "deepseek")
    public ChatLanguageModel deepseek() {
        return OpenAiChatModel.builder()
                .modelName("deepseek-chat")
                .baseUrl("https://api.deepseek.com")
                .apiKey(apiKey)
                .build();
    }



}
