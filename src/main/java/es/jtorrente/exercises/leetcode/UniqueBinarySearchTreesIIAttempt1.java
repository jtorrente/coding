package es.jtorrente.exercises.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jtorrente on 09/07/2015.
 */
public class UniqueBinarySearchTreesIIAttempt1 {

    public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

     public List<TreeNode> generateTrees(int n) {
        List<TreeNode> list = new ArrayList<>();
        if (n==0){
            list.add(null);
            return list;
        }
        boolean[] available = new boolean[n];
        for (int i=0; i<n; i++) {
            available[i] = true;
        }

        for (int i=0; i<n; i++) {
            TreeNode root = new TreeNode(i+1);
            available[i] = false;
            generateTrees(available, n-1, root, root, list);
            available[i] = true;
        }
        return list;
    }

    private void generateTrees(boolean[] available, int availableCount, TreeNode currentTree, TreeNode pointer, List<TreeNode> trees) {
        if (availableCount==0) {
            trees.add(duplicate(currentTree));
            return;
        }

        for (int i=0; i<available.length; i++) {
            if (!available[i]) {
                continue;
            }
            TreeNode newNode = new TreeNode(i+1);
            if (i+1<pointer.val) {
                pointer.left = newNode;
            } else {
                pointer.right = newNode;
            }
            available[i] = false;
            generateTrees(available, availableCount-1, currentTree, newNode, trees);
            if (i+1<pointer.val) {
                pointer.left = null;
            } else {
                pointer.right = null;
            }
            available[i] = true;
        }

        for (int i=0; i<pointer.val-1; i++) {
            if (!available[i]) {
                continue;
            }
            for (int j=pointer.val; j<available.length; j++){
                if (!available[j]) {
                    continue;
                }
                TreeNode newNode1 = new TreeNode(i+1);
                TreeNode newNode2 = new TreeNode(j+1);
                pointer.left = newNode1;
                pointer.right = newNode2;
                available[i] = false;
                available[j] = false;
                generateTrees(available, availableCount-2, currentTree, newNode1, trees);
                if (availableCount>2){
                    generateTrees(available, availableCount-2, currentTree, newNode2, trees);
                }
                pointer.left = null;
                pointer.right = null;
                available[i] = true;
                available[j] = false;
            }
        }
    }

    private TreeNode duplicate(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(node.val);
        newRoot.left = duplicate(node.left);
        newRoot.right = duplicate(node.right);
        return newRoot;
    }
}
