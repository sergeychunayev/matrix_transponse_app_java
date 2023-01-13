package com.example.transpose.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TransposeControllerTest {

    @Autowired
    private TransposeController transposeController;

    @Test
    void transpose() {
        final ResponseEntity<Object> result = transposeController.transpose(new int[][]{{1, 2, 3}});
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void transposeBad() {
        final ResponseEntity<Object> result = transposeController.transpose(new int[][]{{1, 2, 3}, {1}});
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Matrix must have same number of columns for all rows. Offending index: 1", result.getBody());
    }

    @Test
    void transposeBadHasEmptyColNull() {
        final ResponseEntity<Object> result = transposeController.transpose(new int[][]{{1, 2, 3}, null});
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Columns must not be empty. Offending index: 1", result.getBody());
    }

    @Test
    void transposeBadHasEmptyCol() {
        final ResponseEntity<Object> result = transposeController.transpose(new int[][]{{1, 2, 3}, {}});
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Columns must not be empty. Offending index: 1", result.getBody());
    }
}