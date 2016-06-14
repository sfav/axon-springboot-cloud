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

package cn.designthoughts.sample.axon.sfav.customer.controller;

import java.security.Principal;
import java.util.Date;

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

import cn.designthoughts.sample.axon.sfav.customer.command.ActivateCustomerCommand;
import cn.designthoughts.sample.axon.sfav.customer.command.AddCustomerAddressCommand;
import cn.designthoughts.sample.axon.sfav.customer.command.CreateCustomerCommand;
import cn.designthoughts.sample.axon.sfav.customer.controller.request.CreateAddressForCustomerRequest;
import cn.designthoughts.sample.axon.sfav.customer.controller.request.CreateCustomerRequest;
import cn.designthoughts.sample.axon.sfav.customer.domain.Address;
import cn.designthoughts.sample.axon.sfav.customer.domain.Customer;
import cn.designthoughts.sample.axon.sfav.customer.domain.Status;
import cn.designthoughts.sample.axon.sfav.customer.domain.CustomerId;

/**
 * @author Thomas Yuan
 */

@RestController
public class CustomerController {

	private final IdentifierFactory identifierFactory = new DefaultIdentifierFactory();
	
	@Autowired
	private EventSourcingRepository<Customer> customerRepository;
	
	@Autowired
	private CommandGateway commandGateway;

	@RequestMapping(value = "/rest/customers", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
		String creationDate = new Date().toString();
		Status status = Status.INACTIVE;
		if(request.getStatus() != null){
			status = request.getStatus();
		}
		commandGateway.send(
				new CreateCustomerCommand(
						request.getCustomerId(),
						request.getPasswordHash(),
						request.getNickName(),
						request.getLegalName(),
						request.getAvatarUrl(),
						request.getPersonalHomePageUrl(),
						request.getMobileNumber(),
						request.getRole(),
						request.getCategory(),
						status,
						request.getEmailAddress(),
						creationDate
				));
		
		return;
	}

	@RequestMapping(value = "/rest/customers/{customerId}/address", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createAddressForCustomer(@PathVariable String customerId, @RequestBody @Valid CreateAddressForCustomerRequest request) {
		commandGateway.send(
			new AddCustomerAddressCommand(
				new CustomerId(customerId),
				new Address(request.getBuilding(),
					request.getStreet(), 
					request.getCity(), 
					request.getCountry())));
		return;
	}

	@RequestMapping(value = "/rest/customers/{customerId}/activation", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createTask(@PathVariable String customerId) {
		commandGateway.send(new ActivateCustomerCommand(new CustomerId(customerId)));
	}
}


