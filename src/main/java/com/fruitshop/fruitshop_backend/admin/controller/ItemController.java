package com.fruitshop.fruitshop_backend.admin.controller;

import com.fruitshop.fruitshop_backend.admin.dto.ItemDto;
import com.fruitshop.fruitshop_backend.admin.mapper.ItemMapper;
import com.fruitshop.fruitshop_backend.admin.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemMapper itemMapper;
    private final ItemService itemService;

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

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model){
        ItemDto itemDto = itemService.getItem(id);
        model.addAttribute("item",itemDto);
        return "item/detail";
    }

}
