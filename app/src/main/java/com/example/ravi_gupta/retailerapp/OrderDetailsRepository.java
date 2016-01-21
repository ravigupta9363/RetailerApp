package com.example.ravi_gupta.retailerapp;

import com.strongloop.android.loopback.ModelRepository;

/**
 * Created by robins on 22/1/16.
 */
public class OrderDetailsRepository extends ModelRepository<OrderDetails> {
    public OrderDetailsRepository() {
        super("OrderDetails", null, OrderDetails.class);
    }
}
