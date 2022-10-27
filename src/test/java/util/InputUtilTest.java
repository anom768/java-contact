package util;

import org.junit.jupiter.api.Test;

public class InputUtilTest {
    
    @Test
    void testInput() {
        String name = InputUtil.input("masukkan nama");
        System.out.println(name);
    }

}
