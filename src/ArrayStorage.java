import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (r == null || r.uuid == null) return;

        int index = getIndex(r.uuid);
        if (index >= 0)
            storage[index] = r;
        else {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Не удалось добавить резюме: база резюме полностью заполнена.");
            }
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        return index >= 0 ? storage[index] : null;
    }

    private int getIndex(String uuid) {

        return uuid == null ? -1 : IntStream.range(0, size)
                                            .filter(i -> uuid.equals(storage[i].uuid))
                                            .findAny()
                                            .orElse(-1);
    }

    void delete(String uuid) {

        int index = getIndex(uuid);
        if (index >= 0) {
            for (int j = index; j < size - 1; j++) {
                storage[j] = storage[j + 1];
            }
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
