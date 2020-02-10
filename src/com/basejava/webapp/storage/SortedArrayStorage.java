package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based com.basejava.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume r, int index) {

        //сейчас index = -indexTo-1, где indexTo - это индекс первого элемента массива, который больше r
        //поэтому сдвигаем все значения после to вправо и вставляем наше резюме.
        int indexTo = -index - 1;
        if (size + 1 - indexTo > 0) {
            System.arraycopy(storage, indexTo, storage, indexTo + 1, size - indexTo);
        }
        storage[indexTo] = r;
    }

    @Override
    protected void deleteByIndex(int index) {
        int numMoved = size - 1 - index;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }
}
