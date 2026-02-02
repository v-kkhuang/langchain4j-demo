package org.example.resetful;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiChatRequestParameters;
import org.example.entity.MyStreamingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.apache.coyote.http11.Constants.a;

@RestController
@RequestMapping("/langChain")
public class LangChainChatController {

    @Autowired
    private ChatLanguageModel deepseek;

    @Autowired
    private StreamingChatLanguageModel deepseekStream;

    /***
     * 这个是最简单的demo，直接使用chatModel聊天，
     * 适用于简单的一问一答模式，并且没有记忆
     * @param userMessage 用户输入的问题
     * @return String
     */
    @RequestMapping(path = "/chat", method = RequestMethod.GET)
    public String chat_low(@RequestParam(name = "userMessage", defaultValue = "hi", required = false) String userMessage) {
        return deepseek.chat(userMessage);
    }

    /***
     * 这个是使用chatMessage 与大模型对话，chatMessage有很多中类型：
     * userMessage，aiMessage，systemMessage，customMessage,ToolExecutionResultMessage(llm分析调用tools)
     * 适用于简单的一问一答模式，并且没有记忆
     * @param userMessage 用户输入的问题
     * @return String
     */
    @RequestMapping(path = "/chatMessage", method = RequestMethod.GET)
    public String chatMessage(@RequestParam(name = "userMessage", defaultValue = "hi", required = false) String userMessage) {
        UserMessage userMessage1 = UserMessage.from(userMessage);
        SystemMessage systemMessage = SystemMessage.from("你是一位严厉的老师");
        ChatResponse chat = deepseek.chat(List.of(userMessage1, systemMessage));
        System.out.println(chat.aiMessage().type());
        return chat.aiMessage().text();
    }


    /***
     * 这个是使用chatRequest 与大模型对话，和chatMessage对比之下，chatMessage有更多功能，比如：parameters（查看源码可证明）
     * parameters里面包含更多高级的llm参数，后面会研究
     * @param userMessage 用户输入的问题
     * @return String
     */
    @RequestMapping(path = "/chatRequest", method = RequestMethod.GET)
    public String chatRequest(@RequestParam(name = "userMessage", defaultValue = "hi", required = false) String userMessage) {
        UserMessage userMessage1 = UserMessage.from(userMessage);
        SystemMessage systemMessage = SystemMessage.from("你是一位严厉的老师");
        ChatRequest chatRequest = ChatRequest.builder()
                .messages(List.of(userMessage1, systemMessage))
                .parameters(OpenAiChatRequestParameters.builder()
                        .maxOutputTokens(10)
                        .build())
                .build();
        ChatResponse chatResponse = deepseek.chat(chatRequest);
        System.out.println(chatResponse.aiMessage().type());
        return chatResponse.aiMessage().text();
    }



    //todo 流式响应spring boot 集成
    @RequestMapping(path = "/chatStream", method = RequestMethod.GET)
    public void chatStream(@RequestParam(name = "userMessage", defaultValue = "hi", required = false) String userMessage) {
        UserMessage userMessage1 = UserMessage.from(userMessage);
        SystemMessage systemMessage = SystemMessage.from("你是一位严厉的老师");
        ChatRequest chatRequest = ChatRequest.builder()
                .messages(List.of(userMessage1, systemMessage))
                .parameters(OpenAiChatRequestParameters.builder()
                        .maxOutputTokens(10)
                        .build())
                .build();
        deepseekStream.chat(chatRequest, new MyStreamingHandler());
    }




}
