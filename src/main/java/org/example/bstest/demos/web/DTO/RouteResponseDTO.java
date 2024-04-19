package org.example.bstest.demos.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bstest.demos.web.enums.StatusEnum;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteResponseDTO<T> {

    String msg;
    StatusEnum statusEnum;

    T result;


    public RouteResponseDTO(T result) {
        this.result = result;
    }

    public RouteResponseDTO(T result, StatusEnum statusEnum) {
        this.result = result;
        this.statusEnum = statusEnum;
    }

    public RouteResponseDTO(T result, StatusEnum statusEnum, String msg) {
        this.result = result;
        this.statusEnum = statusEnum;
        this.msg = msg;
    }
    public RouteResponseDTO(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public RouteResponseDTO(StatusEnum statusEnum, String msg) {
        this.msg = msg;
        this.statusEnum = statusEnum;
    }

}
