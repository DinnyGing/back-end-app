package com.my.backend.repositories;

import com.my.backend.models.Tag;
import com.my.backend.models.Word;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class WordRepositoryImpl implements WordRepositoryCustom{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Word> findAllByTag(Long id)
//    {
//
//        Query query = manager.createQuery("SELECT w FROM  Word w " +
//                "JOIN FETCH w.likedTag t WHERE t.id = :id", Word.class);
//        query.setParameter("id", id);
//        return query.getResultList();
//    }
    {
        Query query = manager.createNativeQuery("SELECT w.* FROM  words w " +
                "JOIN tag_word tw ON w.id_word = tw.id_word WHERE tw.id_tag = ?", Word.class);
        query.setParameter(1, id);
        return query.getResultList();
    }

    @Override
    public List<Word> checkName(String name) {
        Query query = manager.createNativeQuery("SELECT w.* FROM  words w WHERE w.name_word = ?", Word.class);
        query.setParameter(1, name);
        return query.getResultList();
    }
    @Override
    public List<Word> checkNameByTag(String name, Long id_tag) {
        Query query = manager.createNativeQuery("SELECT w.* FROM  words w " +
                "JOIN tag_word tw ON w.id_word = tw.id_word WHERE tw.id_tag = ? and w.name_word = ?", Word.class);
        query.setParameter(1, id_tag);
        query.setParameter(2, name);
        return query.getResultList();
    }

    @Override
    public Optional<Word> saveWord(Word word) {
        try {
            manager.getTransaction().begin();
            manager.persist(word);
            manager.getTransaction().commit();
            return Optional.of(word);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
