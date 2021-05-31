package com.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.exceptions.CategoryNotFoundException;
import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.example.config.model.Category;
import com.example.config.model.Product;
import com.example.config.repository.ProductRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductServiceImpl.
 *
 * @author shivam.rai
 */
@Service
public class ProductServiceImpl implements ProductService {

	/** The product repository. */
	@Autowired
	ProductRepository productRepository;

	/** The category service. */
	@Autowired
	CategoryService categoryService;
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	/**
	 * Search product by product name.
	 *
	 * @param productName the product name
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @return the list
	 * @throws ProductNotFoundException the product not found exception
	 */
	public List<ProductDTO> searchProductByProductName(String productName,int pageNumber,int pageSize) throws ProductNotFoundException {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		
		List<Product> productList = productRepository.findProductsByProductNameContains(productName,pageable);

		if (!productList.isEmpty()) {
			logger.info("Product found with product name : {}",productName);
			List<ProductDTO> productDTOList = new ArrayList<>();

			for (Product product : productList) {
				ProductDTO productDTO = new ProductDTO();
				BeanUtils.copyProperties(product, productDTO);
				productDTOList.add(productDTO);
			}

			return productDTOList;
		} else {
			logger.error("Product not found with product name : {}",productName);
			throw new ProductNotFoundException("Product not found with product Name " + productName);
		}

	}

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
			throws CategoryNotFoundException, ProductNotFoundException {
		System.out.println(categoryName);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Category category = categoryService.findCategoryByCategoryName(categoryName);
	
		List<Product> productList = productRepository
				.findProductsByCategoryId(String.valueOf(category.getCategoryId()),  pageable);

		if (!productList.isEmpty()) {
			logger.info("Product found with category name : {}",categoryName);
			List<ProductDTO> productDTOList = new ArrayList<>();

			for (Product product : productList) {
				ProductDTO productDTO = new ProductDTO();
				BeanUtils.copyProperties(product, productDTO);
				productDTOList.add(productDTO);
			}

			return productDTOList;
		} else {
			logger.error("Product not found with category name : {}",categoryName);
			throw new ProductNotFoundException("Product not Found with Category Name " + categoryName);
		}

	}

	/**
	 * Gets the product by id.
	 *
	 * @param productId the product id
	 * @return the product by id
	 * @throws ProductNotFoundException the product not found exception
	 */
	public Product getProductById(Long productId) throws ProductNotFoundException {
		Optional<Product> product = productRepository.findById(productId);

		if (product.isPresent()) {
			logger.info("Product found with product Id : {}",productId);
			return product.get();
		} else {
			logger.error("Product not Found with product Id : {}",productId);
			throw new ProductNotFoundException("Product not Found with product Id " + productId);
		}
	}

	/**
	 * Update product by product id.
	 *
	 * @param productId the product id
	 * @param product the product
	 * @return the product
	 * @throws ProductNotFoundException the product not found exception
	 */
	public Product updateProductByProductId(Long productId, Product product) throws ProductNotFoundException {
		Product product2 = getProductById(productId);
		product2.setCategoryId(product.getCategoryId());
		product2.setDescription(product.getDescription());
		product2.setPrice(product.getPrice());
		product2.setQuantity(product.getQuantity());
		product2.setProductName(product.getProductName());
		productRepository.save(product2);
		return product2;
	}

	
}
