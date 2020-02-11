package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{
    protected Map<String, Resume> storage = new HashMap<String, Resume>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    protected void doSave(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    protected void doDelete(Object uuid) {
        storage.remove((String) uuid);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
