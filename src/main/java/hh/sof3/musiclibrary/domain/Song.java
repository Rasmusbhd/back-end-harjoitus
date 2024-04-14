package hh.sof3.musiclibrary.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Artist is required")
    private String artist;
    @NotNull(message = "Duration is required")
    private double duration;

    @ManyToOne
    @JsonIgnoreProperties("songs")
    @JoinColumn(name = "genreid")
    private Genre genre;

    @ManyToMany(mappedBy = "songs")
    @JsonIgnoreProperties("songs")
    private List<Playlist> playlists;

public Song(String name, String artist, double duration, Genre genre) {
    this.name = name;
    this.artist = artist;
    this.duration = duration;
    this.genre = genre;
}
public Song() {
}
public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getArtist() {
    return artist;
}
public void setArtist(String artist) {
    this.artist = artist;
}
public double getDuration() {
    return duration;
}
public void setDuration(double duration) {
    this.duration = duration;
}
public Genre getGenre() {
    return genre;
}
public void setGenre(Genre genre) {
    this.genre = genre;
}
public List<Playlist> getPlaylists() {
    return playlists;
}
public void setPlaylists(List<Playlist> playlists) {
    this.playlists = playlists;
}
}
