package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    //1. Add an Order
    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    //2. Add a Delivery Partner
    public void addPartner(String partnerId ){
        orderRepository.addPartner(partnerId);
    }

    //3. Assign an order to a partner
    public void addOrderPartnerPair(String orderId,String partnerId){
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    //4. Get Order by orderId
    public Order getOrderById(String orderId){
        return orderRepository.getOrderById(orderId);
    }

    //5. Get Partner by partnerId
    public DeliveryPartner getPartnerById(String partnerId){
        return orderRepository.getPartnerById(partnerId);
    }

    //6.Get number of orders assigned to given partnerId
    public int getOrderCountByPartnerId(String partnerId){
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    //7. Get List of all orders assigned to given partnerId
    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    //8. Get List of all orders in the system
    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    //9. Get count of orders which are not assigned to any partner
    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }
    //12. Delete a partner and the corresponding orders should be unassigned
    public void deletePartnerById(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }

    //13. Delete an order and the corresponding partner should be unassigned
    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }
}
