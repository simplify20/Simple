package com.simple.creact.simple;

/**
 * @author:YJJ
 * @date:2016/3/21
 * @email:yangjianjun@117go.com
 */
public class MatrixGenerator {

    private static final int COLUMNS = 6;
    private static final int ROWS = 15;

    public int[][] generateMatrix(int[][] input) {

        int[][] output = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {

            for (int j = 0; j < COLUMNS; j++) {
                //init O1,O2
                if (i < 6) {
                    output[i][j] = 0;
                } else if (i < 9) {
                    //init C
                    if (j < 3)
                        output[i][j] = input[i - 6][j];
                    else
                        // init O3
                        output[i][j] = 0;
                } else if (i < 12) {
                    //init O4,O5
                    output[i][j] = 0;
                } else if (i < ROWS) {
                    //init O6
                    if (j < 3) {
                        output[i][j] = 0;
                    } else {
                        //init I1
                        output[i][j] = 1;
                    }
                }
            }
        }
        return output;
    }
}
