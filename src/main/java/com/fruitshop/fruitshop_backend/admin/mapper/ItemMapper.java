package com.fruitshop.fruitshop_backend.admin.mapper;

import com.fruitshop.fruitshop_backend.admin.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void insertItem(ItemDto itemDto);
    Optional<ItemDto> selectItemById(int id);
    List<ItemDto> selectItems();
    void updateItem(ItemDto itemDto);
    void deleteItem(int id);

}
