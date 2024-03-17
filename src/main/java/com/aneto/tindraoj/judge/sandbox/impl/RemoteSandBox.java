package com.aneto.tindraoj.judge.sandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.aneto.tindraoj.common.BaseResponse;
import com.aneto.tindraoj.exception.BusinessException;
import com.aneto.tindraoj.judge.sandbox.SandBox;
import com.aneto.tindraoj.judge.sandbox.model.ExecuteCodeRequest;
import com.aneto.tindraoj.judge.sandbox.model.ExecuteCodeResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RemoteSandBox implements SandBox {


    private String way = "docker";


    private String host = "192.168.47.58";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = "http://" + host + ":8989/execute/" + way;
        System.out.println("🚀 ~ file:RemoteSandBox.java method:executeCode line:30 -----url:" + url);
        ExecuteCodeRequest request = new ExecuteCodeRequest(); // 构造你的请求对象
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        BaseResponse response = JSONUtil.toBean(HttpUtil.createPost(url)
                .body(json)
                .execute()
                .body(), BaseResponse.class);
        System.out.println("🚀 ~ file:RemoteSandBox.java method:executeCode line:27 -----responseStr:" + response);
        int code = response.getCode();
        if (code != 200) {
            throw new BusinessException(response.getCode(), response.getMessage());
        }

        Object object = response.getData();
        ExecuteCodeResponse executeCodeResponse = JSONUtil.toBean(JSONUtil.toJsonStr(object), ExecuteCodeResponse.class);
        return executeCodeResponse;

    }
}
