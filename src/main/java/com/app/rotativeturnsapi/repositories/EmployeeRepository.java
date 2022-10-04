package com.app.rotativeturnsapi.repositories;

import com.app.rotativeturnsapi.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
