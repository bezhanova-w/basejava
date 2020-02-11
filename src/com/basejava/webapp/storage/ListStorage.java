package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage.set((Integer) index, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    protected Resume doGet(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
