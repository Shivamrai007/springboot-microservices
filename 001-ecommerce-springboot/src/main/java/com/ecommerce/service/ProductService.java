package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.exceptions.CategoryNotFoundException;
import com.ecommerce.exceptions.ProductNotFoundException;
import com.example.config.model.Product;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProductService.
 *
 * @author shivam.rai
 */
public interface ProductService {

	/**
	 * Search product by product name.
	 *
	 * @param productName the product name
	 * @return the list
	 * @throws ProductNotFoundException the product not found exception
	 */
	public List<ProductDTO> searchProductByProductName(String productName,int pageNumber,int pageSize) throws ProductNotFoundException ;
	
	/**
	 * Search product by category name.
	 *
	 * @param categoryName the category name
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @return the list
	 * @throws CategoryNotFoundException the category not found exception
	 * @throws ProductNotFoundException the product not found exception
	 */
	public List<ProductDTO> searchProductByCategoryName(String categoryName,int pageNumber,int pageSize)
			throws CategoryNotFoundException, ProductNotFoundException ;
	
	/**
	 * Gets the product by id.
	 *
	 * @param productId the product id
	 * @return the product by id
	 * @throws ProductNotFoundException the product not found exception
	 */
	public Product getProductById(Long productId) throws ProductNotFoundException;
	
	/**
	 * Update product by product id.
	 *
	 * @param productId the product id
	 * @param product the product
	 * @return the product
	 * @throws ProductNotFoundException the product not found exception
	 */
	public Product updateProductByProductId(Long productId, Product product) throws ProductNotFoundException;
}
