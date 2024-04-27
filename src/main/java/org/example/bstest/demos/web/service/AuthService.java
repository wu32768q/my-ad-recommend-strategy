package org.example.bstest.demos.web.service;

import org.example.bstest.demos.web.DTO.RouteResponseDTO;

public interface AuthService {

    public RouteResponseDTO tryLogIn(String user, String pwd);
}
