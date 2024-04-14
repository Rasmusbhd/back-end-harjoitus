package hh.sof3.musiclibrary.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hh.sof3.musiclibrary.domain.Playlist;
import hh.sof3.musiclibrary.domain.PlaylistRepository;
import hh.sof3.musiclibrary.domain.Song;
import hh.sof3.musiclibrary.domain.SongRepository;
import jakarta.validation.Valid;

@Controller
public class PlaylistController {

    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    SongRepository songRepository;
    
    //Näytetään kaikki soittolistat
    @GetMapping("/playlists")
    public String index(Model model) {
        model.addAttribute("playlists", playlistRepository.findAll());
        return "playlists";
    }
    //Lisätään uusi soittolista
    @GetMapping("/addplaylist")
    public String addPlaylistForm(Model model) {
        model.addAttribute("playlist", new Playlist());
        return "addplaylist";
    }
    //Tallennetaan uusi soittolista
    @PostMapping("/saveplaylist")
    public String savePlaylist(@Valid Playlist playlist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addplaylist";
        }
        playlistRepository.save(playlist);
        return "redirect:/playlists";
    }
    //Admin oikeuksilla voi poistaa soittolistan
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("deleteplaylist/{playlistid}")
    public String deletePlaylist(@PathVariable("playlistid") Long playlistid, Model model) {
        if (playlistid != null) {
            playlistRepository.deleteById(playlistid);
        }
        return "redirect:/playlists";
    }
    //Admin oikeuksilla voi muokata soittolistaa
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("editplaylist/{playlistid}")
    public String editPlaylist(@PathVariable("playlistid") Long playlistid, Model model) {
        if (playlistid != null) {
            Playlist playlist = playlistRepository.findById(playlistid)
            .orElseThrow(() -> new IllegalArgumentException("Invalid playlist Id: " + playlistid));
            model.addAttribute("playlist", playlist);
        }
        return "editplaylist";
    }
    //Näytetään soittolistan kappaleet
    @SuppressWarnings("null")
    @GetMapping("/playlist/{playlistid}/songs")
    public String showSongList(@PathVariable("playlistid") Long playlistid, Model model ) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistid);
        if (playlistOptional.isPresent()) {
            Playlist playlist = playlistOptional.get();
            model.addAttribute("playlist", playlist);
            Iterable<Song> songs = songRepository.findAll();
            model.addAttribute("songs", songs);
            return "playlist";
        } else {
            return "error";
        }
    }
    //Lisätään soittolistaan uusi kappale
    @GetMapping("/playlist/{playlistid}/addSong")
    public String showAddSongForm(@PathVariable("playlistid") Long playlistid, Model model) {
        @SuppressWarnings("null")
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistid);
        Iterable<Song> songs = songRepository.findAll();

        if (playlistOptional.isPresent()) {
            Playlist playlist = playlistOptional.get();
            model.addAttribute("playlist", playlist);
            model.addAttribute("songs", songs);
            return "addsongtoplaylist";
        } else {
            return "error";
        }
    }
    //Tallennetaan soittolistaan kappale
    @PostMapping("/playlist/{playlistid}/addSong")
    public String saveSongToPlaylist(@PathVariable("playlistid") Long playlistid, @RequestParam("songId") Long id) {
        @SuppressWarnings("null")
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistid);
        @SuppressWarnings("null")
        Optional<Song> songOptional = songRepository.findById(id);

        if (playlistOptional.isPresent() && songOptional.isPresent()) {
            Playlist playlist = playlistOptional.get();
            Song song = songOptional.get();

            if (!playlist.getSongs().contains(song)) {
                playlist.getSongs().add(song);
                playlistRepository.save(playlist);
            }
            return "redirect:/playlist/" + playlistid + "/songs";
        } else {
            return "error";
        }
    }
    //Admin oikeuksilla voi poistaa soittolistasta kappale
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/playlist/{playlistid}/deleteSong/{id}")
    public String deleteSongFromPlaylist(@PathVariable("playlistid") Long playlistid, @PathVariable("id") Long id) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistid);
        Optional<Song> songOptional = songRepository.findById(id);

        if (playlistOptional.isPresent() && songOptional.isPresent()) {
            Playlist playlist = playlistOptional.get();
            Song song = songOptional.get();

            playlist.getSongs().remove(song);
            playlistRepository.save(playlist);

            return "redirect:/playlist/" + playlistid + "/songs";
        } else {
            return "error";
        }
    }
    }
