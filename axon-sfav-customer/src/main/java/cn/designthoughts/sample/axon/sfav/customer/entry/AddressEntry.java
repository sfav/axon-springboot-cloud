/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.designthoughts.sample.axon.sfav.customer.entry;

import javax.persistence.Entity;

import org.springframework.util.Assert;

/**
 * An address.
 * 
 * @author Oliver Gierke
 */

@Entity
public class AddressEntry extends AbstractEntity {

	private final String building, street, city, country;
	
	
	/**
	 * Creates a new {@link AddressEntry} from the given street, city and country.
	 * 
	 * @param building must not be {@literal null} or empty.
	 * @param street must not be {@literal null} or empty.
	 * @param city must not be {@literal null} or empty.
	 * @param country must not be {@literal null} or empty.
	 */
	public AddressEntry(String building, String street, String city, String country) {

		Assert.hasText(building, "Building must not be null or empty!");
		Assert.hasText(street, "Street must not be null or empty!");		
		Assert.hasText(city, "City must not be null or empty!");
		Assert.hasText(country, "Country must not be null or empty!");

		this.building = building;
		this.street = street;
		this.city = city;
		this.country = country;
	}

	/**
	 * Returns a copy of the current {@link AddressEntry} instance which is a new entity in terms of persistence.
	 * 
	 * @return
	 */
	public AddressEntry getCopy() {
		return new AddressEntry(this.building, this.street, this.city, this.country);
	}
	/**
	 * Returns the building.
	 * 
	 * @return
	 */
	public String getBuilding() {
		return building;
	}
	
	/**
	 * Returns the street.
	 * 
	 * @return
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Returns the city.
	 * 
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Returns the country.
	 * 
	 * @return
	 */
	public String getCountry() {
		return country;
	}
}
