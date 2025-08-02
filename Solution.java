public class Solution {
    public static int largestCombination(int[] candidates) {
        int[] setbits = new int[32];
        for (int i = 0; i < 32; i++) {
            int bits = 0;
            for (int j = 0; j < candidates.length; j++) {
                bits += ((candidates[j] & (1 << i)) != 0) ? 1 : 0;
            }
            setbits[i] = bits;
        }

        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = Math.max(res, setbits[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{16,17,71,62,12,24,14};
        System.out.println(largestCombination(candidates));
    }
}