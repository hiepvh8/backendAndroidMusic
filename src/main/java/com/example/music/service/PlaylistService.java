package com.example.music.service;

import com.example.music.model.dto.PlayListDTO;
import com.example.music.model.entity.PlayList;

import java.util.List;

public interface PlaylistService {

    List<PlayListDTO> getPlayListsByUsername(String username);



    PlayList addPlaylistForUser(String username, PlayListDTO playListDTO);

}
