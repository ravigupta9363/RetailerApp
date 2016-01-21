package com.example.ravi_gupta.retailerapp;

import com.google.common.collect.ImmutableMap;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.remoting.JsonUtil;
import com.strongloop.android.remoting.adapters.Adapter;
import com.strongloop.android.remoting.adapters.RestContract;
import com.strongloop.android.remoting.adapters.RestContractItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Ravi-Gupta on 1/21/2016.
 */
public class OrderRepository  extends ModelRepository<Order> {

    public OrderRepository() {
        super("Order", null, Order.class);
    }

    public RestContract createContract() {
        RestContract contract = super.createContract();
        contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/getOrders", "POST"), getClassName() + ".getOrders");
        return contract;
    }

    public void getOrder(String retailerId, final ListCallback<Order> callback) {
        invokeStaticMethod("getOrders", ImmutableMap.of("retailerId", retailerId), new Adapter.JsonObjectCallback() {
            @Override
            public void onSuccess(JSONObject response) {

                if(response != null) {
                    JSONArray orderArray = response.optJSONArray("order");
                    //Now converting jsonObject to list
                    List<Map<String, Object>> objList = (List) JsonUtil.fromJson(orderArray);
                    List<Order> orderList = new ArrayList<Order>();
                    OrderRepository orderRepo = getRestAdapter().createRepository(OrderRepository.class);
                    for (Map<String, Object> obj : objList) {
                        if(obj.get("orderDetails") != null){
                            List<Map> orderDetails = (List<Map>)obj.get("orderDetails");
                            List<OrderDetails> orderDetailses = new ArrayList<>();
                            for(Map<String, Object> detail : orderDetails){
                                OrderDetailsRepository orderDetailsRepo = getRestAdapter().createRepository(OrderDetailsRepository.class);
                                OrderDetails orderDetails1 = orderDetailsRepo.createObject(detail);
                                orderDetailses.add(orderDetails1);
                            }
                            obj.put("orderDetails", orderDetailses);

                        }

                        Order order = orderRepo.createObject(obj);
                        orderList.add(order);
                    }

                    callback.onSuccess(orderList);
                }else{
                    callback.onSuccess(null);
                }

            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }
}
