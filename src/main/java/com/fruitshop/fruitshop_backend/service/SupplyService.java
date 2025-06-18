package com.fruitshop.fruitshop_backend.service;

import com.fruitshop.fruitshop_backend.dto.PageDto;
import com.fruitshop.fruitshop_backend.dto.SupplyDto;
import com.fruitshop.fruitshop_backend.mapper.SupplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyService {

    @Autowired
    private SupplyMapper supplyMapper;

    public void createSupply(SupplyDto supplyDto){
        supplyMapper.insertSupply(supplyDto);
    }

    public SupplyDto getSupply(int id){
        return supplyMapper.getSupplyById(id).orElseThrow(
                ()-> new IllegalStateException("데이터를 찾을 수 없습니다.")
        );
    }

    public PageDto<SupplyDto> getSupplies(int page, int size) {
        int offset = (page - 1) * size;
        List<SupplyDto> supplies = supplyMapper.getSupplies(size, offset);
        int totalElements = supplyMapper.countTotal();

        return new PageDto<>(page, size, totalElements, supplies);
    }

    public void modifySupply(SupplyDto  supplyDto){
        supplyMapper.updateSupply(supplyDto);
    }

    public void removeSupply(int id){
        supplyMapper.deleteSupply(id);
    }

}
