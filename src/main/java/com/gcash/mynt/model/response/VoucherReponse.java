package com.gcash.mynt.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VoucherReponse {

    private String code;
    private float discount;
    private String expiry;

}
