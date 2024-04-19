package org.example.bstest.demos.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bstest.demos.web.enums.ResponseStatusEnum;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteResponseDTO<T> {

    String msg;
    ResponseStatusEnum responseStatusEnum;

    T result;


    public RouteResponseDTO(T result) {
        this.result = result;
    }

    public RouteResponseDTO(T result, ResponseStatusEnum responseStatusEnum) {
        this.result = result;
        this.responseStatusEnum = responseStatusEnum;
    }

    public RouteResponseDTO(T result, ResponseStatusEnum responseStatusEnum, String msg) {
        this.result = result;
        this.responseStatusEnum = responseStatusEnum;
        this.msg = msg;
    }
    public RouteResponseDTO(ResponseStatusEnum responseStatusEnum) {
        this.responseStatusEnum = responseStatusEnum;
    }

    public RouteResponseDTO(ResponseStatusEnum responseStatusEnum, String msg) {
        this.msg = msg;
        this.responseStatusEnum = responseStatusEnum;
    }

}
