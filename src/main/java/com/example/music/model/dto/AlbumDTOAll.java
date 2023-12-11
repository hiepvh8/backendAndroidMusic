package com.example.music.model.dto;

import com.example.music.model.entity.Album;
import com.example.music.model.entity.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTOAll {
    private Long id;

    private String title;

    private String description; //Mô tả

    private int duration; //Thời lượng giây
    public AlbumDTOAll(Album album){
        this.id = album.getId();
        this.title = album.getTitle();
        this.duration = album.getDuration();
        this.description = album.getDescription();
    }
}
