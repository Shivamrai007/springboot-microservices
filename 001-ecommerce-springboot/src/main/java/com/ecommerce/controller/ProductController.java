package com.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.exceptions.CategoryNotFoundException;
import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.exceptions.UserInputException;
import com.ecommerce.service.ProductService;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductController.
 *
 * @author shivam.rai
 */
@RestController
@RequestMapping("products")
@CrossOrigin //enabled for swagger for running on api gateway port.
public class ProductController {

	/** The product service. */
	@Autowired
	ProductService productService;
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(ProductController.class);

	/**
	 * Search product.
	 *
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @param productName the product name
	 * @param categoryName the category name
	 * @return the list
	 * @throws ProductNotFoundException the product not found exception
	 * @throws CategoryNotFoundException the category not found exception
	 * @throws UserInputException the user input exception
	 */
	
	@GetMapping
	public List<ProductDTO> searchProduct(@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "2") int pageSize, @RequestParam(required = false) String productName,@RequestParam(required = false) String categoryName) throws ProductNotFoundException, CategoryNotFoundException, UserInputException{
		
		if(categoryName == null) {
			logger.info("Searching product is intiated with product name : {}",productName);
			return productService.searchProductByProductName(productName, pageNumber, pageSize);
		}else {
			logger.info("Searching product is intiated with category name : {}",categoryName);
			return productService.searchProductByCategoryName(categoryName, pageNumber, pageSize);
		}
		
	}
	
	
}
