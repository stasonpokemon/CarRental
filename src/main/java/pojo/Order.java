package pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Order extends Entity{
        private double price;
        private String state;
        private Timestamp date;
        private int time;
        private Car car;
        private Client client;
        @SerializedName("returned")
        private Refund refund;

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public Timestamp getDate() {
                return date;
        }

        public void setDate(Timestamp date) {
                this.date = date;
        }

        public int getTime() {
                return time;
        }

        public void setTime(int time) {
                this.time = time;
        }

        public Car getCar() {
                return car;
        }

        public void setCar(Car car) {
                this.car = car;
        }

        public Client getClient() {
                return client;
        }

        public void setClient(Client client) {
                this.client = client;
        }

        public Refund getRefund() {
                return refund;
        }

        public void setRefund(Refund refund) {
                this.refund = refund;
        }

        @Override
        public String toString() {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
                return "Order{" +
                        "price=" + price +
                        ", state='" + state + '\'' +
                        ", date=" + format.format(date) +
                        ", time=" + time +
                        ", car=" + car +
                        ", client=" + client +
                        ", refund=" + refund +
                        '}';
        }
}
