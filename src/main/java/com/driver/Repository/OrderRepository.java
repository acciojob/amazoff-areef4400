package com.driver.Repository;

import java.util.*;

import com.driver.Model.DeliveryPartner;
import com.driver.Model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;

    public OrderRepository(){
        this.orderMap = new HashMap<String, Order>();
        this.partnerMap = new HashMap<String, DeliveryPartner>();
        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
        this.orderToPartnerMap = new HashMap<String, String>();
    }

    public void saveOrder(Order order){
        orderMap.put(order.getId(), order);
    }

    public void savePartner(String partnerId){
        // your code here
        // create a new partner with given partnerId and save it
//        partnerMap.put(partnerId, )
    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            // your code here
            //add order to given partner's order list
            //increase order count of partner
            //assign partner to this order
        }
    }

    public Order findOrderById(String orderId){
        // your code here
        return new Order("455", "bdjdhf");
    }

    public DeliveryPartner findPartnerById(String partnerId){
        // your code here
        return new DeliveryPartner("tt");
    }

    public Integer findOrderCountByPartnerId(String partnerId){
        // your code here
        return 0;
    }

    public List<String> findOrdersByPartnerId(String partnerId){
        // your code here
        List<String> al = new ArrayList<>();
        return al;
    }

    public List<String> findAllOrders(){
        // your code here
        // return list of all orders

                List<String> al = new ArrayList<>();
        return al;
    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by ID

    }

    public Integer findCountOfUnassignedOrders(){
        // your code here
        return 0;
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
        // your code here
        return 0;
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
        return "";
    }
}