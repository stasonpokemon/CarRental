package pojo;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;

public class Order extends Entity{
        @Expose(serialize = true)
        private double price;
        @Expose(serialize = true)
        private String state;
        @Expose(serialize = true)
        private Timestamp date;
        @Expose(serialize = true)
        private int time;
        @Expose(serialize = true)
        private Car car;
        @Expose(serialize = true)
        private Client client;
        @Expose(serialize = true)
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
                return "Order{" +
                        "price=" + price +
                        ", state='" + state + '\'' +
                        ", date=" + date +
                        ", time=" + time +
                        ", car=" + car +
                        ", client=" + client +
                        ", refund=" + refund +
                        '}';
        }
}
