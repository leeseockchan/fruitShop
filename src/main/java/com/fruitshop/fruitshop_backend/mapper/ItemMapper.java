package com.fruitshop.fruitshop_backend.mapper;

import com.fruitshop.fruitshop_backend.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void insertItem(ItemDto itemDto);
    Optional<ItemDto> selectItemById(int id);
    List<ItemDto> selectItems(@Param("size")int size, @Param("offset")int offset);
    int countTotal();
    void updateItem(ItemDto itemDto);
    void deleteItem(int id);

}
