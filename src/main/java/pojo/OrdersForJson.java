package pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrdersForJson {
    @SerializedName("orders")
    private List<Order> orders = new ArrayList<>();

    private static OrdersForJson ordersForJson;

    public static OrdersForJson getOrdersForJson() {
        if (ordersForJson == null) {
            ordersForJson = new OrdersForJson();
        }
        return ordersForJson;
    }

    public OrdersForJson createOrdersForJson(List<Order> orderList) {
        orders.clear();
        addAllOrders(orderList);
        return ordersForJson;
    }

    public void addAllOrders(List<Order> orderList) {
        orders.addAll(orderList);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
