package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based com.basejava.webapp.storage for Resumes
 */
public interface Storage {

    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in com.basejava.webapp.storage (without null)
     */
    Resume[] getAll();

    int size();
}
