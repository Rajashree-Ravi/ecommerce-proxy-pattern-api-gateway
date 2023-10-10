package com.ecommerce.apigateway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.apigateway.domain.AggregateResponse;
import com.ecommerce.apigateway.domain.ProductDto;
import com.ecommerce.apigateway.domain.InventoryDto;

@RestController
@RequestMapping("/api/aggregate-data")
public class ZuulProxyController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping
	private ResponseEntity<List<AggregateResponse>> getAllInventory() {
		List<AggregateResponse> aggregateResponse = new ArrayList<>();

		// Call Product
		ResponseEntity<List<ProductDto>> products = restTemplate.exchange("http://localhost:8085/api/products",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDto>>() {
				});

		if (products != null && products.hasBody()) {
			for (ProductDto product : products.getBody()) {
				AggregateResponse response = new AggregateResponse();
				response.setProductDto(product);

				// Call Inventory
				ResponseEntity<List<InventoryDto>> inventory = restTemplate.exchange(
						"http://localhost:8085/api/inventory/product/" + product.getId(), HttpMethod.GET, null,
						new ParameterizedTypeReference<List<InventoryDto>>() {
						});

				if (inventory != null && inventory.hasBody() && inventory.getBody().size() > 0) {
					response.setInventoryDto(inventory.getBody());
				} else {
					response.setInventoryDto(new ArrayList<>());
				}
			}
		}

		return new ResponseEntity<>(aggregateResponse, HttpStatus.OK);
	}
}
