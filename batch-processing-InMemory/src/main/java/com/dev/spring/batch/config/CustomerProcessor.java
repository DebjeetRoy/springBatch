package com.dev.spring.batch.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.spring.batch.entity.Customer;
import com.dev.spring.batch.repository.CustomerRepository;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer process(Customer customer) throws Exception {
		// Check if a row with the same ID exists
		if (customerRepository.existsById(customer.getId())) {
			// Delete the existing row
			customerRepository.deleteById(customer.getId());
			System.out.println("Deleted existing customer with ID: " + customer.getId());
		}
		// Return the customer to be written (inserted) by the writer
		return customer;
	}
}