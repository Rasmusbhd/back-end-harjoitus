package hh.sof3.musiclibrary.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long genreid;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    @JsonIgnoreProperties("genre")
    private List<Song> songs;

    public Genre(String name) {
        this.name = name;
    }
    public Genre() {

    }
    public Long getGenreid() {
        return genreid;
    }
    public void setGenreid(Long genreid) {
        this.genreid = genreid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    @Override
    public String toString() {
        return "Genre [genreid=" + genreid + ", name=" + name + "]";
    }
}
