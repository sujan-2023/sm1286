package com.sm1286.controller;

import static com.sm1286.controller.CheckoutController.BASE_URL;

import com.sm1286.controller.request.CheckoutRequest;
import com.sm1286.controller.response.CheckoutResponse;
import com.sm1286.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping(BASE_URL)
@RequiredArgsConstructor
@Log4j2
public class CheckoutController {
    public static final String BASE_URL = "/api/checkout";
    private final CheckoutService checkoutService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CheckoutResponse> checkout(@Valid @RequestBody CheckoutRequest checkoutRequest) throws ValidationException {

        final CheckoutResponse response = checkoutService.checkout(checkoutRequest)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Checkout failed"));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }


}
