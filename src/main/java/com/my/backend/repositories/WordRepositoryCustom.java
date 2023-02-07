package com.my.backend.repositories;

import com.my.backend.models.Tag;
import com.my.backend.models.Word;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WordRepositoryCustom {
    List<Word> findAllByTag(Long id);
    List<Word> checkName(String name);
    List<Word> checkNameByTag(String name, Long id_tag);
    Optional<Word> saveWord(Word word);
}
