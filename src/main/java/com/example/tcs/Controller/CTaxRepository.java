package com.example.tcs.Controller;

import com.example.tcs.CTax;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CTaxRepository extends JpaRepository<CTax, Double> {

}
