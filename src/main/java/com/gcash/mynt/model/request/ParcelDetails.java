package com.gcash.mynt.model.request;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParcelDetails {

    private float weight;
    private float height;
    private float width;
    private float length;

}
