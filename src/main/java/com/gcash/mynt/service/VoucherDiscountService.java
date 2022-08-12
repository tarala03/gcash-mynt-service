package com.gcash.mynt.service;

import com.gcash.mynt.api.GcashMynthVoucherAPI;
import com.gcash.mynt.model.response.VoucherReponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VoucherDiscountService {

    private final Logger LOGGER = LoggerFactory.getLogger(VoucherDiscountService.class);

    @Autowired
    private GcashMynthVoucherAPI gcashMynthVoucherAPI;

    public float getVoucherDiscount(String voucherCode) {
        float result = 0.00f;

        try {
            ResponseEntity<VoucherReponse> voucherResponse = gcashMynthVoucherAPI.getVoucherDiscount(voucherCode,"apikey");
            result = voucherResponse.getBody().getDiscount();
        } catch (FeignException.BadRequest ex) {
            LOGGER.error("Encountered BadRequest Exception on getVoucherCode {}", ex.getMessage());
        }

        // TODO check validity of voucherCode base on expiry date
        return result;
    }

}
