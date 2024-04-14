package hh.sof3.musiclibrary.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.sof3.musiclibrary.domain.GenreRepository;
import hh.sof3.musiclibrary.domain.Song;
import hh.sof3.musiclibrary.domain.SongRepository;

@CrossOrigin
@RestController
public class SongRestController {

    @Autowired
    SongRepository songRepository;
    @Autowired
    GenreRepository genreRepository;

    //Etsitään kaikki kappaleet
    @GetMapping("/allSongs") 
    public @ResponseBody List<Song> songListRest() {
        return (List<Song>) songRepository.findAll();
    }
    //Etsitään kappale Id:n perusteella
    @GetMapping("/allSongs/{id}")
    public @ResponseBody Optional<Song> findSongListRest(@PathVariable("id") Long id) {
        return songRepository.findById(id);
    }
    //Lisätään uusi kappale
    @PostMapping
    public @ResponseBody Song saveSongRest(@RequestBody Song song) {
        return songRepository.save(song);
    }
}
