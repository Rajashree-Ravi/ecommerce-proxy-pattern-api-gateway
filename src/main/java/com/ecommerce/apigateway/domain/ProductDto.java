package com.ecommerce.apigateway.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private Long id;
	private String productCode;
	private String productName;
	private BigDecimal price;
	private String description;
	private String category;
}
