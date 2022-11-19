package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> deliveryPartnerMap;

    private HashMap<String, List<String>> partnerOrderMap;

    public OrderRepository(){
        this.orderMap = new HashMap<String, Order>();
        this.deliveryPartnerMap = new HashMap<String, DeliveryPartner>();
        this.partnerOrderMap = new HashMap<String, List<String>>();
    }
    //1. Add an Order
    public void addOrder(Order order){
        orderMap.put(order.getId(),order);
    }

    //2. Add a Delivery Partner
    public void addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        deliveryPartnerMap.put(partnerId,deliveryPartner);
    }

    //3. Assign an order to a partner

    public void addOrderPartnerPair(String orderId,String partnerId){

        if(orderMap.containsKey(orderId) && deliveryPartnerMap.containsKey(partnerId)){
            orderMap.put(orderId, orderMap.get(orderId));
            deliveryPartnerMap.put(partnerId, deliveryPartnerMap.get(partnerId));
            List<String> currentOrders = new ArrayList<String>();
            if(partnerOrderMap.containsKey(partnerId)) currentOrders = partnerOrderMap.get(partnerId);
            currentOrders.add(orderId);
            partnerOrderMap.put(partnerId, currentOrders);
        }
        DeliveryPartner d = deliveryPartnerMap.get(partnerId);
        d.setNumberOfOrders(d.getNumberOfOrders()+1);
    }

    //4. Get Order by orderId

    public Order getOrderById(String orderId){
        return orderMap.get(orderId);
    }

    //5. Get Partner by partnerId

    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnerMap.get(partnerId);
    }

    //6.Get number of orders assigned to given partnerId
    public int getOrderCountByPartnerId(String partnerId){
        List<String> oc = new ArrayList<String>();
        if(partnerOrderMap.containsKey(partnerId)) oc = partnerOrderMap.get(partnerId);
        return oc.size();
    }

    //7. Get List of all orders assigned to given partnerId
    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> oil = new ArrayList<String>();
        if(partnerOrderMap.containsKey(partnerId)) oil = partnerOrderMap.get(partnerId);
        return oil;
    }

    //8. Get List of all orders in the system
    public List<String> getAllOrders(){
        List<String> aol = new ArrayList<String>();
        orderMap.forEach((k, v)-> aol.add(k));
        return aol;
    }

    //9. Get count of orders which are not assigned to any partner
    public int getCountOfUnassignedOrders(){
        List<String> allOrd = new ArrayList<String>();
        List<String> assOrd;
        orderMap.forEach((k, v)-> allOrd.add(k));
        for (Map.Entry<String,List<String>> mapElement : partnerOrderMap.entrySet()) {
            assOrd = new ArrayList<String>();
            assOrd = mapElement.getValue();
            for(String s : assOrd){
                allOrd.remove(s);
            }
        }
        return allOrd.size();
    }

    //12. Delete a partner and the corresponding orders should be unassigned
    public void deletePartnerById(String partnerId){
        if(partnerOrderMap.containsKey(partnerId)){
            partnerOrderMap.remove(partnerId);
        }
        if(deliveryPartnerMap.containsKey(partnerId)){
            deliveryPartnerMap.remove(partnerId);
        }
    }

    //13. Delete an order and the corresponding partner should be unassigned
    public void deleteOrderById(String orderId) {
        List<String> assOrd;
        for (Map.Entry<String, List<String>> mapElement : partnerOrderMap.entrySet()) {
            assOrd = new ArrayList<String>();
            assOrd = mapElement.getValue();
            for (String s : assOrd) {
                if(s.equals(orderId)){
                    DeliveryPartner d = deliveryPartnerMap.get(mapElement.getKey());
                    d.setNumberOfOrders(d.getNumberOfOrders()-1);
                    assOrd.remove(s);
                    partnerOrderMap.put(mapElement.getKey(),assOrd);
                    break;
                }
            }
        }
        if(orderMap.containsKey(orderId)){
            orderMap.remove(orderId);
        }
    }
}
