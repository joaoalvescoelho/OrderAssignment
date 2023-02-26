package com.example.orderassignment.mapper;

import com.example.orderassignment.dto.AllOrdersDTO;
import com.example.orderassignment.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<AllOrdersDTO, Order>{
}
