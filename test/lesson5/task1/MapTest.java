package lesson5.task1;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static lesson5.task1.MapKt.subtractOf;
import static org.junit.Assert.assertEquals;

public class MapTest {


    @Test
    @DisplayName("Попытка удалить из одной мапы все элементы другой. Мапы равны по элементам. Успешно")
    public void subtractOf_similarMaps_Success() {
        Map<String, String> mutableMap = new HashMap<>();
        mutableMap.put(null, null);
        subtractOf(mutableMap, mutableMap);
        assertEquals(0, mutableMap.size());
    }

    @Test
    @DisplayName("Попытка удалить из одной мапы все элементы другой. Мапы пусты. Успешно")
    public void subtractOf_emptyMaps_Success() {
        Map<String, String> mutableMap = new HashMap<>();
        subtractOf(mutableMap, mutableMap);
        assertEquals(0, mutableMap.size());
    }

    @Test
    @DisplayName("Попытка удалить из одной мапы все элементы другой. Мапа удаление пуста. Успешно")
    public void subtractOf_fullMutableMapAndEmptySecondMap_Success() {
        Map<String, String> mutableMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            mutableMap.put(String.valueOf(i), String.valueOf(i));
        }
        Map<String, String> emptyMap = new HashMap<>();
        subtractOf(mutableMap, emptyMap);
        assertEquals(10, mutableMap.size());
    }

    @Test
    @DisplayName("Попытка удалить из одной мапы все элементы другой. Мапа удаления состоит из четных элементов. Успешно")
    public void subtractOf_fullMutableMapAndHalfSecondMap_Success() {
        Map<String, String> mutableMap = new HashMap<>();
        Map<String, String> secondMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            mutableMap.put(String.valueOf(i), String.valueOf(i));
            if (i % 2 == 0) {
                secondMap.put(String.valueOf(i), String.valueOf(i));
            }
        }
        subtractOf(mutableMap, secondMap);
        assertEquals(5, mutableMap.size());
    }

    @Test
    @DisplayName("Попытка удалить из одной мапы все элементы другой. Мапа удаления состоит из всех элементов. Успешно")
    public void subtractOf_HalfMutableMapAndFullSecondMap_Success() {
        Map<String, String> mutableMap = new HashMap<>();
        Map<String, String> secondMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            secondMap.put(String.valueOf(i), String.valueOf(i));
            if (i % 2 == 0) {
                mutableMap.put(String.valueOf(i), String.valueOf(i));
            }
        }
        subtractOf(mutableMap, secondMap);
        assertEquals(0, mutableMap.size());
    }

    @Test
    @DisplayName("Попытка удалить из одной мапы все элементы другой. Мапа удаления состоит из случайных элементов. Успешно")
    public void subtractOf_RandomMutableMapAndRandomSecondMapButDifferent_Success() {
        Random random = new Random();
        Map<String, String> mutableMap = new HashMap<>();
        Map<String, String> secondMap = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            secondMap.put(String.valueOf(random.nextInt(1000) + 1000), String.valueOf(random.nextInt(1000) + 1000));
            mutableMap.put(String.valueOf(random.nextInt(1000)), String.valueOf(random.nextInt(1000)));
        }

        int expected = mutableMap.size();
        subtractOf(mutableMap, secondMap);
        assertEquals(expected, mutableMap.size());
    }

    @Test
    @DisplayName("Попытка удалить из одной мапы все элементы другой. " +
            "Мапа удаления состоит из случайных элементов, ключи равны, значения нет. Успешно")
    public void subtractOf_RandomMutableMapAndRandomSecondMapButDifferentValues_Success() {
        Random random = new Random();
        Map<String, String> mutableMap = new HashMap<>();
        Map<String, String> secondMap = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            String string = String.valueOf(random.nextInt(1000) + 1000);
            secondMap.put(string, string + "1");
            mutableMap.put(string, string);
        }

        int expected = mutableMap.size();
        subtractOf(mutableMap, secondMap);
        assertEquals(expected, mutableMap.size());
    }


}
