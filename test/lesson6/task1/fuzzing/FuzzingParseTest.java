package lesson6.task1.fuzzing;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lesson6.task1.ParseKt.bestLongJump;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

@RunWith(JQF.class)
@Slf4j
public class FuzzingParseTest {

    private static final String REGEX = "([0-9]+|-|%)( ([0-9]+|-|%))*";

    @Fuzz
    @DisplayName("Неверная строка")
    public void bestLongJump_notCorrectValueString_Success(String string) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(string);
        assumeTrue(!matcher.find());

        int actual = bestLongJump(string);
        assertEquals(-1, actual);
    }

    @Fuzz
    @DisplayName("Верные данные")
    public void bestLongJump_correctValueString_Success(int first, int second, int third) {
        String stringBuilder = first + " " + second + " " + third;
        int actual = bestLongJump(stringBuilder);
        assertEquals(Math.max(Math.max(Math.max(first, second), third), 0), actual);
    }

    @Fuzz
    @DisplayName("Символы, не являющиеся цифрами")
    public void bestLongJump_notNumbers_Success(char first, char second, char third) {
        boolean assume = true;
        String stringBuilder = first + " " + second + " " + third;
        System.out.println(stringBuilder);
        for (int i = 0; i < stringBuilder.length(); i++) {
            char character = stringBuilder.charAt(i);
            if ((character >= '0' && character <= '9')) {
                assume = false;
                break;
            }
        }
        assumeTrue(assume);
        System.out.println(1);
        int actual = bestLongJump(stringBuilder);
        assertEquals(-1, actual);
    }

    @Fuzz
    @DisplayName("Символы, не являющиеся цифрами и число в конце")
    public void bestLongJump_notNumbersCharsAndIntInTheEnd_Success(char first, char second, int third) {
        String stringBuilder = first + " " + second + " " + third;

        int actual = bestLongJump(stringBuilder);
        assertEquals(-1, actual);
    }

    @Fuzz
    @DisplayName("число в конце и два символы из допустимых")
    public void bestLongJump_IntInTheEndAndGoodChars_Success(int third) {
        third = Math.abs(third);
        String stringBuilder = "- % " + third;
        int actual = bestLongJump(stringBuilder);
        assertEquals(third, actual);
    }

    @Fuzz
    @DisplayName("число в конце и два символы из допустимых, число отрицательное")
    public void bestLongJump_negativeInt_Success(int third) {
        third = Math.abs(third) * -1;
        String stringBuilder = "- % " + third;
        int actual = bestLongJump(stringBuilder);
        assertEquals(0, actual);
    }

}
