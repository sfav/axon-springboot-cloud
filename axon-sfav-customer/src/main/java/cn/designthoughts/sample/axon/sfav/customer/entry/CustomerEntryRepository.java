package cn.designthoughts.sample.axon.sfav.customer.entry;

import org.springframework.data.repository.CrudRepository;

public interface CustomerEntryRepository extends CrudRepository<CustomerEntry, String>{
	public CustomerEntry findById(String id);
	public CustomerEntry findByNickName(String nickName);
}
