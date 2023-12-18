package com.example.music.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayListDTO {
    private Long id;
    private String title;
    private LocalDate timestamp;
    private int numberSong;
    private int duration;
}
