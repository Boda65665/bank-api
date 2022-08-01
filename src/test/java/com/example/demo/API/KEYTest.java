package com.example.demo.API;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KEYTest {

    @Test
    void generate() {
        KEY key = new KEY();
        key.generate();
    }
}