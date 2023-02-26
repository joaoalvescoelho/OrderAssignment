package com.example.orderassignment.mapper;

import com.example.orderassignment.dto.ProductDTO;
import com.example.orderassignment.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product>{
}
