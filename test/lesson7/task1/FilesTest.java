package lesson7.task1;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static lesson7.task1.FilesKt.countSubstrings;
import static org.junit.Assert.assertEquals;

public class FilesTest {

    private static final String VERY_BIG_STRING = "/**\n" +
            " * Средняя\n" +
            " *\n" +
            " * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.\n" +
            " * Выбрать из данного словаря наиболее длинное слово,\n" +
            " * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.\n" +
            " * Вывести его в выходной файл с именем outputName.\n" +
            " * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,\n" +
            " * в выходной файл следует вывести их все через запятую.\n" +
            " * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.\n" +
            " *\n" +
            " * Пример входного файла:\n" +
            " * Карминовый\n" +
            " * Боязливый\n" +
            " * Некрасивый\n" +
            " * Остроумный\n" +
            " * БелогЛазый\n" +
            " * ФиолетОвый\n" +
            "\n" +
            " * Соответствующий выходной файл:\n" +
            " * Карминовый, Некрасивый\n" +
            " *\n" +
            " * Обратите внимание: данная функция не имеет возвращаемого значения\n" +
            " */";

    @Test(expected = StringIndexOutOfBoundsException.class)
    @DisplayName("попытка посчитать вхождения в файл. Пустая строка. Кидает исключение")
    public void countSubstringsTest_emptyString_ThrowException() {
        List<String> list = new ArrayList<>();
        list.add("");
        countSubstrings("input/substrings_in1.txt", list);
    }

    @Test
    @DisplayName("попытка посчитать вхождения в файл. Длинная строка. Успешно")
    public void countSubstringsTest_bigString_Success() {
        List<String> list = new ArrayList<>();
        list.add(VERY_BIG_STRING);
        countSubstrings("input/substrings_in1.txt", list);
        Map<String, Integer> actual = countSubstrings("input/substrings_in1.txt", list);
        assertEquals(1, actual.size());
        assertEquals(0, actual.get(VERY_BIG_STRING).intValue());
    }

    @Test(expected = FileNotFoundException.class)
    @DisplayName("попытка посчитать вхождения в файл. Неверное имя файла. Кидает исключение")
    public void countSubstringsTest_notGoodFileName_ThrowException() {
        List<String> list = new ArrayList<>();
        list.add("");
        countSubstrings("input/file_that_not_exists.txt", list);
    }

    @Test
    @DisplayName("попытка посчитать вхождения в файл. Строка с пробелом, но в файле нет пробелов. Успешно")
    public void countSubstringsTest_spaceStringAndTextWithoutSpaces_Success() {
        List<String> list = new ArrayList<>();
        list.add(" ");
        Map<String, Integer> actual = countSubstrings("input/empty.txt", list);
        assertEquals(1, actual.size());
        assertEquals(0, actual.get(" ").intValue());
    }

    @Test
    @DisplayName("попытка посчитать вхождения в файл. Строка с пробелом, в файле есть пробелы. Кидает исключение")
    public void countSubstringsTest_spaceStringAndTextWithSpaces_Success() {
        List<String> list = new ArrayList<>();
        list.add(" ");
        Map<String, Integer> actual = countSubstrings("input/expr_in1.txt", list);
        assertEquals(1, actual.size());
        assertEquals(8, actual.get(" ").intValue());
    }

    @Test
    @DisplayName("попытка посчитать вхождения в файл. Примитивная строка. Успешно")
    public void countSubstringsTest_oneSimpleString_Success() {
        List<String> list = new ArrayList<>();
        list.add("x");
        Map<String, Integer> actual = countSubstrings("input/expr_in1.txt", list);
        assertEquals(1, actual.size());
        assertEquals(4, actual.get("x").intValue());
    }

    @Test
    @DisplayName("попытка посчитать вхождения в файл. Несколько простых строк. Успешно")
    public void countSubstringsTest_twoStrings_Success() {
        List<String> list = new ArrayList<>();
        list.add("x");
        list.add(" ");
        Map<String, Integer> actual = countSubstrings("input/expr_in1.txt", list);
        assertEquals(2, actual.size());
        assertEquals(4, actual.get("x").intValue());
        assertEquals(8, actual.get(" ").intValue());
    }

    @Test
    @DisplayName("попытка посчитать вхождения в файл. Поиск символов переноса строки. Успешно")
    public void countSubstringsTest_slashNFind_Success() {
        List<String> list = new ArrayList<>();
        list.add("\n");
        Map<String, Integer> actual = countSubstrings("input/chaotic_in1.txt", list);
        assertEquals(1, actual.size());
        assertEquals(0, actual.get("\n").intValue());
    }

    @Test
    @DisplayName("попытка посчитать вхождения в файл. Строка с символом 'Л', проверка работы в разных регистрах. Успешно")
    public void countSubstringsTest_anyCase_Success() {
        List<String> list = new ArrayList<>();
        list.add("л");
        Map<String, Integer> actual = countSubstrings("input/chaotic_in1.txt", list);
        assertEquals(1, actual.size());
        assertEquals(4, actual.get("л").intValue());
    }
}
