package com.example.demo.services.mapservices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.services.crudservices.ProductService;

@Service
@Profile("map")
public class ProductServiceImpl implements ProductService {

	private Map<Integer, Product> products = new HashMap<Integer, Product>();

	public ProductServiceImpl() {
		// loadProducts();
	}

	private void loadProducts() {
		Product p1 = new Product();
		p1.setId(1);
		p1.setDescription("Sample1");
		p1.setPrice(new BigDecimal(145872));
		p1.setImageUrl("/Sample1");
		products.put(1, p1);

		Product p2 = new Product();
		p2.setId(2);
		p2.setDescription("Sample2");
		p2.setPrice(new BigDecimal(145872));
		p2.setImageUrl("/Sample2");
		products.put(2, p2);

		Product p3 = new Product();
		p3.setId(3);
		p3.setDescription("Sample3");
		p3.setPrice(new BigDecimal(145872));
		p3.setImageUrl("/Sample3");
		products.put(3, p3);

	}

	@Override
	public List<Product> listAllProduct() {
		// TODO Auto-generated method stub
		return new ArrayList<>(products.values());
	}

	@Override
	public Product getProductById(Integer id) {
		return products.get(id);
	}

	@Override
	public Product saveOrUpdateProduct(Product product) {
		if (product != null) {
			if (product.getId() == null) {
				product.setId(getMaxId());
			}
			products.put(product.getId(), product);
			return product;
		} else {
			throw new RuntimeException("Product can't be null");
		}

	}

	private Integer getMaxId() {
		if (null != products && !products.isEmpty()) {
			return Collections.max(products.keySet()) + 1;
		} else {
			return 1;
		}
	}

	@Override
	public void deleteProduct(Integer id) {
		products.remove(id);
	}

}
