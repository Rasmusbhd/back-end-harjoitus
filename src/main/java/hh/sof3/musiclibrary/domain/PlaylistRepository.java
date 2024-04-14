package hh.sof3.musiclibrary.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
    List<Playlist> findByPlaylistid(Long playlistid);
    List<Playlist> findBySongsContains(Song song);
}
