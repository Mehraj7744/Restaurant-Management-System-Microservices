package com.restaurant.auth.service;

import com.restaurant.auth.dto.request.RegisterRequest;
import com.restaurant.auth.dto.response.ApiResponse;

public interface AuthService {

    ApiResponse register(RegisterRequest request);

}