package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based com.basejava.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return uuid == null ? -1 : IntStream.range(0, size)
                .filter(i -> uuid.equals(storage[i].getUuid()))
                .findAny()
                .orElse(-1);    }
}
