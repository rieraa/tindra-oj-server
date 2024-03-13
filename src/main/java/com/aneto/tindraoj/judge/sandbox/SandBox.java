package com.aneto.tindraoj.judge.sandbox;

import com.aneto.tindraoj.judge.sandbox.model.ExecuteCodeRequest;
import com.aneto.tindraoj.judge.sandbox.model.ExecuteCodeResponse;

public interface SandBox {

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
