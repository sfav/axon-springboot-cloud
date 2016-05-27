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

package cn.designthoughts.sample.axon.sfav.customer.domain;

import java.util.HashSet;
import java.util.Set;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import cn.designthoughts.sample.axon.sfav.customer.event.*;

/**
 * @author Thomas Yuan
 */

public class Customer extends AbstractAnnotatedAggregateRoot {
    private static final long serialVersionUID = 8723320580782813954L;

    @AggregateIdentifier
    private CustomerId customerId;
    
    private String passwordHash;
    
    private String nickName;
    private String legalName;
    private String avatarUrl;
    private String personalHomePageUrl;
    private String mobileNumber;
    
    private Role role;
    private Category category;
    private Status status;
    
	private EmailAddress emailAddress;
	
	private Set<Address> addresses = new HashSet<Address>();
	
	private String creationDate;
	
    @SuppressWarnings("UnusedDeclaration")
    protected Customer() {
    }

    public Customer(CustomerId customerId, String passwordHash, String nickName,
    				String legalName, String avatarUrl, String personalHomePageUrl,
    				String mobileNumber, Role role, Category category, Status status,
    				EmailAddress emailAddress, String creationDate) {
        apply(new CustomerCreatedEvent(customerId, passwordHash, nickName,
				legalName, avatarUrl, personalHomePageUrl,
				mobileNumber, role, category, status,
				emailAddress, creationDate));
    }
    
    public void activate(CustomerId customerId) {
    	apply(new CustomerActivatedEvent(customerId));
    }

    @Override
    public CustomerId getIdentifier() {
        return this.customerId;
    }

    @EventHandler
    public void handle(CustomerCreatedEvent event) {
        this.customerId = event.getCustomerId();
		this.passwordHash = event.getPasswordHash();
		this.nickName = event.getNickName();
		this.legalName = event.getLegalName();
		this.avatarUrl = event.getAvatarUrl();
		this.personalHomePageUrl = event.getPersonalHomePageUrl();
		this.mobileNumber = event.getMobileNumber();
		this.role = event.getRole();
		this.category = event.getCategory();
		this.status = event.getStatus();
		this.emailAddress = event.getEmailAddress();
		this.creationDate = event.getCreationDate();
    }
    
    @EventHandler
    public void handle(CustomerActivatedEvent event) {
		this.status = Status.ACTIVE;
    }
}
