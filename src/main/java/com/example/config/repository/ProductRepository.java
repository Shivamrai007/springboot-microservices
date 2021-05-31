package com.example.config.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.config.model.Product;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProductRepository.
 *
 * @author shivam.rai
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{

	/**
	 * Find products by product name contains.
	 *
	 * @param productName the product name
	 * @return the list
	 */
	public List<Product> findProductsByProductNameContains(String productName, Pageable pageable);
	
	/**
	 * Find products by category id.
	 *
	 * @param categoryId the category id
	 * @return the list
	 */
	public List<Product> findProductsByCategoryId(String categoryId, Pageable pageable);
}
