package com.example.demo.services.crudservices;

import java.util.List;

import com.example.demo.domain.Customer;

public interface CustomerService {
	Customer getCustomerById(Integer id);

	Customer saveOrUpdateCustomer(Customer customer);

	void deleteCustomer(Integer id);

	List<Customer> listAllCustomer();
}
