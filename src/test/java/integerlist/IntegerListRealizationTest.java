package integerlist;

import exception.IntegerListElementNotFoundException;
import exception.IntegerListIndexOutOfBoundsException;
import exception.IntegerListNullPointerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListRealizationTest {

    private IntegerList list;

    @BeforeEach
    public void setUp() {
        list = new IntegerListRealization(5);
        list.add(111);
        list.add(222);
        list.add(333);
    }

    @Test
    void shouldAddElementCorrectly() {
        // ожидаем исключение при добавлении null
        assertThrows(IntegerListNullPointerException.class,
                () -> list.add(null));

        Integer expected1 = 11;
        Integer expected2 = 22;
        list.add(expected1);
        assertEquals(expected1, list.get(3));
        assertEquals(expected2, list.add(expected2));
    }

    @Test
    void shouldAddElementByIndexCorrectly() {
        // ожидаем исключение при добавлении null и по отрицательному индексу
        assertThrows(IntegerListNullPointerException.class,
                () -> list.add(0, null));
        assertThrows(IntegerListIndexOutOfBoundsException.class,
                () -> list.add(-1, 66));

        Integer expected1 = 11;
        Integer expected2 = 22;
        int expectedListSize = list.size();
        list.add(0, expected1);
        // после успешного добавления одного элемента size должен увеличиться на 1
        assertEquals(expectedListSize + 1, list.size());
        assertEquals(expected1, list.get(0));
        assertEquals(expected2, list.add(2, expected2));

        // так как equals от Object не переопределён, сравниваем лист в цикле с ожидаемым после проделанных тестов
        IntegerList expectedAfterAddTest = new IntegerListRealization(5);
        expectedAfterAddTest.add(11);
        expectedAfterAddTest.add(111);
        expectedAfterAddTest.add(22);
        expectedAfterAddTest.add(222);
        expectedAfterAddTest.add(333);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(expectedAfterAddTest.get(i), list.get(i));
        }
    }

    @Test
    void shouldSetElementByIndexCorrectly() {
        // ожидаем исключение при изменении на null, по отрицательному индексу и индексу >= size
        assertThrows(IntegerListNullPointerException.class,
                () -> list.set(0, null));
        assertThrows(IntegerListIndexOutOfBoundsException.class,
                () -> list.set(-1, 66));
        assertThrows(IntegerListIndexOutOfBoundsException.class,
                () -> list.set(list.size(), 66));
        assertThrows(IntegerListIndexOutOfBoundsException.class,
                () -> list.set(list.size() + 1, 66));

        Integer expected1 = 11;
        Integer expected2 = 22;
        int expectedSize = list.size();
        list.set(0, expected1);
        assertEquals(expected1, list.get(0));
        assertEquals(expected2, list.set(2, expected2));
        // после успешного изменения элемента size должен остаться прежним
        assertEquals(expectedSize, list.size());
    }

    @Test
    void shouldRemoveElementByStringValueCorrectly() {
        // ожидаем исключение при удалении null и элемента, которого нет в списке
        assertThrows(IntegerListNullPointerException.class,
                () -> list.remove(null));
        assertThrows(IntegerListElementNotFoundException.class,
                () -> list.remove(Integer.valueOf(66)));

        Integer expected = 222;
        int expectedSize = list.size();
        assertEquals(expected, list.remove(expected));
        // после успешного удаление элемента size должен уменьшиться на 1
        assertEquals(expectedSize - 1, list.size());
    }

    @Test
    void shouldRemoveElementByIndexCorrectly() {
        // ожидаем исключение при удалении элемента с индексом меньше 0 или равным и больше size
        assertThrows(IntegerListIndexOutOfBoundsException.class,
                () -> list.remove(-1));
        assertThrows(IntegerListIndexOutOfBoundsException.class,
                () -> list.remove(list.size()));
        assertThrows(IntegerListIndexOutOfBoundsException.class,
                () -> list.remove(list.size() + 1));

        Integer expected = list.get(1);
        int expectedSize = list.size();
        assertEquals(expected, list.remove(1));
        // после успешного удаления элемента size должен уменьшиться на 1
        assertEquals(expectedSize - 1, list.size());
    }

    @Test
    void shouldCheckContainsElementCorrectly() {
        // ожидаем исключение при передаче null
        assertThrows(IntegerListNullPointerException.class,
                () -> list.contains(null));

        assertTrue(list.contains(list.get(0)));
        assertFalse(list.contains(66));
    }

    @Test
    void shouldReturnIndexOfElementCorrectly() {
        // ожидаем исключение при передаче null
        assertThrows(IntegerListNullPointerException.class,
                () -> list.indexOf(null));

        int expectedIndex1 = 1;
        int expectedIndex2 = 2;
        int expectedFailIndex = -1;
        assertEquals(expectedIndex1, list.indexOf(list.get(expectedIndex1)));
        assertEquals(expectedIndex2, list.indexOf(333));
        assertEquals(expectedFailIndex, list.indexOf(66));
    }

    @Test
    void shouldReturnLastIndexOfElementCorrectly() {
        // ожидаем исключение при передаче null
        assertThrows(IntegerListNullPointerException.class,
                () -> list.lastIndexOf(null));

        int expectedFailIndex = -1;
        // добавим в список повторяющиеся элементы для теста
        list.add(0, list.get(0));
        list.add(4, list.get(2));
        assertEquals(1, list.lastIndexOf(111));
        assertEquals(4, list.lastIndexOf(222));
        assertEquals(expectedFailIndex, list.lastIndexOf(66));
    }

    @Test
    void shouldGetElementCorrectly() {
        // ожидаем исключение, если индекс меньше 0 или >= size
        assertThrows(IntegerListIndexOutOfBoundsException.class,
                () -> list.get(-1));
        assertThrows(IntegerListIndexOutOfBoundsException.class,
                () -> list.get(list.size()));
        assertThrows(IntegerListIndexOutOfBoundsException.class,
                () -> list.get(list.size() + 1));

        Integer expected1 = 111;
        Integer expected2 = 222;
        assertEquals(expected1, list.get(0));
        assertEquals(expected2, list.get(list.indexOf(expected2)));
    }

    @Test
    void shouldDoEqualsListsCorrectly() {
        // ожидаем исключение при передаче null
        assertThrows(IntegerListNullPointerException.class,
                () -> list.equals(null));

        IntegerList anotherList = new IntegerListRealization(list.size());
        for (int i = 0; i < list.size(); i++) {
            anotherList.add(list.get(i));
        }
        assertTrue(list.equals(anotherList));
        anotherList.remove(0);
        assertFalse(list.equals(anotherList));
    }

    @Test
    void shouldGetSizeCorrectly() {
        int expectedSize = 3;
        assertEquals(expectedSize, list.size());
        list.add(77);
        assertNotEquals(expectedSize, list.size());
    }

    @Test
    void shouldReturnIsEmptyCorrectly() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        list = new IntegerListRealization(1);
        assertTrue(list.isEmpty());

    }

    @Test
    void shouldClearCorrectly() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void ShouldMapToArrayCorrectly() {
        Integer[] excepted = new Integer[list.size()];
        for (int i = 0; i < excepted.length; i++) {
            excepted[i] = list.get(i);
        }
        assertArrayEquals(excepted, list.toArray());
        list.set(2, 77);
        assertFalse(Arrays.equals(excepted, list.toArray()));
    }
}