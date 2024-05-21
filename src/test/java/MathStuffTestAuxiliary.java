import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test cases for auxiliary methods in {@code MathStuff}.
 * Martina Markova, 1942026, 18.05.2024
 */
public class MathStuffTestAuxiliary {

    @Test 
    public void test1() {
        int answer = MathStuff.binarySearch(4, 2);
        assertEquals(answer, 2);
    }

    @Test 
    public void test2() {
        int answer = MathStuff.binarySearch(5, 2);
        assertEquals(answer, -1);
    }

    @Test 
    public void test3() {
        int answer = MathStuff.binarySearch(1, 2);
        assertEquals(answer, 1);
    }

    @Test 
    public void test4() {
        int answer = MathStuff.binarySearch(994009, 2);
        assertEquals(answer, 997);
    }

    @Test 
    public void test5() {
        int answer = MathStuff.binarySearch(1048576, 20);
        assertEquals(answer, 2);
    }
}
