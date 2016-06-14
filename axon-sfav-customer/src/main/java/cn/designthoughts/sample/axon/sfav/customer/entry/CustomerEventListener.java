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
package cn.designthoughts.sample.axon.sfav.customer.entry;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.designthoughts.sample.axon.sfav.customer.domain.CustomerId;
import cn.designthoughts.sample.axon.sfav.customer.domain.Status;
import cn.designthoughts.sample.axon.sfav.customer.event.CustomerActivatedEvent;
import cn.designthoughts.sample.axon.sfav.customer.event.CustomerAddressAddedEvent;
import cn.designthoughts.sample.axon.sfav.customer.event.CustomerCreatedEvent;
/**
 * @author Thomas Yuan
 */
@Component
public class CustomerEventListener {
	
	@Autowired
	private CustomerEntryRepository customerEntryRepository;

    @EventHandler
    public void handle(CustomerCreatedEvent event) {
    	customerEntryRepository.save(new CustomerEntry(event.getCustomerId().toString(),
    			event.getNickName(),
    			event.getLegalName(),
    			event.getAvatarUrl(),
    			event.getPersonalHomePageUrl(),
    			event.getMobileNumber(),
    			event.getRole().toString(),
    			event.getCategory().toString(),
    			event.getStatus().toString(),
    			event.getEmailAddress().toString(),
    			event.getCreationDate()
    			));
    	
    }
    
    @EventHandler
    public void handle(CustomerActivatedEvent event) {
    	CustomerEntry customerEntry = customerEntryRepository.findByIdentifier(event.getCustomerId().toString());
    	customerEntry.setStatus(Status.ACTIVE.toString());
    	customerEntryRepository.save(customerEntry);
    }
    
    @EventHandler
    public void handle(CustomerAddressAddedEvent event) {
    	CustomerEntry customerEntry = customerEntryRepository.findByIdentifier(event.getCustomerId().toString());
    	customerEntry.addAddress(event.getAddress().getBuilding(),
    			event.getAddress().getStreet(),
    			event.getAddress().getCity(),
    			event.getAddress().getCountry()
    			);
    	customerEntryRepository.save(customerEntry);
    }
}
