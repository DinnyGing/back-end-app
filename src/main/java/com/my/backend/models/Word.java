package com.my.backend.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.NonNull;
import java.util.Set;

@Entity
@Table(name = "words")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_word")
    @NonNull
    Long id;

    @Column(name = "name_word")
    @NonNull
    String name;

    @Column(name = "transcript_word")
    String transcript;

    @Column(name = "url_photo_word")
    @NonNull
    String url_photo_word;

    @NonNull
    @Column(name = "status_learn_word")
    String status;

    @ManyToMany
    @JoinTable(
            name = "tag_word",
            joinColumns = @JoinColumn(name = "id_word"),
            inverseJoinColumns = @JoinColumn(name = "id_tag"))
    Set<Tag> likedTag;

    public Word() {
    }

    public Word(@NonNull String name, String transcript, @NonNull String url_photo_word) {
        this.name = name;
        this.transcript = transcript;
        this.url_photo_word = url_photo_word;
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

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getUrl_photo_word() {
        return url_photo_word;
    }

    public void setUrl_photo_word(String url_photo_word) {
        this.url_photo_word = url_photo_word;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Tag> getLikedTag() {
        return likedTag;
    }

    public void setLikedTag(Set<Tag> likedTag) {
        this.likedTag = likedTag;
    }
}

