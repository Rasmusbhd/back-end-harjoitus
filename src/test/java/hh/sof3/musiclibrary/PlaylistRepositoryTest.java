package hh.sof3.musiclibrary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof3.musiclibrary.domain.Playlist;
import hh.sof3.musiclibrary.domain.PlaylistRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlaylistRepositoryTest {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Test
    public void testSavePlaylist() {
        //Luodaan soittolista
        Playlist playlist = new Playlist("Test Playlist", "Test Description");

        //Tallenetaan soittolista
        Playlist savedPlaylist = playlistRepository.save(playlist);

        //Katsotaan onko soittolista tallennettu oikein
        assertThat(savedPlaylist).isNotNull();
        assertThat(savedPlaylist.getPlaylistid()).isNotNull();
        assertEquals("Test Playlist", savedPlaylist.getName());
        assertEquals("Test Description", savedPlaylist.getDescription());
    }

    @Test
    public void testFindById() {
        //Tehdään soittolista
        Playlist playlist = new Playlist("Test Playlist", "Test Description");
        Playlist savedPlaylist = playlistRepository.save(playlist);

        //Etsitään soittolista Id:n perusteella
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(savedPlaylist.getPlaylistid());

        //Katsotaan löytyykö soittolista
        assertTrue(optionalPlaylist.isPresent());
        Playlist foundPlaylist = optionalPlaylist.get();
        assertEquals("Test Playlist", foundPlaylist.getName());
        assertEquals("Test Description", foundPlaylist.getDescription());
    }
    @Test
    public void testDeletePlaylist() {
        //Luodaan soittolista
        Playlist playlist = new Playlist("Test Playlist", "Test Description");
        Playlist savedPlaylist = playlistRepository.save(playlist);

        //Poistetaan soittolista
        playlistRepository.deleteById(savedPlaylist.getPlaylistid());

        //Katsotaan onko soittolista poistettu
        assertFalse(playlistRepository.findById(savedPlaylist.getPlaylistid()).isPresent());
    }

    @Test
    public void testEditPlaylist() {
        //Luodaan soittolista
        Playlist playlist = new Playlist("Test Playlist", "Test Description");
        Playlist savedPlaylist = playlistRepository.save(playlist);

        //Muokataan soittolistaa
        savedPlaylist.setName("Modified Playlist");
        savedPlaylist.setDescription("Modified Description");
        playlistRepository.save(savedPlaylist);

        //Haetaan muokattu soittolista
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(savedPlaylist.getPlaylistid());

        //Tarkastetaan onko soittolistaa muokattu
        assertTrue(optionalPlaylist.isPresent());
        Playlist modifiedPlaylist = optionalPlaylist.get();
        assertEquals("Modified Playlist", modifiedPlaylist.getName());
        assertEquals("Modified Description", modifiedPlaylist.getDescription());
    }
}
