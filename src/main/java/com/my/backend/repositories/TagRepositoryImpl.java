package com.my.backend.repositories;

import com.my.backend.models.Tag;
import com.my.backend.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class TagRepositoryImpl implements TagRepositoryCustom{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Tag> checkName(String name, Long id) {
        Query query = manager.createNativeQuery("SELECT t.* FROM Tags t " +
                "WHERE t.name_tag = ? and t.id_user = ?", Tag.class);
        query.setParameter(1, name);
        query.setParameter(2, id);
        return query.getResultList();
    }

    @Override
    public List<Tag> findAllByUser(Long id_user) {
        Query query = manager.createNativeQuery("SELECT * FROM Tags  " +
                "WHERE id_user = ?", Tag.class);
        query.setParameter(1, id_user);
        return query.getResultList();
    }
}
