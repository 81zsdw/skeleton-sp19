import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;


public class FlikTest {

    @Test(timeout = 1000) //milliseconds
    public void TestisSameNumber() {
        int a = 3;
        int b = 4;
        int c = 129;
        assertEquals(Flik.isSameNumber(a, a), true);
        assertEquals(Flik.isSameNumber(b, b), true);
        assertEquals(Flik.isSameNumber(c, c), true);
        assertEquals(Flik.isSameNumber(a, b), false);
    }
}
