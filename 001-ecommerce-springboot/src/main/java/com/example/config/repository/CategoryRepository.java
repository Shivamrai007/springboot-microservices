package com.example.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.config.model.Category;
import com.example.config.model.Product;

// TODO: Auto-generated Javadoc
/**
 * The Interface CategoryRepository.
 *
 * @author shivam.rai
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	/**
	 * Find category by category name.
	 *
	 * @param categoryName the category name
	 * @return the category
	 */
	public Category findCategoryByCategoryName(String categoryName);
}
