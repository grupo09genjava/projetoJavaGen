package com.grupo09.generation.security;

import com.grupo09.generation.model.EmployeeModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
public class EmployeeAuthenticated implements UserDetails{
    private final EmployeeModel employee;

    public EmployeeAuthenticated(EmployeeModel employee){
        this.employee = employee;
    }

    @Override
    public boolean isAccountNonLocked(){
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled(){
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(() -> "read");
    }

    @Override
    public String getPassword(){
        return employee.getPassword();
    }

    @Override
    public String getUsername(){
        return employee.getEmail();
    }

}
