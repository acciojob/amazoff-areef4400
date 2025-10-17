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
        partnerMap.put(partnerId, new DeliveryPartner(partnerId));
    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            // your code here
            //add order to given partner's order list
            //increase order count of partner
            //assign partner to this order
            if(partnerToOrderMap.containsKey(partnerId)){
                HashSet<String> hs = partnerToOrderMap.get(partnerId);
                hs.add(String.valueOf(orderId));
                partnerToOrderMap.put(partnerId, hs);

            }else{
                HashSet<String> hs = new HashSet<>();
                hs.add(String.valueOf(orderId));
                partnerToOrderMap.put(partnerId, hs);
            }
            DeliveryPartner deliveryPartner = partnerMap.get(partnerId);
            deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);

            orderToPartnerMap.put(orderId, partnerId);
        }
    }

    public Order findOrderById(String orderId){
        // your code here
        Order order = null;
        if(orderMap.containsKey(orderId)){
            order = orderMap.get(String.valueOf(orderId));
        }
        return order;
    }

    public DeliveryPartner findPartnerById(String partnerId){
        // your code here
        DeliveryPartner deliveryPartner = null;
        if(partnerMap.containsKey(partnerId)){
            deliveryPartner = partnerMap.get(String.valueOf(partnerId));
        }
        return deliveryPartner;
    }

    public Integer findOrderCountByPartnerId(String partnerId){
        // your code here
        if(partnerMap.containsKey(partnerId)){
            return partnerMap.get(partnerId).getNumberOfOrders();
        }
        return 0;
    }

    public List<String> findOrdersByPartnerId(String partnerId){
        // your code here
        List<String> al = null;
        if(partnerToOrderMap.containsKey(partnerId)){
            al = new ArrayList<>(partnerToOrderMap.get(partnerId));
        }
        return al;
    }

    public List<String> findAllOrders(){
        // your code here
        // return list of all orders
        List<String> al = new ArrayList<>();
        for(Map.Entry<String, Order> e : orderMap.entrySet()){
            al.add(e.getKey());
        }
        return al;
    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
        partnerMap.remove(partnerId);
        HashSet<String> ordersList = partnerToOrderMap.get(partnerId);
        partnerToOrderMap.remove(partnerId);

        for(Map.Entry<String, String> e : orderToPartnerMap.entrySet()){
            if(e.getValue() == partnerId){
                orderToPartnerMap.remove(e.getKey());
            }
        }
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by ID
        orderMap.remove(orderId);
        String parterId = orderToPartnerMap.get(orderId);
        HashSet<String> hs = partnerToOrderMap.get(parterId);
        hs.remove(orderId);
        partnerToOrderMap.put(parterId, hs);
    }

    public Integer findCountOfUnassignedOrders(){
        // your code here
        Integer countUnassignedOrders = 0;
        for(Map.Entry<String, Order> e : orderMap.entrySet()){
            if(!orderToPartnerMap.containsKey(e.getKey())){
                countUnassignedOrders++;
            }
        }
        return countUnassignedOrders;
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
        // your code here
        Integer countOrders = 0;
        List<String> al = new ArrayList<>(partnerToOrderMap.get(partnerId));

        String arr[] = timeString.split(":");
        Integer timeStampInt = (Integer.parseInt(arr[0]) * 60) + Integer.parseInt(arr[1]);

        for(Map.Entry<String, Order> e : orderMap.entrySet()){
            if(e.getValue().getDeliveryTime() > timeStampInt){
                countOrders++;
            }
        }
        return countOrders;
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
        Integer lastTime = 0;

        List<String> ordesOfPartner = new ArrayList<>(partnerToOrderMap.get(partnerId));
        for(int i = 0; i < ordesOfPartner.size(); i++){
            Integer orderDelTime  = orderMap.get(ordesOfPartner.get(i)).getDeliveryTime();
            lastTime = Math.max(lastTime, orderDelTime);
        }
        String hours = String.valueOf(lastTime / 60);
        String min = String.valueOf(lastTime % 60);

        return hours+":"+min;
    }
}