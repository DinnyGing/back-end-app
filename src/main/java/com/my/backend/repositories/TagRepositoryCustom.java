package com.my.backend.repositories;

import com.my.backend.models.Tag;
import com.my.backend.models.User;

import java.util.List;

public interface TagRepositoryCustom {
    List<Tag> checkName(String name, Long id_user);
    List<Tag> findAllByUser(Long id_user);
}
