package com.fruitshop.fruitshop_backend.admin.controller;

import com.fruitshop.fruitshop_backend.admin.dto.PageDto;
import com.fruitshop.fruitshop_backend.admin.dto.SupplyDto;
import com.fruitshop.fruitshop_backend.admin.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/supplies")
public class SupplyController {

    private final SupplyService supplyService;

    @GetMapping("/create")
    public String createSupply(){
        return "supply/create";
    }

    @PostMapping
    @ResponseBody
    public void createSupply(@RequestBody SupplyDto supplyDto){
        System.out.println(supplyDto.getName());
        supplyService.createSupply(supplyDto);
    }

    @GetMapping("/{id}")
    public String getSupply(@PathVariable("id")int id, Model model){
        try{
            SupplyDto supplyDto = supplyService.getSupply(id);
            model.addAttribute("supply",supplyDto);
        }catch(IllegalStateException e){
            model.addAttribute("message", e.getMessage());
            return "error/404";
        }
        return "supply/detail";
    }

    @GetMapping
    public String getSupplies(@RequestParam(name = "page", defaultValue="1")int page,
                           @RequestParam(name = "size", defaultValue="10")int size,
                           Model model){
        PageDto<SupplyDto> pageDto = supplyService.getSupplies(page, size);
        model.addAttribute("pageDto", pageDto);
        return "supply/list";
    }

    @GetMapping("/{id}/modify")
    public String modifySupply(@PathVariable("id")int id, Model model){
        try{
            SupplyDto supplyDto = supplyService.getSupply(id);
            model.addAttribute("supply", supplyDto);
        }catch(IllegalStateException e){
            model.addAttribute("message", e.getMessage());
            return "error/404";
        }
        return "supply/modify";
    }

    @PutMapping("/{id}/modify")
    @ResponseBody
    public void modifySupply(@RequestBody SupplyDto supplyDto) {
        System.out.println(supplyDto.getName());
        supplyService.modifySupply(supplyDto);
    }

    @DeleteMapping("/{id}/remove")
    @ResponseBody
    public void removeItem(@PathVariable("id") int id) {
        supplyService.removeSupply(id);
    }

}
