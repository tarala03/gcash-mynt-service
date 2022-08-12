package com.gcash.mynt.service;

import com.gcash.mynt.constant.ParcelAmount;
import com.gcash.mynt.model.request.ParcelDetails;
import com.gcash.mynt.model.DeliveryCost;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateDeliveryService {

    @Autowired
    VoucherDiscountService voucherDiscount;

    public String calculateCostOfDelivery(ParcelDetails parselDetails, String voucherCode) {
        String result = "Reject";
        float weight = parselDetails.getWeight();

        if (validWeight(weight)) {
            float volume = getVolume(parselDetails);
            float weightCost =  getWeightCost(weight);
            float volumeCost = getVolumeCost(volume);

            float deliveryCostAmount  = Float.sum(weightCost, volumeCost);
            DeliveryCost deliveryCost = new DeliveryCost();

            deliveryCost.setDeliveryCost(deliveryCostAmount);
            deliveryCost.setDiscountAmount(voucherDiscount.getVoucherDiscount(voucherCode));
            deliveryCost.setDiscountDeliveryCost(deliveryCost.getDeliveryCost() - deliveryCost.getDiscountAmount());

          return new Gson().toJson(deliveryCost);
        }

        return result;
    }

    /**
     * To get weigh cost;
     * @param weight
     */
    private float getWeightCost(float weight) {
        return weight > 10 ? (ParcelAmount.HEAVY * weight) : 0;
    }

    /**
     * To get volume cost base on parcel volume;
     * @param volume
     */
    private float getVolumeCost(float volume) {
        if(volume < 1500) {
            return ParcelAmount.SMALLL * volume;
        } else if (volume < 2500) {
            return ParcelAmount.MEDIUM * volume;
        } else {
            return ParcelAmount.LARGE * volume;
        }
    }

    /**
     * To compute volume value base on height, width, length.
     * @param parselDetails
     */
    private float getVolume(ParcelDetails parselDetails) {
        return parselDetails.getHeight() * parselDetails.getWidth() * parselDetails.getLength();
    }

    /**
     * To Check if weight is valid or invalid (not exceed 50kg)
     * @param weight
     */
    private boolean validWeight(float weight) {
        return weight <= 50 ? true : false;
    }

}