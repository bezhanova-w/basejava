package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.model.ResumeTestData;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(ResumeTestData.R1);
        storage.save(ResumeTestData.R2);
        storage.save(ResumeTestData.R3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(Arrays.asList(ResumeTestData.R1, ResumeTestData.R2, ResumeTestData.R3), list);
    }

    @Test
    public void get() {
        assertGet(ResumeTestData.R1);
        assertGet(ResumeTestData.R2);
        assertGet(ResumeTestData.R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(ResumeTestData.R4);
        assertSize(4);
        assertGet(ResumeTestData.R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(ResumeTestData.R1);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(ResumeTestData.UUID_1, "Name1");
        storage.update(newResume);
        assertTrue(newResume == storage.get(ResumeTestData.UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(ResumeTestData.R4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(ResumeTestData.UUID_1);
        assertSize(2);
        storage.get(ResumeTestData.UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}