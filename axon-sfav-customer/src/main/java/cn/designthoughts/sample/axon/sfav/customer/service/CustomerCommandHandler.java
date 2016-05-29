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

package cn.designthoughts.sample.axon.sfav.customer.service;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cn.designthoughts.sample.axon.sfav.customer.command.*;
import cn.designthoughts.sample.axon.sfav.customer.domain.*;

/**
 * @author Thomas Yuan
 */

@Component
public class CustomerCommandHandler {

    private EventSourcingRepository<Customer> customerRepository;

    @CommandHandler
    public void handle(CreateCustomerCommand command) {
    	Customer customer = new Customer(
                command.getCustomerId(),
                command.getPasswordHash(),
                command.getNickName(),
                command.getLegalName(),
                command.getAvatarUrl(),
                command.getPersonalHomePageUrl(),
                command.getMobileNumber(),
                command.getRole(),
                command.getCategory(),
                command.getStatus(),
                command.getEmailAddress(),
                command.getCreationDate());
    	customerRepository.add(customer);
    }

    @CommandHandler
    public void handle(ActivateCustomerCommand command) {
        Customer customer = customerRepository.load(command.getCustomerId());
        customer.activate(customer.getIdentifier());
    }
    @CommandHandler
    public void handle(AddCustomerAddressCommand command) {
        Customer customer = customerRepository.load(command.getCustomerId());
        customer.addAddress(customer.getIdentifier(), command.getAddress());
    }
    @Autowired
    public void setRepository(EventSourcingRepository<Customer> customerRepository) {
        this.customerRepository = customerRepository;
    }
}
