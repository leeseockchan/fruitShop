package com.fruitshop.fruitshop_backend.admin.controller;

import com.fruitshop.fruitshop_backend.admin.dto.ItemDto;
import com.fruitshop.fruitshop_backend.admin.dto.PageDto;
import com.fruitshop.fruitshop_backend.admin.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemDto itemDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errorMap = new HashMap<>();

            bindingResult.getFieldErrors().forEach(error -> {

                String field = error.getField(); // 에러가 발생한 필드명 추출
                String message = error.getDefaultMessage(); // 해당 필드의 에러 메시지 추출
                // errorMap에 필드별 에러메시지 리스트 추가
                // computeIfAbsent: 해당 key가 없으면 새 ArrayList 생성
                // 있으면 기존 리스트에 메시지 추가
                errorMap.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
            });

            return ResponseEntity.badRequest().body(errorMap);
        }
        System.out.println(itemDto.getName());
        itemService.createItem(itemDto);
        return ResponseEntity.ok().build();
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
