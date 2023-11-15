package com.example.music.model.entity;

import com.example.music.Enum.Gender;
import com.example.music.model.dto.SignupDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "fullname")
    private String fullName;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "profilepicture")
    private String profilePicture;

    private String address;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private Album album;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Feadback> feadbacks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Folower> folowers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<PlayList> playLists;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<PlayHistory> playHistories;

    public User(SignupDTO signupDTO){
        this.username = signupDTO.getUsername();
        this.password = signupDTO.getPassword();
        this.email = signupDTO.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
