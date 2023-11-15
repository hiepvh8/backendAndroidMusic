package com.example.music.model.dto;

import com.example.music.Enum.Gender;
import com.example.music.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOAll {
    private Long id;

    private String username;

    private String email;

    private String fullName;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profilePicture;

    private String address;

    private String phoneNumber;

    public UserDTOAll(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.birthday = user.getBirthday();
        this.gender = user.getGender();
        this.profilePicture = user.getProfilePicture();
        this.address = user.getAddress();
        this.phoneNumber = user.getPhoneNumber();
    }
}
