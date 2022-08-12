package com.gcash.mynt.controller;

import com.gcash.mynt.model.request.ParcelDetails;
import com.gcash.mynt.service.CalculateDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculateDeliveryController {

    @Autowired
    private CalculateDeliveryService calculateDeliveryService;

    @PostMapping(value = "/delivery-cost/{voucherCode}")
    public ResponseEntity<String> calculate(@RequestBody ParcelDetails parcelDetails, @PathVariable String voucherCode)  {
        return ResponseEntity.ok(calculateDeliveryService.calculateCostOfDelivery(parcelDetails, voucherCode));
    }
}

