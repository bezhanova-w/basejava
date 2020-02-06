package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based com.basejava.webapp.storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected int prepareAndGetIndexToSave(int index) {

        //сейчас index = -indexTo-1, где indexTo - это индекс первого элемента массива, который больше r
        //поэтому сдвигаем все значения после to вправо и вставляем наше резюме.
        int indexTo  = - index - 1;
        if (size + 1 - indexTo >= 0) {
            System.arraycopy(storage, indexTo, storage, indexTo + 1, size + 1 - indexTo);
        }
        return indexTo;
    }

    @Override
    protected void updateByIndex(int index, Resume r) {
        /** не просто сохраняем резюме в ячейку index, т.к. массив должен быть отсортирован.
         * если compareTo  в классе Resume будет включать не только сравнение полей uuid, то
         * вставка в ячейку index окажется неправильной (не соблюдающей порядок сортировки),
         * ведь резюме r мы находим по полю uuid. Поэтому удаляем старое и ищем место для нового.
         **/
         deleteByIndex(index);
         save(r);
    }

    @Override
    protected void deleteByIndex(int index) {
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size -1] = null;
    }
}
