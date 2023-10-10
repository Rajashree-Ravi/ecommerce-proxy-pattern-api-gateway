package com.ecommerce.apigateway.domain;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregateResponse {
	private ProductDto productDto;
	private List<InventoryDto> inventoryDto;
}
