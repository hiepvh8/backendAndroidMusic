package com.example.music.model.dto;

import com.example.music.Enum.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {
    private String title;
    private int duration;

    @Enumerated(EnumType.STRING)
    private Genre genre;// thể loại

    private int year;

    private String lyrics;// lời ba hát
}
