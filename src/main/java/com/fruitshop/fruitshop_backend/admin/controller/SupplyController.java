package com.fruitshop.fruitshop_backend.admin.controller;

import com.fruitshop.fruitshop_backend.admin.dto.SupplyDto;
import com.fruitshop.fruitshop_backend.admin.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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

}
