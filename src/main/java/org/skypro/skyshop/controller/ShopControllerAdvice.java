package org.skypro.skyshop.controller;

import org.skypro.skyshop.NoSuchProductException;
import org.skypro.skyshop.service.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException e) {
        ShopError shopError = new ShopError("NO_SUCH_PRODUCT_WITH_THIS_ID", "Продукт с таким id не найден");
        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}
