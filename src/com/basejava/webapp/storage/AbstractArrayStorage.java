package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        if (uuid == null) {
            System.out.println("Переданы некорректные данные.");
            return null;
        }

        int index = getIndex(uuid);
        if (index >= 0)
            return storage[index];
        else {
            System.out.printf("Резюме с uuid \"%s\" не найдено в базе.", uuid);
            return null;
        }
    }

    protected abstract int getIndex(String uuid);
}
