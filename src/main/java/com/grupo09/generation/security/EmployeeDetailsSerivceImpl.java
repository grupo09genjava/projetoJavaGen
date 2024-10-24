package com.grupo09.generation.security;

import com.grupo09.generation.exception.NotFoundException;
import com.grupo09.generation.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsSerivceImpl implements UserDetailsService{
    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsSerivceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return employeeRepository.findByEmail(email).map(EmployeeAuthenticated::new)
                .orElseThrow(() -> new NotFoundException("Employee not found in the database."));
    }
}
