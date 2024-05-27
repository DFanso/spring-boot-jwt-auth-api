package com.dfanso.spring_jwt_auth.auth.service;

import com.dfanso.spring_jwt_auth.auth.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    @Getter
    private String email;
    private String password;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String email, String password, String firstName, String lastName, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword(),
                user.getFirstname(), // Assuming User class has a getFirstname() method
                user.getLastname(), // Assuming User class has a getLastname() method
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can implement your own logic here
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // You can implement your own logic here
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You can implement your own logic here
    }

    @Override
    public boolean isEnabled() {
        return true; // You can implement your own logic here
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl that = (UserDetailsImpl) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}