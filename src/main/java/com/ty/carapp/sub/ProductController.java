package com.ty.carapp.sub;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
private ProductRepository productRepository;

	@PostMapping("/pro/savepro")
	public Product savePro(@RequestBody Product p) {
		return productRepository.save( p);
	}
	
	@GetMapping("/product/findbyid")
	public Product findbyId(@RequestParam int proId) {
		Optional<Product> optional = productRepository.findById(proId);
		if(optional.isEmpty()) {
			return optional.get();
		}
		return null;
	}
	@DeleteMapping("/product/deleted")
	public String deleteProduct(@RequestParam int proId) {
		Optional<Product> optional =  productRepository.findById(proId);
		if(optional.isPresent()) {
			productRepository.delete(optional.get());
			return "deleted";
		}
		return "id not found";
	}
	
	@PatchMapping
	public Product updateProduct(int proId, double price) {
		Optional<Product> optional =  productRepository.findById(proId);
		if(optional.isPresent()) {
			Product product = optional.get();
			product.setPrice(67765);
			return productRepository.save(product);
		}else {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
