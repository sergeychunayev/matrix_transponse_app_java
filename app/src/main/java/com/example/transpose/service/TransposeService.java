package com.example.transpose.service;

import org.springframework.stereotype.Service;

@Service
public class TransposeService {
    public int[][] transpose(final int[][] matrix) {
        if (matrix.length == 0) {
            return matrix;
        }

        final int m = matrix.length;
        final int n = matrix[0].length;
        final int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                final int temp = matrix[j][i];
                result[i][j] = temp;
            }
        }
        return result;
    }
}
