package com.uet.auction;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Tests platform-independent file paths.
 */
public class PathTest {
    @Test
    void shouldBuildPathUsingPathApi() {
        Path path = Path.of("data", "input.txt");

        String actualPath = path.toString();

        assertTrue(actualPath.contains("data"));
        assertTrue(actualPath.contains("input.txt"));
    }
}