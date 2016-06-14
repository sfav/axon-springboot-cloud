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
package cn.designthoughts.sample.axon.sfav.query.customer.entry;
/**
 * @author Thomas Yuan
 */
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
public class CustomerEntry extends AbstractEntity {

    private String identifier;
    
    private String nickName;
    
    private String legalName;
    
    private String avatarUrl;
    
    private String personalHomePageUrl;
    
    @Column(unique=true)
    private String mobileNumber;
    
    private String role;
    
    private String category;
    
    private String status;
    
    @Column(unique = true)
	private String emailAddress;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customer_id")
	private Set<AddressEntry> addresses = new HashSet<AddressEntry>();
	
	private String creationDate;

	public CustomerEntry(String identifier, String nickName, String legalName, String avatarUrl,
			String personalHomePageUrl, String mobileNumber, String role, String category, String status,
			String emailAddress, String creationDate) {
		super();
		this.identifier = identifier;
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
	public CustomerEntry() {

	}
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<AddressEntry> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<AddressEntry> addresses) {
		this.addresses = addresses;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	
    public void addAddress(String building, String street, String city, String country) {
    	this.addresses.add(new AddressEntry( building,  street,  city,  country));  
    }
}