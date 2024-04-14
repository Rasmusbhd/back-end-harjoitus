package hh.sof3.musiclibrary.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long>{
    List<Song> findByArtist(String artist);
    List<Song> findByDuration(double duration);
    List<Song> findByGenre(Genre genre);
}
