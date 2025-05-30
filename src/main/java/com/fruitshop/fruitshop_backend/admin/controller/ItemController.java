package com.fruitshop.fruitshop_backend.admin.controller;

import com.fruitshop.fruitshop_backend.admin.dto.ItemDto;
import com.fruitshop.fruitshop_backend.admin.dto.PageDto;
import com.fruitshop.fruitshop_backend.admin.mapper.ItemMapper;
import com.fruitshop.fruitshop_backend.admin.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String getItems(@RequestParam(name = "page", defaultValue="1")int page,
                           @RequestParam(name = "size", defaultValue="10")int size,
                           Model model){
        PageDto pageDto = itemService.getItems(page, size);
        model.addAttribute("pageDto", pageDto);
        return "item/list";
    }

    @GetMapping("/create")
    public String createItem(){
        return "item/create";
    }

    @PostMapping
    @ResponseBody
    public void createItem(@RequestBody ItemDto itemDto){
        System.out.println(itemDto.getName());
        itemService.createItem(itemDto);
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model){
        try {
            ItemDto itemDto = itemService.getItem(id);
            model.addAttribute("item", itemDto);
        }catch (IllegalStateException e){
            model.addAttribute("message", e.getMessage());
            return "error/404";
        }
        return "item/detail";
    }

    @GetMapping("/{id}/modify")
    public String modifyItem(@PathVariable("id") int id, Model model){
        try{
            ItemDto itemDto = itemService.getItem(id);
            model.addAttribute("item", itemDto);
        }catch(IllegalStateException e){
            model.addAttribute("message", e.getMessage());
            return "error/404";
        }
        return "item/modify";
    }
    @PutMapping("/{id}/modify")
    @ResponseBody
    public void modifyItem(@RequestBody ItemDto itemDto){
        System.out.println(itemDto.getName());
        itemService.modifyItem(itemDto);
    }

    @DeleteMapping("/{id}/remove")
    @ResponseBody
    public void removeItem(@PathVariable("id") int id){
        itemService.removeItem(id);
    }



}
