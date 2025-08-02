import java.util.*;

class Sum_of_Nodes_in_Kth_Level {

    static int kLevelSumRecur(int[] idx, String s, int k, int level) {
        if (s.charAt(idx[0]) == '(') idx[0]++;

        // Find the value of current node.
        int currNode = 0;
        while (idx[0] < s.length() && s.charAt(idx[0]) != '(' && s.charAt(idx[0]) != ')') {
            currNode = currNode * 10 + (s.charAt(idx[0]) - '0');
            idx[0]++;
        }

        // Append the value of current node only if it is at level k.
        currNode = (level == k) ? currNode : 0;

        int left = 0, right = 0;

        // If left subtree exists
        if (idx[0] < s.length() && s.charAt(idx[0]) == '(') {
            left = kLevelSumRecur(idx, s, k, level + 1);
        }

        // if right subtree exists
        if (idx[0] < s.length() && s.charAt(idx[0]) == '(') {
            right = kLevelSumRecur(idx, s, k, level + 1);
        }

        idx[0]++; // To take care of closing parenthesis

        return currNode + left + right;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String s = sc.next();
        System.out.println(kLevelSumRecur(new int[1], s, k, 0));
        sc.close();
    }
}