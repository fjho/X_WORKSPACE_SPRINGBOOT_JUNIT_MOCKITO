package com.ust.formacion.web_project_with_unit_test.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Item {

        @Id
        private int id;
        private String name;
        private int price;
        private int quantity;

        @Transient
        private int total;

        

        public Item(int id, String name, int price, int quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public Item() {
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getTotal() {
            return total;
        }   
        
        public void setTotal(int total) {
            this.total = total;
        }   

        public String toString() {
            return "{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    ", total=" + total +
                    '}';
        }
}
