package com.my.backend.controllers;


import com.my.backend.models.Tag;
import com.my.backend.models.Word;
import com.my.backend.repositories.TagRepository;
import com.my.backend.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tags/{id}/words")
public class WordController {

    private final WordRepository wordRepository;
    private final TagRepository tagRepository;

    enum WordStatus{
        NOT_BEGIN,
        LEARNING,
        LEARNED
    }
    @Autowired
    public WordController(WordRepository wordRepository,
                          TagRepository tagRepository) {
        this.wordRepository = wordRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/")
    public List<Word> getAllWords(@PathVariable("id") Long id){
        return wordRepository.findAllByTag(id);
    }
    //@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    @PostMapping("/add")
    public String addWord(@PathVariable("id") Long id, @RequestBody Word word){
        word.setStatus(WordStatus.NOT_BEGIN.toString());
        Tag tag = tagRepository.findById(id).get();
        Set<Word> words = tag.getLikesTag();
        if(!wordRepository.checkNameByTag(word.getName(), id).isEmpty()) {
            return "That word is already added";
        }
        Word addWord;
        if(!wordRepository.checkName(word.getName()).isEmpty())
            addWord = wordRepository.checkName(word.getName()).get(0);
        else
            addWord = wordRepository.save(word);
        word.setId(addWord.getId());
        Set<Tag> tags = addWord.getLikedTag();
        if(tags == null)
            tags = new HashSet<>();
        tags.add(tag);
        word.setLikedTag(tags);
        wordRepository.save(word);
        if(words == null)
            words = new HashSet<>();
        words.add(word);
        tag.setLikesTag(words);
        tagRepository.save(tag);
        return "Word is added";
    }
    @GetMapping("/{id_word}")
    public Word getWord(@PathVariable("id_word") Long id_word){
        return wordRepository.getById(id_word);
    }

    @PostMapping("/{id_word}/edit")
    public String editWord(@PathVariable("id") Long id_tag, @PathVariable("id_word") Long id_word, @RequestBody Word newWord){
        Word word = wordRepository.getById(id_word);
        newWord.setStatus(word.getStatus());
        newWord.setId(id_word);
        Tag tag = tagRepository.findById(id_tag).get();
        Set<Word> words = tag.getLikesTag();
        Set<Tag> tags = word.getLikedTag();
        if(tags == null)
            tags = new HashSet<>();
        tags.add(tag);
        newWord.setLikedTag(tags);
        wordRepository.save(newWord);
        if(words == null)
            words = new HashSet<>();
        words.add(newWord);
        tag.setLikesTag(words);
        tagRepository.save(tag);
        return "Word is edited";
    }
    @PostMapping("/{id_word}/delete")
    public String deleteWord(@PathVariable("id_word") Long id_word){
        if(wordRepository.findById(id_word).get() == null)
            return "Don`t find this word";
        wordRepository.deleteById(id_word);
        return "Word is deleted";
    }
}
