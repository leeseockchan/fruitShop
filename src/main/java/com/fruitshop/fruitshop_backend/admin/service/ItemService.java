package com.fruitshop.fruitshop_backend.admin.service;

import com.fruitshop.fruitshop_backend.admin.dto.ItemDto;
import com.fruitshop.fruitshop_backend.admin.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemMapper itemMapper;

    public List<ItemDto> getItems(){
        return itemMapper.selectItems();
    }

    public ItemDto getItem(int id){
//       nullException 처리
        ItemDto itemDto = itemMapper.selectItemById(id).orElseThrow(
                () -> new IllegalStateException("데이터를 찾을 수 없습니다.")
        );
        return itemDto;
    }

    public void createItem(ItemDto itemDto) {
        itemMapper.insertItem(itemDto);
    }

    public void modifyItem(ItemDto itemDto){
        itemMapper.updateItem(itemDto);
    }

    public void removeItem(int id){
        itemMapper.deleteItem(id);
    }
}
