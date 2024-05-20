package org.example.bstest.demos.bishe.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bstest.demos.bishe.enums.ResponseStatusEnum;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RouteResponseDTO<T> {

    String message;
    ResponseStatusEnum responseStatusEnum;
    int code =200;
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
        this.message = msg;
    }
    public RouteResponseDTO(ResponseStatusEnum responseStatusEnum) {
        this.responseStatusEnum = responseStatusEnum;
    }

    public RouteResponseDTO(ResponseStatusEnum responseStatusEnum, String msg) {
        this.message = msg;
        this.responseStatusEnum = responseStatusEnum;
    }


    public static <T> RouteResponseDTO<T> ok(T result, String msg) {
        return new RouteResponseDTO<T>(result, ResponseStatusEnum.SUCESS);
    }

    public static <T> RouteResponseDTO<T> ok(T result) {
        return new RouteResponseDTO<T>(result, ResponseStatusEnum.SUCESS);
    }
}
