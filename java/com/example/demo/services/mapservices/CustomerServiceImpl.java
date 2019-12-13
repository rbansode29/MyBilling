package com.example.demo.services.mapservices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Customer;
import com.example.demo.services.crudservices.CustomerService;


@Service
@Profile("map")
public class CustomerServiceImpl implements CustomerService {
	private Map<Integer, Customer> customers = new HashMap<Integer, Customer>();

	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Customer> listAllCustomer() {
		// TODO Auto-generated method stub
		return new ArrayList<>(customers.values());
	}

	@Override
	public Customer getCustomerById(Integer id) {
		// TODO Auto-generated method stub
		return customers.get(id);
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		if (customer != null) {
			if (customer.getId() == null) {
				customer.setId(getMaxId());
			}
			customers.put(customer.getId(), customer);
			return customer;
		} else {
			throw new RuntimeException("Customer can't be null");
		}
	}

	@Override
	public void deleteCustomer(Integer id) {
		// TODO Auto-generated method stub
		customers.remove(id);
	}

	private Integer getMaxId() {
		if (null != customers && !customers.isEmpty()) {
			return Collections.max(customers.keySet()) + 1;
		} else {
			return 1;
		}
	}

}
