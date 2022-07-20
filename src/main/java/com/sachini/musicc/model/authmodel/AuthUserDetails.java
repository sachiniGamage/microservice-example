package com.sachini.musicc.model.authmodel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthUserDetails extends User implements UserDetails {

    public AuthUserDetails() {
    }

    public AuthUserDetails(User user){
         super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
