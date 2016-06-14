package com.sample.db.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.db.entity.EmployeeEntity;


public interface IEmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
	
}
