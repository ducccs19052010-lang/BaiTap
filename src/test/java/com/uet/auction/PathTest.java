package com.uet.auction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import org.junit.jupiter.api.Test;

public class PathTest {
    @Test
    void shouldUseWindowsSeparator() {
        assertEquals("\\", File.separator);
    }
}