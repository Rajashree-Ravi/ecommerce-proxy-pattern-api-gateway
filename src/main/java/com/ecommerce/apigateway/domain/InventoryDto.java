package com.ecommerce.apigateway.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
	private Long id;
	private String sku;
	private Long productId;
	private String vendor;
	private Integer vendorInventory;
	private BigDecimal vendorPrice;
}
