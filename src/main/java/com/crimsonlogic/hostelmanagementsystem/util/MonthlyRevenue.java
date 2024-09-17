package com.crimsonlogic.hostelmanagementsystem.util;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonthlyRevenue {
    private int month;
    private double revenue;

    public MonthlyRevenue(int month, double revenue) {
        this.month = month;
        this.revenue = revenue;
    }

}
