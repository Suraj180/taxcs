package com.example.tcs.config;

import com.example.tcs.Controller.AdminRepository;
import com.example.tcs.UserDtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDtlsService implements UserDetailsService {

    @Autowired
    private AdminRepository repo;

    @Override
    public UserDetails loadUserByUsername(String em) throws UsernameNotFoundException {



        try{

            UserDtls u=repo.findByUsername(em);
            if(u==null){
                throw new UsernameNotFoundException("No User");
            } else{
                return new CustomUserDtls(u);
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
