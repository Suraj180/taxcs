package com.example.tcs.Controller;


import com.example.tcs.Taxs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaxRepository extends JpaRepository<Taxs, Long> {

}

