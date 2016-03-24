package com.simple.creact.simple;

import org.junit.Before;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    private int[][] input;

    @Before
    public void setUp() throws Exception {

        input = new int[3][3];
        System.out.println("input:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                input[i][j] = i + j;
                if (j < 2)
                    System.out.print(input[i][j] + " ");
                else
                    System.out.println(input[i][j]);
            }
        }

    }

    @Test
    public void matrix_isCorrect() throws Exception {
        int[][] output = new MatrixGenerator().generateMatrix(input);
        System.out.println("output:");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 6; j++) {
                if (j < 5)
                    System.out.print(output[i][j] + " ");
                else
                    System.out.println(output[i][j]);
            }
        }
    }
}