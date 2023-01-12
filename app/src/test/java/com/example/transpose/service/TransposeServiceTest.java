package com.example.transpose.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransposeServiceTest {

    private static final int[][] EMPTY_MATRIX = {};

    private final TransposeService transposeService = new TransposeService();

    @Test
    void transpose() {
        final int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        final int[][] result = transposeService.transpose(matrix);
        assertArrayEquals(new int[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}}, result);
    }

    @Test
    void transposeRow() {
        final int[][] matrix = {{1, 2, 3, 4, 5, 6, 7, 8, 9}};
        final int[][] result = transposeService.transpose(matrix);
        assertArrayEquals(new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}}, result);
    }

    @Test
    void transposeCol() {
        final int[][] matrix = {{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}};
        final int[][] result = transposeService.transpose(matrix);
        assertArrayEquals(new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9}}, result);
    }

    @Test
    void transposeEmpty() {
        assertArrayEquals(EMPTY_MATRIX, transposeService.transpose(EMPTY_MATRIX));
    }
}