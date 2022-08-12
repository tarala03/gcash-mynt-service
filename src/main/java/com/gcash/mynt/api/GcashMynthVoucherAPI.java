package com.gcash.mynt.api;

import com.gcash.mynt.model.response.VoucherReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gcash-mynt", url = "https://mynt-exam.mocklab.io/voucher")
public interface GcashMynthVoucherAPI {
    @GetMapping(value = "/{voucherCode}")
    ResponseEntity<VoucherReponse> getVoucherDiscount(@PathVariable(value="voucherCode") String voucherCode, @RequestParam(value="key") String key);
}
