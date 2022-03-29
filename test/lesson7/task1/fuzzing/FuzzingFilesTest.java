package lesson7.task1.fuzzing;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static lesson7.task1.FilesKt.countSubstrings;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@RunWith(JQF.class)
@Slf4j
public class FuzzingFilesTest {

    @Fuzz
    @DisplayName("попытка посчитать вхождения в файл. Количество входных строк равно выходным")
    public void countSubstringsTest_randomListEqualSize_Success(List<String> list) {
        Map<String, Integer> actual = countSubstrings("input/chaotic_in1.txt", list);
        assertEquals(new HashSet<>(list).size(), actual.size());
    }

    @Fuzz
    @DisplayName("попытка посчитать вхождения в файл. Входные строки равны выходным")
    public void countSubstringsTest_randomListEqualKeys_Success(List<String> list) {
        boolean equal = true;
        Map<String, Integer> actual = countSubstrings("input/chaotic_in1.txt", list);
        for (Map.Entry<String, Integer> entry : actual.entrySet()) {
            if (!list.contains(entry.getKey())) {
                equal = false;
                break;
            }
        }
        assertTrue(equal);
    }

    @Fuzz
    @DisplayName("попытка посчитать вхождения в файл. Файл не существует")
    public void countSubstringsTest_FileNotFound_Success(String nameFile, List<String> list) {
        try {
            assumeTrue(!nameFile.endsWith(".txt"));
            countSubstrings(nameFile, list);
            fail();
        }
        catch (Exception ignored) {
        }
    }

    @Fuzz
    @DisplayName("попытка посчитать вхождения в файл. Пустой файл")
    public void countSubstringsTest_EmptyFile_Success(List<String> list) {
        boolean equal = true;
        Map<String, Integer> actual = countSubstrings("input/chaotic_in1.txt", list);
        for (Map.Entry<String, Integer> entry : actual.entrySet()) {
            if (entry.getValue() > 0) {
                equal = false;
                break;
            }
        }
        assertTrue(equal);
    }
}
