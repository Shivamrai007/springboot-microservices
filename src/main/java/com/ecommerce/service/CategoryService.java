package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.exceptions.CategoryNotFoundException;
import com.ecommerce.exceptions.ProductNotFoundException;
import com.example.config.model.Category;
import com.example.config.model.Product;

// TODO: Auto-generated Javadoc
/**
 * The Interface CategoryService.
 *
 * @author shivam.rai
 */
public interface CategoryService {

	/**
	 * Find category by category name.
	 *
	 * @param categoryName the category name
	 * @return the category
	 * @throws CategoryNotFoundException the category not found exception
	 */
	public Category findCategoryByCategoryName(String categoryName) throws CategoryNotFoundException;
}
