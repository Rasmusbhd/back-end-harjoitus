package hh.sof3.musiclibrary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3.musiclibrary.domain.Genre;
import hh.sof3.musiclibrary.domain.GenreRepository;
import hh.sof3.musiclibrary.domain.Playlist;
import hh.sof3.musiclibrary.domain.PlaylistRepository;
import hh.sof3.musiclibrary.domain.Song;
import hh.sof3.musiclibrary.domain.SongRepository;
import hh.sof3.musiclibrary.domain.User;
import hh.sof3.musiclibrary.domain.UserRepository;


@SpringBootApplication
public class MusiclibraryApplication {

	private static final Logger log = LoggerFactory.getLogger(MusiclibraryApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MusiclibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner playlistDemo(SongRepository songRepository, PlaylistRepository playlistRepository, GenreRepository genreRepository, UserRepository userRepository) {
		return (args) -> {

			Genre g1 = new Genre("");
			genreRepository.save(g1);
			Genre g2 = new Genre("Pop");
			genreRepository.save(g2);
			Genre g3 = new Genre("Hip hop");
			genreRepository.save(g3);
			Genre g4 = new Genre("Rock");
			genreRepository.save(g4);
			Genre g5 = new Genre("Rap");
			genreRepository.save(g5);
			Genre g6 = new Genre("R&B");
			genreRepository.save(g6);
			Genre g7 = new Genre("EDM");
			genreRepository.save(g7);
			Genre g8 = new Genre("Country");
			genreRepository.save(g8);
			Genre g9 = new Genre("Jazz");
			genreRepository.save(g9);
			Genre g10 = new Genre("Reggae");
			genreRepository.save(g10);
			Genre g11 = new Genre("Classical");
			genreRepository.save(g11);
			Genre g12 = new Genre("Suomipop");
			genreRepository.save(g12);
			
			Song s1 = new Song("Tuulisii", "Jvg", 3.40, g12);
			songRepository.save(s1);
			Song s2 = new Song("Mikä kesä", "Valvomo", 3.33, g12);
			songRepository.save(s2);
			Song s3 = new Song("Mitä silmät ei nää", "Juha Tapio", 3.42, g12);
			songRepository.save(s3);
			Song s4 = new Song("Sulle tehty", "Abreu", 3.21, g12);
			songRepository.save(s4);
			Song s5 = new Song("Huhtikuu", "Ellis", 2.45, g12);
			songRepository.save(s5);
			Song s6 = new Song("Purple Haze", "Jimi Hendrix", 2.53, g4);
			songRepository.save(s6);
			Song s7 = new Song("Jolene", "Dolly Parton", 2.42, g8);
			songRepository.save(s7);
			Song s8 = new Song("A Lalala Long", "Bob Marley", 3.47, g10);
			songRepository.save(s8);
			Song s9 = new Song("Wake me up", "Avicii", 4.33, g7);
			songRepository.save(s9);
			Song s10 = new Song("In da Club", "50 Cent", 4.08, g5);
			songRepository.save(s10);
			Song s11 = new Song("Illusion", "Dua Lipa", 3.08, g2);
			songRepository.save(s11);
			Song s12 = new Song("Greedy", "Tate McRae", 2.12, g2);
			songRepository.save(s12);
			Song s13 = new Song("Hotline Bling", "Drake", 4.25, g2);
			songRepository.save(s13);
			Song s14 = new Song("Staying alive", "Bee Gees", 4.45, g2);
			songRepository.save(s14);
			Song s15 = new Song("Someone like you", "Adele", 4.45, g2);
			songRepository.save(s15);


			Playlist p1 = new Playlist("Suomilista", "Suomalaisia kappaleita");
			playlistRepository.save(p1);
			Playlist p2 = new Playlist("Sekoitus", "Kaikenlaisia kappeleita");
			playlistRepository.save(p2);
			Playlist p3 = new Playlist("Lempilista", "Lempi kappaleet");
			playlistRepository.save(p3);

			p1.getSongs().add(s1);
			p1.getSongs().add(s2);
			p1.getSongs().add(s3);
			p1.getSongs().add(s4);
			p1.getSongs().add(s5);
			playlistRepository.save(p1);

			p2.getSongs().add(s6);
			p2.getSongs().add(s7);
			p2.getSongs().add(s8);
			p2.getSongs().add(s9);
			p2.getSongs().add(s10);
			playlistRepository.save(p2);

			p3.getSongs().add(s11);
			p3.getSongs().add(s12);
			p3.getSongs().add(s13);
			p3.getSongs().add(s14);
			p3.getSongs().add(s15);
			playlistRepository.save(p3);


			User user1 = new User("testuser", "$2a$10$Rpx60MEd1pDdjtZFgW9DMeh6N9QJwShxBYyvx0QLGl.Uf2O84UzDi", "USER");
			User user2 = new User("admin", "$2a$10$VKY3p0EEHB7HKKht54S6QeCZPuYiQEYUvRr3w4c8LMflE/t0GQjAe", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);


			log.info("fetch all songs");
			for (Song song : songRepository.findAll()) {
				log.info(song.toString());
			}
			log.info("fetch all playlists");
			for (Playlist playlist : playlistRepository.findAll()) {
				log.info(playlist.toString());
			}
		};
	}
}
