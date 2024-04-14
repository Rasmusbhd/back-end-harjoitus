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

import hh.sof3.musiclibrary.domain.Playlist;
import hh.sof3.musiclibrary.domain.PlaylistRepository;
import hh.sof3.musiclibrary.domain.SongRepository;

@CrossOrigin
@RestController
public class PlaylistRestController {

    @Autowired
    PlaylistRepository playlistRepository;
    
    @Autowired
    SongRepository songRepository;

    //Etsitään kaikki soittolistat
    @GetMapping("/allPlaylists")
    public @ResponseBody List<Playlist> playlistsRest() {
        return (List<Playlist>) playlistRepository.findAll();
    }
    //Etsitään soittolista Id:n perusteella
    @GetMapping("allPlaylists/{playlistid}")
    public @ResponseBody Optional<Playlist> findPlaylistRest(@PathVariable("playlistid") Long playlistid) {
        return playlistRepository.findById(playlistid);
    }
    //Lisätään uusi soittolista
    @PostMapping("allPlaylists")
    public @ResponseBody Playlist savePlaylistRest(@RequestBody Playlist playlist) {
        return playlistRepository.save(playlist);
    }
}