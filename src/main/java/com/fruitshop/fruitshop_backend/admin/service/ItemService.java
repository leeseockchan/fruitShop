package com.fruitshop.fruitshop_backend.admin.service;

import com.fruitshop.fruitshop_backend.admin.dto.ItemDto;
import com.fruitshop.fruitshop_backend.admin.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemMapper itemMapper;

    public ItemDto getItem(int id){
//       nullException 처리
        ItemDto itemDto = itemMapper.selectItemById(id).orElseThrow(
                () -> new IllegalStateException("파일을 찾을 수 없습니다.")
        );
        return itemDto;
    }

    public void modify(ItemDto itemDto){
        itemMapper.updateItem(itemDto);
    }

    public void remove(int id){
        itemMapper.deleteItem(id);
    }
}
