package com.ecommerce.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.CategoryNotFoundException;
import com.ecommerce.service.CategoryService;
import com.example.config.model.Category;
import com.example.config.repository.CategoryRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoryServiceImpl.
 *
 * @author shivam.rai
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	/** The category repository. */
	@Autowired
	CategoryRepository categoryRepository;
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	
	/**
	 * Find category by category name.
	 *
	 * @param categoryName the category name
	 * @return the category
	 * @throws CategoryNotFoundException the category not found exception
	 */
	public Category findCategoryByCategoryName(String categoryName) throws CategoryNotFoundException {
		Category category = categoryRepository.findCategoryByCategoryName(categoryName);

		if (category != null) {
			logger.info("Category found with category name : {}",categoryName);
			return category;
		} else {
			logger.info("Category not found with category name : {}",categoryName);
			throw new CategoryNotFoundException("Product not Found with category Name " + categoryName);
		}

	}


}
