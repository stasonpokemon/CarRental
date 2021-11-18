package pojo;

import com.google.gson.annotations.Expose;

public class Refund extends Entity{
    @Expose(serialize = true)
    private String state;
    @Expose(serialize = true)
    private String detail;
    @Expose(serialize = true)
    private double price;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Refund{" +
                "state='" + state + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                '}';
    }
}
