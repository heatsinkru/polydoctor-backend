package org.polytech.covid;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Teest {
    public static int a;
    public static int b;
    public static int c;

    @BeforeAll
    public static void setup() {
        a = 1;
        b = 2;
        c = a + b;
    }

    @Test
    public void test1() {
        Assertions.assertThat(c).isEqualTo(4);
    }

    @Test
    public void test2() {
        Assertions.assertThat(c).isEqualTo(3);

    }
}
