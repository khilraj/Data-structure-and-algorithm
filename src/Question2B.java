package src;

public class Question2B {
        private static class TreeNode {
            int val;
            TreeNode left, right;

            TreeNode(int val) {
                this.val = val;
                this.left = null;
                this.right = null;
            }
        }

        public static int minServiceCenters(TreeNode root) {
            int[] result = helper(root);
            return result[0];
        }

        private static int[] helper(TreeNode node) {
            if (node == null) {
                return new int[]{0, 0};
            }

            int[] leftResult = helper(node.left);
            int[] rightResult = helper(node.right);

            int numCenters = leftResult[0] + rightResult[0];

            if (leftResult[1] == 1 || rightResult[1] == 1) {
                numCenters++;
                return new int[]{numCenters, 2};
            } else {
                return new int[]{numCenters, 1};
            }
        }

        public static void main(String[] args) {
            TreeNode root = new TreeNode(0);
            root.left = new TreeNode(0);
            root.right = new TreeNode(0);
            root.left.left = new TreeNode(0);
            root.right.left = new TreeNode(0);

            System.out.println(minServiceCenters(root));
        }
}
