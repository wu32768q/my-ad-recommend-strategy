package org.example.bstest.demos.bishe.service;

import org.example.bstest.demos.bishe.DTO.RouteResponseDTO;

public interface AuthService {

    public RouteResponseDTO tryLogIn(String user, String pwd);
}
