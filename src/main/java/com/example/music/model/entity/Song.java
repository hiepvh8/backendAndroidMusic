package com.example.music.model.entity;

import com.example.music.Enum.Genre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int duration; // Thời lượng giây

    @Enumerated(EnumType.STRING)
    private Genre genre;// thể loại

    private int year;

    private String lyrics;// lời ba hát

    @Column(name = "coverart")
    private String coverArt;

    @Column(name = "playcount")
    private int playCount;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(name = "song_playlist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    private List<PlayList> playlists;

    @OneToMany(mappedBy = "song")
    private List<PlayHistory> playHistories;
}
