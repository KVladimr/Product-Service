package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Product;

@Repository
public class ProductServiceImpl implements ProductService {
    
    @Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Product getProductById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, Product.class);
	}

	@Override
	public List<Product> getAllProducts() {
		return mongoTemplate.findAll(Product.class);
	}

	@Override
	public Product addProduct(Product product) {
		mongoTemplate.save(product);
		return product;
	}

	@Override
	public List<String> searchProducts(String name, String parameter, String value) {
		Query query = new Query();
		query.fields().include("name");
		if (name != null) {
			//query.addCriteria(Criteria.where("name").is(name));
			query.addCriteria(Criteria.where("name").regex(name));
		}
		if (parameter != null) {
			if (value != null) {
				query.addCriteria(Criteria.where("parameters." + parameter).is(value));
			}
			else {
				query.addCriteria(Criteria.where("parameters." + parameter).exists(true));
			}
		}
		List<Product> products = mongoTemplate.find(query, Product.class);
		
		List<String> listOfNames = new ArrayList<>();
		products.forEach((product) -> listOfNames.add(product.getName()));
		return listOfNames;
	}
}
