package model;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Customer {
    private String CustID;
    private String CustTitle;
    private String CustName;
    private LocalDate DOB;
    private double salary;
    private String CustAddress;
    private String City;
    private String Province;
    private String PostalCode;
}
