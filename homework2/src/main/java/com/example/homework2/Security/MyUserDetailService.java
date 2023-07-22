package com.example.homework2.Security;

import com.example.homework2.dao.DaoAuthor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService
{
    private final DaoAuthor daoAuthor;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return daoAuthor.findByName(s);
    }

}
