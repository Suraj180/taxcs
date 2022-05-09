package com.example.tcs.Controller;

import com.example.tcs.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<UserDtls,Integer> {

    public UserDtls findByUsername(String em);

}
