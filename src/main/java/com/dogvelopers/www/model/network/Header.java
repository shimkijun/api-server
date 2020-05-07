package com.dogvelopers.www.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import javafx.scene.control.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Header<T> {

    // api 통신 시간
    private LocalDateTime transactionTime;

    // api 응답 코드
    private boolean status;

    // api 부가 설명
    private String message;

    // api 데이터
    private T data;

    // pagination
    private Pagination pagination;

    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .status(true)
                .message("OK")
                .build();
    }

    public static <T> Header<T> OK(String message){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .status(true)
                .message(message)
                .build();
    }

    public static <T> Header<T> OK(String message,T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .status(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> Header<T> OK(String message,T data, Pagination pagination){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .status(true)
                .message(message)
                .data(data)
                .pagination(pagination)
                .build();
    }

    public static <T> Header<T> ERROR(String message){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .status(false)
                .message(message)
                .build();
    }

}
