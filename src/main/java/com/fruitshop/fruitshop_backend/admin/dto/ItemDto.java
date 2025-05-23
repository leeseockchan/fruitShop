package com.fruitshop.fruitshop_backend.admin.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("AdminItem")
public class ItemDto {
    private Integer id;
    private String name;
}
