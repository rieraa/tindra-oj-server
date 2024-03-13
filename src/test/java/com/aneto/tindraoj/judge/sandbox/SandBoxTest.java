package com.aneto.tindraoj.judge.sandbox;

import com.aneto.tindraoj.judge.sandbox.impl.TestSandBox;
import com.aneto.tindraoj.judge.sandbox.model.ExecuteCodeRequest;
import com.aneto.tindraoj.judge.sandbox.model.ExecuteCodeResponse;
import com.aneto.tindraoj.model.enums.QuestionLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SandBoxTest {
    @Value("${sandbox.type}")
    private String type;
    @Test
    void executeCode() {
        SandBox codeSandbox = SandboxFactory.sandBoxInstance(type);
        codeSandbox = new SandboxProxy(codeSandbox);
        String code = "int main() { }";
        String language = QuestionLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .input(inputList)
                .build();
        // 调用代理对象的方法
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
    }



}