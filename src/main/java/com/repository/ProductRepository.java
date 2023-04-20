package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	List<Product> findAll();
	List<Product> findByName(String name);
}
