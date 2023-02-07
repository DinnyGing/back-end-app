package com.my.backend.models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.NonNull;

import java.util.Set;

@Entity
@Table(name = "tags")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    Long id;

    @Column(name = "name_tag")
    String name;

    @Column(name = "status_learn_tag")
    String status;

    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    User user;

    @ManyToMany(mappedBy = "likedTag")
    @JsonBackReference
    Set<Word> likesTag;
    public Tag() {
    }

    public Tag(@NonNull String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Word> getLikesTag() {
        return likesTag;
    }

    public void setLikesTag(Set<Word> likesTag) {
        this.likesTag = likesTag;
    }
    public void addWord(Word word) {
        likesTag.add(word);
        word.getLikedTag().add(this);
    }
}
