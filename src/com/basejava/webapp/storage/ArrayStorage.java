package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based com.basejava.webapp.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        if (r == null || r.getUuid() == null) {
            System.out.println("Переданы некорректные данные.");
            return;
        }

        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.printf("Резюме с uuid \"%s\" не найдено в базе.", r.getUuid());
        }
    }

    public void save(Resume r) {
        if (r == null || r.getUuid() == null) {
            System.out.println("Переданы некорректные данные.");
            return;
        }

        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.printf("Резюме с uuid \"%s\" уже есть в базе.", r.getUuid());
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Не удалось добавить резюме: база резюме полностью заполнена.");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void delete(String uuid) {
        if (uuid == null) {
            System.out.println("Переданы некорректные данные.");
            return;
        }

        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.printf("Резюме с uuid \"%s\" не найдено в базе.", uuid);
        }
    }

    protected int getIndex(String uuid) {
        return uuid == null ? -1 : IntStream.range(0, size)
                                            .filter(i -> uuid.equals(storage[i].getUuid()))
                                            .findAny()
                                            .orElse(-1);
    }

    /**
     * @return array, contains only Resumes in com.basejava.webapp.storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
