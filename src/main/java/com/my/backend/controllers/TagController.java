package com.my.backend.controllers;

import com.my.backend.models.Tag;
import com.my.backend.repositories.TagRepository;
import com.my.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{id_user}/tags")
@CrossOrigin
public class TagController {
    TagRepository tagRepository;
    UserRepository userRepository;
    enum TagStatus{
        NOT_BEGIN,
        LEARNING,
        LEARNED
    }
    @Autowired
    public TagController(TagRepository tagRepository, UserRepository userRepository) {
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/")
    public List<Tag> getAllTags(@PathVariable("id_user") Long id_user){
        return tagRepository.findAllByUser(id_user);
    }

    @PostMapping("/add")
    public String addTag(@PathVariable("id_user") Long id_user, @RequestBody Tag tag){
        String res = "You didn`t login";
        if(!userRepository.findById(id_user).isEmpty()){
            tag.setUser(userRepository.findById(id_user).get());
            tag.setStatus(TagStatus.NOT_BEGIN.toString());
            if(tagRepository.checkName(tag.getName(), id_user).isEmpty()) {
                tagRepository.save(tag);
                res = "Tag is added";
            }
            else res = "That tag was already";
        }
        return res;
    }
    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable("id_user") Long id_user, @PathVariable("id") Long id){
        if(!userRepository.findById(id_user).isEmpty()) {
            return tagRepository.findById(id).get();
        }
        return null;
    }

    @PostMapping("/{id}/edit")
    public String editTag(@PathVariable("id_user") Long id_user, @RequestBody Tag editTag, @PathVariable("id") Long id){
        String res = "You didn`t login";
       if(!userRepository.findById(id_user).isEmpty()){
           Tag tag = tagRepository.findById(id).get();
           tag.setName(editTag.getName());
           if(tagRepository.checkName(tag.getName(), id_user).isEmpty()) {
               tagRepository.save(tag);
               res = "Tag is edited";
           }
           else res = "That tag was already";
        }
        return res;
    }
    @PostMapping("/{id}/delete")
    public String deleteTag(@PathVariable("id_user") Long id_user, @PathVariable("id") Long id){
        String res = "You didn`t login";
        if(!userRepository.findById(id_user).isEmpty()) {
            if (tagRepository.findById(id).isEmpty())
                res = "Don`t find this tag";
            else {
                tagRepository.deleteById(id);
                res = "Tag is deleted";
            }
        }
        return res;

    }
}
