package com.example.testgit.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(nullable = true)
    private String address;
    @Column(nullable = true)
    private String phoneNumber;
    @Column(nullable = true)
    private Date birthday;
    @Column(nullable = true)
    private String sex;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private String hobby;
    @Column(nullable = true)
    private String avatar;
    @Column(nullable = true)
    private String background;
    private Boolean enabled = false;
    private Boolean locked = false;
    public User(String firstName,
                String lastName ,
                String password,
                String email,
                String avatar,
                UserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(simpleGrantedAuthority);
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
