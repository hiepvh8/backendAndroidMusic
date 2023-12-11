package com.example.music.serviceImp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.music.Enum.Genre;
import com.example.music.exception.NotFoundException;
import com.example.music.model.dto.SongDTO;
import com.example.music.model.dto.SongDTOAll;
import com.example.music.model.entity.Album;
import com.example.music.model.entity.Song;
import com.example.music.repository.SongRepository;
import com.example.music.service.AlbumService;
import com.example.music.service.SongService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SongServiceImp implements SongService {
    private final SongRepository songRepository;
    private final Cloudinary cloudinary;
    private final AlbumService albumService;

    public SongServiceImp(SongRepository songRepository, Cloudinary cloudinary, AlbumService albumService) {
        this.songRepository = songRepository;
        this.cloudinary = cloudinary;
        this.albumService = albumService;
    }

    // Map Song -> SongDTOAll.
    public List<SongDTOAll> mapSongsToSongDTOAll(List<Song> songs) {
        List<SongDTOAll> songDTOAllList = new ArrayList<>();

        for (Song song : songs) {
            SongDTOAll songDTOAll = new SongDTOAll(song);
            songDTOAllList.add(songDTOAll);
        }

        return songDTOAllList;
    }

    public Song getSongById(Long songId){
        return songRepository.getSongById(songId);
    }
    public List<SongDTOAll> getAllSong(){
        return mapSongsToSongDTOAll(songRepository.findAll());
    }

    public List<SongDTOAll> getAllSongForGenre(Genre genre){
        return mapSongsToSongDTOAll(songRepository.findByGenre(genre));
    }



    //Path Media
    public String createPathUrlForMedia(MultipartFile file) {
        String url = "";
        try {
            Map response = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            url = response.get("url").toString();
        } catch (Exception e) {
            url = e.getMessage();
        }
        return url;
    }

    //Path image
    public String createPathUrlForImage(MultipartFile file) {
        String f = "";
        try {
            Map x = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            f = (x.get("url").toString());
        } catch (Exception e) {
            f = e.getMessage();
        }
        return f;
    }
    //Xem truoc image
//    public String xemTruocAvatar( MultipartFile file){
//        String url = "";
//        try {
//            url = createPathUrlForImage(file);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//
//        }
//        return url;
//    }
//    public String xemTruocAudio( MultipartFile file){
//        String url = "";
//        try {
//            url = createPathUrlForImage(file);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//
//        }
//        return url;
//    }
    public List<String> xemTruocAll( MultipartFile imageFile, MultipartFile audioFile){
        List<String> lists = new ArrayList<>();
        String urlImage = "";
        String urlAudio = "";
        try {
            urlImage = createPathUrlForImage(imageFile);
            urlAudio = createPathUrlForImage(audioFile);
            lists.add(urlImage);
            lists.add(urlAudio);
        } catch (Exception e){
            System.out.println(e.getMessage());

        }
        return lists;
    }

    public Song addSong(SongDTO songDTO, Long albumId){
        Album album = albumService.getAlbumById(albumId);
        if(album == null){
            throw new NotFoundException("Album không tồn tại.");
        }else {
            Song song = new Song();
            song.setTitle(songDTO.getTitle());
            song.setDuration(songDTO.getDuration());
            song.setGenre(songDTO.getGenre());
            song.setYear(songDTO.getYear());
            song.setLyrics(songDTO.getLyrics());
            song.setAlbum(album);
            songRepository.save(song);
            return song;
        }
    }

    public Song updateSong(MultipartFile imageFile, MultipartFile audioFile, Long songId){
        String urlImage = "";
        String urlAudio = "";
        try {
            urlImage = createPathUrlForImage(imageFile);
            urlAudio = createPathUrlForImage(audioFile);
            Song song = getSongById(songId);
            if(song == null){
                throw new NotFoundException("Không tồn tại id bài hát.");
            }else{
                song.setCoverArt(urlImage);
                song.setFilePath(urlAudio);
                songRepository.save(song);
                return song;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());

        }
        return null;
    }
    //Tìm kiếm gần đúng theo tên bài hát
    @Override
    public List<SongDTOAll> searchSongsByPartialTitle(String partialTitle) {
        List<Song> songs = songRepository.findByTitleContaining(partialTitle);
        return mapSongsToSongDTOAll(songs);
    }

    //Nghe bài hát tăng biến dem
    public void incrementPlayCount(Long songId) {
        Optional<Song> song = songRepository.findById(songId);
        if(song.isEmpty()){
            throw new NotFoundException("Baì hát không tồn tại. ");
        }else{
            Song songTemp = song.orElse(null);
            int currentPlayCount = songTemp.getPlayCount();
            songTemp.setPlayCount(currentPlayCount + 1);
            songRepository.save(songTemp);
        }
    }

    //Top bai hat nổi bật
    @Override
    public List<SongDTOAll> getSongsByPlayCountDescending() {
        List<Song> songs = songRepository.findAllByOrderByPlayCountDesc();
        return mapSongsToSongDTOAll(songs);
    }

    @Override
    public List<Song> getSongsByAlbumId(Long albumId) {
        return songRepository.findByAlbumId(albumId);
    }
}
