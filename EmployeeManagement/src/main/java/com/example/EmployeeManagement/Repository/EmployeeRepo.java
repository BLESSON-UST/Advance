package com.example.EmployeeManagement.Repository;

import com.example.EmployeeManagement.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Optional<Object> findEmployeeById(Long id);

    void deleteEmployeeById(Long id);
}
