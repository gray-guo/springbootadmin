package com.sbm.admin.exception;

/**
 * @Description
 * @Author guoxiaoyong
 * @Date 2023/6/18
 */
public class ServiceException extends RuntimeException{
    private Integer code;


    public ServiceException(Integer code, String message){
        super(message);
        this.code = code;
    }


    public ServiceException(String message){
        super(message);
        this.code = 500;
    }

    public Integer getCode(){
        return this.code;
    }
}
