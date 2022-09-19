package com.av;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class SpringDataApplicationTest {

    @Test
    void main() {
        Assertions.assertTrue(true);
    }
}