package org.example.bstest.demos.web.service.impl;

import org.example.bstest.demos.web.DTO.RouteResponseDTO;
import org.example.bstest.demos.web.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public RouteResponseDTO tryLogIn(String user, String pwd) {
        return null;
    }
}
