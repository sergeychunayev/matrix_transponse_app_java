package com.example.transpose.controller;

import com.example.transpose.service.TransposeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TransposeController {

    private final TransposeService transposeService;

    public TransposeController(final TransposeService transposeService) {
        this.transposeService = transposeService;
    }

    @PostMapping("/transpose")
    public ResponseEntity<Object> transpose(@RequestBody final int[][] matrix) {
        return validate(matrix).orElseGet(() -> {
            final int[][] result = transposeService.transpose(matrix);
            return ResponseEntity.ok(result);
        });
    }

    private static Optional<ResponseEntity<Object>> validate(final int[][] matrix) {
        return validateColNotEmpty(matrix)
                .or(() -> validateColsSameSize(matrix));
    }

    private static Optional<ResponseEntity<Object>> validateColsSameSize(final int[][] matrix) {
        boolean sameSize = true;
        int i = 1;
        for (; i < matrix.length; i++) {
            if (matrix[i].length != matrix[i - 1].length) {
                sameSize = false;
                break;
            }
        }
        if (!sameSize) {
            return Optional.of(
                    ResponseEntity
                            .badRequest()
                            .body("Matrix must have same number of columns for all rows. Offending index: " + i)
            );
        }
        return Optional.empty();
    }

    private static Optional<ResponseEntity<Object>> validateColNotEmpty(final int[][] matrix) {
        boolean empty = false;
        int i = 0;
        for (; i < matrix.length; i++) {
            if (matrix[i] == null || matrix[i].length == 0) {
                empty = true;
                break;
            }
        }
        if (empty) {
            return Optional.of(
                    ResponseEntity
                            .badRequest()
                            .body("Columns must not be empty. Offending index: " + i)
            );
        }
        return Optional.empty();
    }
}
