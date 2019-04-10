package com.mtm.examples.controller.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtm.examples.client.AccountClient;
import com.mtm.examples.controller.CustomerServiceController;
import com.mtm.examples.domain.Account;
import com.mtm.examples.domain.Customer;
import com.mtm.examples.service.CustomerService;

@RestController
public class CustomerServiceControllerImpl implements CustomerServiceController {
	private static Logger LOGGER = Logger.getLogger(CustomerServiceControllerImpl.class.getName());

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountClient accountClient;

	@Override
	@RequestMapping("/pesel/{pesel}")
	public Customer findByPesel(@PathVariable("pesel") String pesel) {
		LOGGER.info(String.format("CustomerServiceControllerImpl.findByPesel(%s)", pesel));
		Customer customer = customerService.findByPesel(pesel);
		customer.add(
				linkTo(methodOn(CustomerServiceControllerImpl.class).findByPesel(customer.getPesel())).withSelfRel());
		return customer;
	}

	@Override
	@RequestMapping("/{customerId}")
	public Customer findByCustomerId(@PathVariable("customerId") Integer customerId) {
		LOGGER.info(String.format("CustomerServiceControllerImpl.findById(%s)", customerId));
		Customer customer = customerService.findByCustomerId(customerId);
		List<Account> accounts = accountClient.getAccounts(customerId);
		customer.setAccounts(accounts);
		customer.add(linkTo(methodOn(CustomerServiceControllerImpl.class).findByCustomerId(customer.getCustomerId()))
				.withSelfRel());
		return customer;
	}

	@Override
	@RequestMapping
	public List<Customer> findAll() {
		LOGGER.info("CustomerServiceControllerImpl.findAll()");
		List<Customer> customers = customerService.findAll();
		customers.stream().forEach(customer -> {
			customer.add(
					linkTo(methodOn(CustomerServiceControllerImpl.class).findByCustomerId(customer.getCustomerId()))
							.withSelfRel());
		});
		return customers;
	}

}
