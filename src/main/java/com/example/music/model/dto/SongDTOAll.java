package com.example.music.model.dto;

import com.example.music.Enum.Genre;
import com.example.music.model.entity.Song;
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
public class SongDTOAll {
    private Long id;
    private String title;
    private String filePath;
    private int duration;
    @Enumerated(EnumType.STRING)
    private Genre genre;// thể loại
    private int year;
    private String lyrics;// lời ba hát
    private String coverArt;
    private int playCount;

    public SongDTOAll(Song song){
        this.id = song.getId();
        this.title = song.getTitle();
        this.filePath = song.getFilePath();
        this.duration = song.getDuration();
        this.genre = song.getGenre();
        this.year = song.getYear();
        this.lyrics = song.getLyrics();
        this.coverArt = song.getCoverArt();
        this.playCount = song.getPlayCount();
    }
}
