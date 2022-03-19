package lesson6.task1;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static lesson6.task1.ParseKt.bestLongJump;
import static org.junit.Assert.assertEquals;

public class ParseTest {

    @Test
    @DisplayName("Попытка найти наибольшее значение. Пустая строка. Не корректно")
    public void bestLongJump_emptyString_NotCorrect() {
        int actual = bestLongJump("");
        assertEquals(-1, actual);
    }

    @Test
    @DisplayName("Попытка найти наибольшее значение. Неверный формат строки. Не корректно")
    public void bestLongJump_notCorrectString_NotCorrect() {
        int actual = bestLongJump("-- --");
        assertEquals(-1, actual);
    }

    @Test
    @DisplayName("Попытка найти наибольшее значение. Ни одного подходящего значения. Не корректно")
    public void bestLongJump_emptyDataString_NotCorrect() { //я считаю, это тоже не верно, должно быть 0.
        int actual = bestLongJump("- - - - - -");
        assertEquals(-1, actual);
    }

    @Test
    @DisplayName("Попытка найти наибольшее значение. Одно подходящее значение в строке. Успешно")
    public void bestLongJump_singletonDataString_Success() {
        int actual = bestLongJump("246 -");
        assertEquals(246, actual);
    }

    @Test
    @DisplayName("Попытка найти наибольшее значение. Отрицательное значение. Успешно")
    public void bestLongJump_notCorrectValueString_Success() {
        int actual = bestLongJump("-2 -"); //тут бы я ожидал не 0, а неправильный формат ввода и -1
        assertEquals(0, actual);
    }

    @Test
    @DisplayName("Попытка найти наибольшее значение. Созданная длинная строка. Успешно")
    public void bestLongJump_longCreatedString_Success() {
        int actual = bestLongJump("100 2 3 4 5 6 7 8 9 10");
        assertEquals(100, actual);
    }

    @Test
    @DisplayName("Попытка найти наибольшее значение. Строка из примера. Успешно")
    public void bestLongJump_exampleString_Success() {
        int actual = bestLongJump("706 - % 717 % 703");
        assertEquals(717, actual);
    }

    @Test
    @DisplayName("Попытка найти наибольшее значение. Слишком большое число. Не корректно")
    public void bestLongJump_outOfRangeInt_NotCorrect() { //мне кажется, это не должно так работать
        int actual = bestLongJump("70610101010100101010101010101010110 - % 717 % 703"); //я бы ожидал тут выбрасывания исключений
        assertEquals(-1, actual);
    }

    @Test
    @DisplayName("Попытка найти наибольшее значение. Сгенерированная строка. Успешно")
    public void bestLongJump_generatedLongString_Success() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                stringBuilder.append(i);
            }
            else {
                stringBuilder.append("-");
            }
            if (i != 99) {
                stringBuilder.append(" ");
            }
        }
        int actual = bestLongJump(stringBuilder.toString());
        assertEquals(98, actual);
    }

    @Test
    @DisplayName("Попытка найти наибольшее значение. Пробел в конце строки. Не корректно")
    public void bestLongJump_exampleStringWithSpaceInTheEnd_NotCorrect() {
        int actual = bestLongJump("706 - % 717 % 703 ");
        assertEquals(-1, actual);
    }

}
