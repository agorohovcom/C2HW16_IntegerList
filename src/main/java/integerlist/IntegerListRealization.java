package integerlist;

import exception.IntegerListElementNotFoundException;
import exception.IntegerListIndexOutOfBoundsException;
import exception.IntegerListNullPointerException;
import exception.InvalidIntegerListInitialCapacityException;

import java.util.Arrays;

public class IntegerListRealization implements IntegerList {

    private Integer[] elementData;
    private int size;
    private final double LOAD_FACTOR = 0.75d;
    private final int CAPACITY_DECOMPRESS_VALUE = 10;
    private final int DEFAULT_CAPACITY_VALUE = 10;

    public IntegerListRealization() {
        elementData = new Integer[DEFAULT_CAPACITY_VALUE];
        size = 0;
    }

    public IntegerListRealization(int initialCapacity) {
        if (initialCapacity >= 0) {
            elementData = new Integer[initialCapacity];
            size = 0;
        } else throw new InvalidIntegerListInitialCapacityException();
    }

    @Override
    public Integer add(Integer item) {
        notNullParamCheckPlease(item);
        decompressElementDataCapacityPlease();
        elementData[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        notNullParamCheckPlease(item);
        decompressElementDataCapacityPlease();
        if (index < 0) {
            throw new IntegerListIndexOutOfBoundsException();
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        notNullParamCheckPlease(item);
        if (index >= size || index < 0) {
            throw new IntegerListIndexOutOfBoundsException();
        }
        elementData[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        notNullParamCheckPlease(item);
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(item)) {
                for (int j = i; j < size - 1; j++) {
                    elementData[j] = elementData[j + 1];
                }
                elementData[--size] = null;
                return item;
            }
        }
        throw new IntegerListElementNotFoundException();
    }

    @Override
    public Integer remove(int index) {
        if (index >= size || index < 0) {
            throw new IntegerListIndexOutOfBoundsException();
        }
        trimToSizePlus10Please();
        Integer result = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[--size] = null;
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        notNullParamCheckPlease(item);
        return Arrays.stream(elementData).limit(size).anyMatch(s -> s.equals(item));
    }

    @Override
    public int indexOf(Integer item) {
        notNullParamCheckPlease(item);
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        notNullParamCheckPlease(item);
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index >= size || index < 0) {
            throw new IntegerListIndexOutOfBoundsException();
        }
        return elementData[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        notNullParamCheckPlease(otherList);
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!elementData[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    @Override
    public void clear() {
        elementData = new Integer[DEFAULT_CAPACITY_VALUE];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] result = new Integer[size];
        System.arraycopy(elementData, 0, result, 0, size);
        return result;
    }

    private void notNullParamCheckPlease(Object param) {
        if (param == null) {
            throw new IntegerListNullPointerException();
        }
    }

    private void decompressElementDataCapacityPlease() {
        if ((size + 1) > (elementData.length * LOAD_FACTOR)) {
            elementData = Arrays.copyOf(elementData, elementData.length + CAPACITY_DECOMPRESS_VALUE);
        }
    }

    private void trimToSizePlus10Please() {
        if ((size * 2) < elementData.length) {
            elementData = Arrays.copyOf(elementData, size + DEFAULT_CAPACITY_VALUE);
        }
    }

    private void selectionSort() {
        Integer[] array = toArray();
        for (int i = 0; i < size - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < size; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            int tmp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = tmp;
        }
        elementData = array;
    }

    private boolean binarySearch() {

    }
}
