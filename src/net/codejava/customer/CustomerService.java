package net.codejava.customer;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {
	@Autowired
	public CustomerRepository repo;
	
	public List<Customer> listAll(){
		
		return (List<Customer>) repo.findAll();
		
	}
	
	public void save(Customer customer) {
		
		 repo.save(customer);
	}

	 public Customer get(Long id) {
	        return repo.findById(id).get();
	    }
	     
	    public void delete(Long id) {
	        repo.deleteById(id);
	    }
	    public List<Customer> search(String keyword) {
	        return repo.search(keyword);
	    }

}
