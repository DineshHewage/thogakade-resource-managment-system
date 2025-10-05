package model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Order {
    private String orderID;
    private Date orderDate;
    private String customerID;
}
