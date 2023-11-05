package com.example.music.serviceImp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.music.Enum.Genre;
import com.example.music.exception.NotFoundException;
import com.example.music.model.dto.SongDTO;
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

    public Song getSongById(Long songId){
        return songRepository.getSongById(songId);
    }
    public List<Song> getAllSong(){
        return songRepository.findAll();
    }

    public List<Song> getAllSongForGenre(Genre genre){
        return songRepository.findByGenre(genre);
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
    public String xemTruocAvatar( MultipartFile file){
        String url = "";
        try {
            url = createPathUrlForImage(file);
        } catch (Exception e){
            System.out.println(e.getMessage());

        }
        return url;
    }
    public String xemTruocAudio( MultipartFile file){
        String url = "";
        try {
            url = createPathUrlForImage(file);
        } catch (Exception e){
            System.out.println(e.getMessage());

        }
        return url;
    }
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

//    public boolean addSong(SongDTO songDTO, MultipartFile imageFile, MultipartFile audioFile){
//        boolean temp = false;
//        try{
//            Song song = new Song();
//            song.setTitle(songDTO.getTitle());
//            song.setDuration(songDTO.getDuration());
//            song.setGenre(songDTO.getGenre());
//            song.setYear(songDTO.getYear());
//            song.setLyrics(songDTO.getLyrics());
//            String urlImage = createPathUrlForImage(imageFile);
//            String urlAudio = createPathUrlForMedia(audioFile);
//
//            if(urlImage != null && urlAudio != null){
//                song.setCoverArt(urlImage);
//                song.setFilePath(urlAudio);
//                songRepository.save(song);
//                temp = true;
//            }else{
//                throw new NotFoundException("Thêm bài hát không thành công!");
//            }
//        }catch (Exception e){
//            new NotFoundException("Lỗi!");
//        }
//        return temp;
//    }

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

}
