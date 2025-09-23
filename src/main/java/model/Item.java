package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private String ItemCode;
    private String Description;
    private String PackSize;
    private double UnitPrice;
    private int QtyOnHand;
}
