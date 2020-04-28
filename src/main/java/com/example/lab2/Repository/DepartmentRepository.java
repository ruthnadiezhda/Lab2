package com.example.lab2.Repository;

import com.example.lab2.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {


    @Query(value = "SELECT * FROM `hr-sw2`.departments WHERE department_name = ?1",nativeQuery = true)
    List<Department> buscarDepa(String name);



}
