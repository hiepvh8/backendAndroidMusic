package com.example.music.model.dto;

import com.example.music.Enum.Gender;
import com.example.music.model.entity.User;
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
public class UserUpdate {


        private String fullName;

        private LocalDate birthday;

        @Enumerated(EnumType.STRING)
        private Gender gender;


        private String address;

        private String phoneNumber;

        public UserUpdate(User user){
            this.fullName = user.getFullName();
            this.birthday = user.getBirthday();
            this.gender = user.getGender();
            this.address = user.getAddress();
            this.phoneNumber = user.getPhoneNumber();
        }
}
