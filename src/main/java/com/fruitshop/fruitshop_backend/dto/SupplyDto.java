package com.fruitshop.fruitshop_backend.dto;

import lombok.Data;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Data
@Alias("AdminSupply")
public class SupplyDto {
    @Setter
    private Integer id;
    @Setter
    private String name;
    private String contact1;
    private String contact2;
    private String businessNumber;

    public void setContact1(String contact1){
        this.contact1 = contact1 != null && contact1.trim().isEmpty() ? null : contact1;
    }

    public void setContact2(String contact2){
        this.contact2 = contact2 != null && contact2.trim().isEmpty() ? null : contact2;
    }

    public void setBusinessNumber(String businessNumber){
        this.businessNumber = businessNumber != null && businessNumber.trim().isEmpty() ? null : businessNumber;
        if (businessNumber.length() > 20) {
            throw new IllegalArgumentException("사업자번호가 너무 깁니다.");
        }
    }
}
