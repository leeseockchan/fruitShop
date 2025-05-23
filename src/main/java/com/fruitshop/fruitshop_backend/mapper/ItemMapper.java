package com.fruitshop.fruitshop_backend.mapper;

import com.fruitshop.fruitshop_backend.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {

    void insertItem(ItemDto itemDto);

}
