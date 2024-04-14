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

import hh.sof3.musiclibrary.domain.Genre;
import hh.sof3.musiclibrary.domain.Song;
import hh.sof3.musiclibrary.domain.SongRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SongRepositoryTest {
    @Autowired
    private SongRepository songRepository;

    @Test
    public void testSaveSong() {
        //Luodaan kappale
        Song song = new Song("Test Song", "Test Artist", 3.5, new Genre("Test Genre"));

        //Tallennetaan kappale
        Song savedSong = songRepository.save(song);

        //Katsotaan onko kappale tallenettu oikein
        assertThat(savedSong).isNotNull();
        assertThat(savedSong.getId()).isNotNull();
        assertEquals("Test Song", savedSong.getName());
        assertEquals("Test Artist", savedSong.getArtist());
        assertEquals(3.5, savedSong.getDuration());
        assertEquals("Test Genre", savedSong.getGenre().getName());
    }

    @Test
    public void testFindById() {
        //Luodaan kappale
        Song song = new Song("Test Song", "Test Artist", 3.5, new Genre("Test Genre"));
        Song savedSong = songRepository.save(song);

        //Etsitään kappale Id:n perusteella
        Optional<Song> optionalSong = songRepository.findById(savedSong.getId());

        //Katsotaan löytyykö kappale
        assertTrue(optionalSong.isPresent());
        Song foundSong = optionalSong.get();
        assertEquals("Test Song", foundSong.getName());
        assertEquals("Test Artist", foundSong.getArtist());
        assertEquals(3.5, foundSong.getDuration());
        assertEquals("Test Genre", foundSong.getGenre().getName());
    }
    @Test
    public void testDeleteSong() {
        //Luodaan kappale
        Song song = new Song("Test Song", "Test Artist", 3.5, new Genre("Test Genre"));
        Song savedSong = songRepository.save(song);

        //Poistetaan kappale
        songRepository.deleteById(savedSong.getId());

        //Tarkistetaan onko kappale poistettu
        assertFalse(songRepository.findById(savedSong.getId()).isPresent());
    }

    @Test
    public void testEditSong() {
        //Luodaan kappale
        Song song = new Song("Test Song", "Test Artist", 3.5, new Genre("Test Genre"));
        Song savedSong = songRepository.save(song);

        //Muokataan kappaletta
        savedSong.setName("Modified Song");
        savedSong.setArtist("Modified Artist");
        savedSong.setDuration(4.2);
        savedSong.getGenre().setName("Modified Genre");
        songRepository.save(savedSong);

        //Haetaan muokattu kappale
        Optional<Song> optionalSong = songRepository.findById(savedSong.getId());

        //Tarkastetaan onko kappaletta muokattu
        assertTrue(optionalSong.isPresent());
        Song modifiedSong = optionalSong.get();
        assertEquals("Modified Song", modifiedSong.getName());
        assertEquals("Modified Artist", modifiedSong.getArtist());
        assertEquals(4.2, modifiedSong.getDuration());
        assertEquals("Modified Genre", modifiedSong.getGenre().getName());
    }
}
