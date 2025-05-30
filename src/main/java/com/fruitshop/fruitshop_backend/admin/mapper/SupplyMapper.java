package com.fruitshop.fruitshop_backend.admin.mapper;

import com.fruitshop.fruitshop_backend.admin.dto.SupplyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SupplyMapper {

    void insertSupply(SupplyDto supplyDto);
    Optional<SupplyDto> getSupplyById(int id);
    List<SupplyDto> getSupplies(@Param("size") int size, @Param("offset") int offset);
    int countTotal();
    void updateSupply(SupplyDto supplyDto);
    void deleteSupply(int id);
}
