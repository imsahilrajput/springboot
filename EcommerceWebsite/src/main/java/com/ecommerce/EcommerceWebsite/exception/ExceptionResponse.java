package com.ecommerce.EcommerceWebsite.exception;

import lombok.Data;

@Data
public class ExceptionResponse {
    int errorCode;
    String message;
    String path;
}
