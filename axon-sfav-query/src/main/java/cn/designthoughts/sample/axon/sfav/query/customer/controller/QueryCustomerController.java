
/*
 * Copyright (c) 2016-2026. DesignThoughts Axon Sample
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.designthoughts.sample.axon.sfav.query.customer.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.domain.DefaultIdentifierFactory;
import org.axonframework.domain.IdentifierFactory;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;

import cn.designthoughts.sample.axon.sfav.query.customer.entry.AddressEntry;
import cn.designthoughts.sample.axon.sfav.query.customer.entry.CustomerEntry;
import cn.designthoughts.sample.axon.sfav.query.customer.entry.CustomerEntryRepository;

import com.fasterxml.jackson.databind.ObjectMapper;;
/**
 * @author Thomas Yuan
 */

@RestController
public class QueryCustomerController {

	private final IdentifierFactory identifierFactory = new DefaultIdentifierFactory();
	
	@Autowired
	private CustomerEntryRepository customerEntryRepository;
	
	@RequestMapping(value = "/rest/customers", method = RequestMethod.GET)
	public String listCustomer() {
		Iterable<CustomerEntry> listCustomers = customerEntryRepository.findAll();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String listCustomerString = "";
		try{
			listCustomerString = mapper.writeValueAsString(listCustomers);
		}catch (JsonProcessingException e){
			e.printStackTrace();
		}
		
		return listCustomerString;
	}

	@RequestMapping(value = "/rest/customers/{customerId}", method = RequestMethod.GET)
	public String getCustomer(@PathVariable String customerId) {
		CustomerEntry customer = customerEntryRepository.findByIdentifier(customerId);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String customerString = "";
		try{
			customerString = mapper.writeValueAsString(customer);
		}catch (JsonProcessingException e){
			e.printStackTrace();
		}
		
		return customerString;
	}

	@RequestMapping(value = "/rest/customers/{customerId}/addresses", method = RequestMethod.GET)
	public String getCustomerAddresses(@PathVariable String customerId) {
		Iterable<AddressEntry> listAddresses = customerEntryRepository.findByIdentifier(customerId).getAddresses();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String listAddressesString = "";
		try{
			listAddressesString = mapper.writeValueAsString(listAddresses);
		}catch (JsonProcessingException e){
			e.printStackTrace();
		}
		return listAddressesString;
	}
	
}


