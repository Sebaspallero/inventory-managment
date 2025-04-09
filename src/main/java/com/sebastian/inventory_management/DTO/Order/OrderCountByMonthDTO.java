package com.sebastian.inventory_management.DTO.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCountByMonthDTO {
    
    private Long count;
    private java.sql.Date month;
}
