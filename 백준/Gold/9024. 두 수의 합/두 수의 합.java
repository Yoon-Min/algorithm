import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        while (t-- > 0) {
            String[] nk = br.readLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);

            String[] arrStr = br.readLine().split(" ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(arrStr[i]);
            }
            Arrays.sort(arr);

            int resultDiff = Integer.MAX_VALUE;
            int resultCount = 0;

            for (int i = 0; i < n; i++) {
                int left = i + 1;
                int right = n - 1;
                int closerDiff = Integer.MAX_VALUE;

                while (left <= right) {
                    int mid = (left + right) / 2;
                    int sum = arr[i] + arr[mid];
                    closerDiff = Math.min(Math.abs(sum - k), closerDiff);

                    if (sum < k) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                if (closerDiff < resultDiff) {
                    resultDiff = closerDiff;
                    resultCount = 1;
                } else if (closerDiff == resultDiff) {
                    resultCount++;
                }
            }

            System.out.println(resultCount);
        }
    }
}