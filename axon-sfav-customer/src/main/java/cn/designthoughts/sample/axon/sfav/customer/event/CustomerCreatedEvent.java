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

package cn.designthoughts.sample.axon.sfav.customer.event;

import java.util.HashSet;
import java.util.Set;

import cn.designthoughts.sample.axon.sfav.customer.domain.Address;
import cn.designthoughts.sample.axon.sfav.customer.domain.Category;
import cn.designthoughts.sample.axon.sfav.customer.domain.CustomerId;
import cn.designthoughts.sample.axon.sfav.customer.domain.EmailAddress;
import cn.designthoughts.sample.axon.sfav.customer.domain.Role;
import cn.designthoughts.sample.axon.sfav.customer.domain.Status;
/**
 * @author Thomas Yuan
 */
public class CustomerCreatedEvent {
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
	
	private String creationDate;
	
	public CustomerCreatedEvent(CustomerId customerId, String passwordHash, String nickName,
			String legalName, String avatarUrl, String personalHomePageUrl,
			String mobileNumber, Role role, Category category, Status status,
			EmailAddress emailAddress, String creationDate){
		this.customerId = customerId;
		this.passwordHash = passwordHash;
		this.nickName = nickName;
		this.legalName = legalName;
		this.avatarUrl = avatarUrl;
		this.personalHomePageUrl = personalHomePageUrl;
		this.mobileNumber = mobileNumber;
		this.role = role;
		this.category = category;
		this.status = status;
		this.emailAddress = emailAddress;
		this.creationDate = creationDate;
		
	}
	
    public CustomerId getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerId customerId) {
		this.customerId = customerId;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getPersonalHomePageUrl() {
		return personalHomePageUrl;
	}

	public void setPersonalHomePageUrl(String personalHomePageUrl) {
		this.personalHomePageUrl = personalHomePageUrl;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
}
