package com.fruitshop.fruitshop_backend.controller;

import com.fruitshop.fruitshop_backend.dto.ItemDto;
import com.fruitshop.fruitshop_backend.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemMapper itemMapper;

    @GetMapping("/create")
    public String create(){
        return "item/create";
    }

    @PostMapping
    @ResponseBody
    public void createItem(@RequestBody ItemDto itemDto){
        System.out.println(itemDto.getName());
        itemMapper.insertItem(itemDto);
    }


}
