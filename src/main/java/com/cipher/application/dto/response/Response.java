package com.cipher.application.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Response<T> {
    private T data;
    private String message;
    private Date processDate;

    public Response(T data){
        this.data=data;
        this.message="Processed successfully";
        this.processDate=new Date();
    }

}
