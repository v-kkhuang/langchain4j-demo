package org.example.entity;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;

public class MyStreamingHandler implements StreamingChatResponseHandler {
    /**
     * 处理部分响应数据的回调方法
     * 当接收到服务端的部分响应数据时，此方法会被调用
     *
     * @param s 接收到的部分响应字符串数据
     */
    @Override
    public void onPartialResponse(String s) {
        System.out.println(s);
    }


    /**
     * 完成响应处理的回调方法
     * 当聊天请求成功完成并收到响应时调用此方法
     *
     * @param chatResponse 聊天响应对象，包含服务器返回的聊天结果数据
     */
    @Override
    public void onCompleteResponse(ChatResponse chatResponse) {

    }

    /**
     * 错误处理的回调方法
     * 当聊天请求过程中发生异常或错误时调用此方法
     *
     * @param throwable 抛出的异常对象，包含错误信息和堆栈跟踪
     */
    @Override
    public void onError(Throwable throwable) {

    }

}
