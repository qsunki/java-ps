public class TriangleMatrix {
    public static void main(String[] args) {
        int size = 3; // 행렬의 크기 설정

        int[][] matrix = new int[size][size];

        int value = 1;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col <= row; col++) {
                matrix[row - col][col] = value++;
            }
        }

        // 결과 출력
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
