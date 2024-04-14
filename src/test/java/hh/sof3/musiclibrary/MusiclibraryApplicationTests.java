package hh.sof3.musiclibrary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof3.musiclibrary.web.PlaylistController;
import hh.sof3.musiclibrary.web.SongController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MusiclibraryApplicationTests {

	@Autowired
	PlaylistController playlistController;
	@Autowired
	SongController songController;

	@Test
	void contextLoads() {
		assertThat(playlistController).isNotNull();
		assertThat(songController).isNotNull();
	}

}
