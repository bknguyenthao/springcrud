package com.nguyenthao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenthao.domain.Customer;

public interface CustomerJpaRepository extends JpaRepository<Customer, Integer> {
	List<Customer> findByNameContaining(String name);

	List<Customer> findByAddressContaining(String address);

	List<Customer> findByEmailContaining(String email);

	List<Customer> findByPhoneContaining(String phone);

}
