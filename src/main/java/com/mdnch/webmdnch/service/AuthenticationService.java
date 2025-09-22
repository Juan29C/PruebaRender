package com.mdnch.webmdnch.service;

import com.mdnch.webmdnch.dto.request.LoginRequest;
import com.mdnch.webmdnch.dto.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
}