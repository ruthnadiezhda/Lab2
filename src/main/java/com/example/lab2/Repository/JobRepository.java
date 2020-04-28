package com.example.lab2.Repository;

import com.example.lab2.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {


    @Query(value ="select * from jobs where min_salary < ?1 < max_salary",
    nativeQuery =true)
    List <Job> buscarSalario(Integer salar );

}
