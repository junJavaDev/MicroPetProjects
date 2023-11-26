package config;

public class Default {

    private Default() {

    }

    private static final int[][] I_BODY = {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
    };
    private static final int[][] J_BODY = {
            {1, 1, 1},
            {0, 0, 1},
            {0, 0, 0},
    };
    private static final int[][] L_BODY = {
            {1, 1, 1},
            {1, 0, 0},
            {0, 0, 0},
    };
    private static final int[][] O_BODY = {
            {1, 1},
            {1, 1},
    };
    private static final int[][] S_BODY = {
            {0, 1, 1},
            {1, 1, 0},
            {0, 0, 0},
    };
    private static final int[][] T_BODY = {
            {1, 1, 1},
            {0, 1, 0},
            {0, 0, 0},
    };
    private static final int[][] Z_BODY = {
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0},
    };
}
