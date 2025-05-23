package com.fruitshop.fruitshop_backend.service;

import com.fruitshop.fruitshop_backend.dto.ItemDto;
import com.fruitshop.fruitshop_backend.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemMapper itemMapper;

    public ItemDto getItem(int id){
        return itemMapper.selectItemById(id);
    }
}
