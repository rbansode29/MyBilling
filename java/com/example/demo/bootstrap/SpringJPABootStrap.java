package com.example.demo.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.services.crudservices.CustomerService;
import com.example.demo.services.crudservices.ProductService;
import com.example.demo.services.crudservices.UserService;
import com.example.demo.services.security.EncryptionService;

@Component
public class SpringJPABootStrap implements ApplicationListener<ContextRefreshedEvent> {
	private ProductService productService;
	private CustomerService customerService;
	private UserService userService;
	private EncryptionService encryptionService;
	

	
	public SpringJPABootStrap() {
		super();	
	}
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}	
	
	@Autowired
	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (null != event) {
			loadProduct();
			loadUser();
			loadCustomer();
			
		}
	}

	public void loadProduct() {
		Product p1 = new Product();
		p1.setDescription("product 1");
		p1.setPrice(new BigDecimal("12.99"));
		p1.setImageUrl("http://example.com/product1");
		productService.saveOrUpdateProduct(p1);
	}

	public void loadCustomer() {
		Customer c1 = new Customer();
		c1.setName("Ravindra Bansode");
		c1.setContact("9503910418");
		c1.setAddress("Pune");
		
		User u1 = userService.listAllUser().get(0);
		if(null == c1.getUser()) {
			c1.setUser(u1);
		}
		customerService.saveOrUpdateCustomer(c1);
	}

	public void loadUser() {
		User u1 = new User();
		u1.setUsername("admin@MyBilling.com");
		u1.setPassword("password");	
		u1.setEncryptedpassword(encryptionService.encryptString(u1.getPassword()));
		u1.setEnabled(true);		
		userService.saveOrUpdateUser(u1);
	}

	

}
