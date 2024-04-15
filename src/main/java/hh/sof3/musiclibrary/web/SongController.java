package hh.sof3.musiclibrary.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof3.musiclibrary.domain.GenreRepository;
import hh.sof3.musiclibrary.domain.Playlist;
import hh.sof3.musiclibrary.domain.PlaylistRepository;
import hh.sof3.musiclibrary.domain.Song;
import hh.sof3.musiclibrary.domain.SongRepository;
import jakarta.validation.Valid;

@Controller
public class SongController {

    @Autowired
    SongRepository songRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    PlaylistRepository playlistRepository;

    //Näytetään kaikki kappaleet thymeleafissa
    @GetMapping("/songlist")
    public String index(Model model) {
        model.addAttribute("songs", songRepository.findAll());
        return "songlist";
    }
    //Lisätään uusi kappale
    @GetMapping("/addsong")
    public String showAddSongForm(Model model) {
        model.addAttribute("song", new Song());
        model.addAttribute("genres", genreRepository.findAll());
        return "addsong";
    }
    //Tallennetaan uusi kappale
    @PostMapping("/savesong")
    public String saveSong(@Valid Song song, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genres", genreRepository.findAll());
            return "addsong";
        }
        songRepository.save(song);
        return "redirect:/songlist";
    }
    //Admin oikeuksilla voi poistaa kappaleen
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deletesong/{id}")
    public String deleteSong(@PathVariable("id") Long id, Model model) {
    if (id != null) {
        Optional<Song> songOptional = songRepository.findById(id);
        
        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            
            List<Playlist> playlists = playlistRepository.findBySongsContains(song);
            
            for (Playlist playlist : playlists) {
                playlist.getSongs().remove(song);
                playlistRepository.save(playlist);
            }
            
            songRepository.deleteById(id);
        }
        }
        return "redirect:/songlist";
    }
    //Admin oikeuksilla voi muokata kappaletta
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editsong/{id}")
    public String editSong(@PathVariable("id") Long id, Model model) {
        if (id != null) {
            Song song = songRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid song Id: " + id));
            model.addAttribute("song", song);
            model.addAttribute("genres", genreRepository.findAll());
        }
        return "editsong";
    }

}
