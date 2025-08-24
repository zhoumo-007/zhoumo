package com.yupi.yuaiagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class HttpAiInvoke {
    public static void main(String[] args) {
        // 替换为你的API密钥
        String apiKey = TestApiKey.API_KEY;
        
        // 构建请求URL
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
        
        // 构建请求体JSON
        JSONObject requestBody = new JSONObject();
        requestBody.set("model", "qwen-plus");
        
        JSONObject input = new JSONObject();
        JSONObject systemMessage = new JSONObject();
        systemMessage.set("role", "system");
        systemMessage.set("content", "You are a helpful assistant.");
        
        JSONObject userMessage = new JSONObject();
        userMessage.set("role", "user");
        userMessage.set("content", "你是谁？");
        
        input.set("messages", JSONUtil.createArray().put(systemMessage).put(userMessage));
        requestBody.set("input", input);
        
        JSONObject parameters = new JSONObject();
        parameters.set("result_format", "message");
        requestBody.set("parameters", parameters);
        
        // 发送POST请求
        HttpResponse response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .execute();
        
        // 输出响应结果
        System.out.println("Status: " + response.getStatus());
        System.out.println("Response: " + response.body());
    }
}