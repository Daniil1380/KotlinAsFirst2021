package lesson5.task1.fuzzing;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static lesson5.task1.MapKt.subtractOf;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@RunWith(JQF.class)
@Slf4j
public class FuzzingMapTest {

    @Fuzz
    @DisplayName("Один общий элемент")
    public void subtractOf_similarElement_Success(Map<String, String> mutableMap, Map<String, String> secondMap, String string) {
        mutableMap.put(string, string);
        secondMap.put(string, string);
        int sizeBefore = mutableMap.size();
        subtractOf(mutableMap, secondMap);
        assertTrue(mutableMap.size() < sizeBefore);
    }

    @Fuzz
    @DisplayName("Все элементы общие")
    public void subtractOf_similarMap_Success(Map<String, String> mutableMap) {
        Map<String, String> secondMap = new HashMap<>();
        for (Map.Entry<String, String> entry : mutableMap.entrySet()) {
            secondMap.put(entry.getKey(), entry.getValue());
        }
        subtractOf(mutableMap, secondMap);
        assertEquals(0, mutableMap.size());
    }

    @Fuzz
    @DisplayName("Большие строки")
    public void subtractOf_bigStringMap_Success(Map<String, String> mutableMap, Map<String, String> secondMap) {
        boolean assume = false;

        for (Map.Entry<String, String> entry : mutableMap.entrySet()) {
            if (entry.getKey().length() > 50) {
                assume = true;
                break;
            }
        }
        assumeTrue(assume);
        subtractOf(mutableMap, secondMap);
    }

    @Fuzz
    @DisplayName("Пустые строки")
    public void subtractOf_emptyStringMap_Success(Map<String, String> mutableMap, Map<String, String> secondMap) {
        boolean assume = false;

        for (Map.Entry<String, String> entry : mutableMap.entrySet()) {
            if (entry.getKey().equals("")) {
                assume = true;
                break;
            }
        }
        assumeTrue(assume);
        subtractOf(mutableMap, secondMap);
    }


}
