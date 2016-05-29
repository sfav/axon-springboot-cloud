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
public class CustomerAddressAddedEvent {
	private CustomerId customerId;
    
	private Address address;

	public Address getAddress() {
		return address;
	}

	public CustomerAddressAddedEvent(CustomerId customerId, Address address){
		this.customerId = customerId;
		this.address = address;
	}
	
    public CustomerId getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerId customerId) {
		this.customerId = customerId;
	}
}
