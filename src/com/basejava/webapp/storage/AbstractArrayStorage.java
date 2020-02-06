package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in com.basejava.webapp.storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public Resume get(String uuid) {
        if (uuid == null) {
            System.out.println("Переданы некорректные данные.");
            return null;
        }

        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " is not exists");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    @Override
    public void save(Resume r) {
        if (r == null || r.getUuid() == null) {
            System.out.println("Переданы некорректные данные.");
            return;
        }

        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + "is already exists");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            int indexTo = prepareAndGetIndexToSave(index);
            storage[indexTo] = r;
            size++;
        }
    }

    protected abstract int prepareAndGetIndexToSave(int index);

    @Override
    public void update(Resume r) {
        if (r == null || r.getUuid() == null) {
            System.out.println("Переданы некорректные данные.");
            return;
        }

        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Resume " + r.getUuid() + "is not exists");
        } else {
            updateByIndex(index, r);
        }
    }

    protected abstract void updateByIndex(int index, Resume r);

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void delete(String uuid) {
        if (uuid == null) {
            System.out.println("Переданы некорректные данные.");
            return;
        }

        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + "is not exists");
        } else {
            deleteByIndex(index);
            size--;
        }
    }

    protected abstract void deleteByIndex(int index);
}
