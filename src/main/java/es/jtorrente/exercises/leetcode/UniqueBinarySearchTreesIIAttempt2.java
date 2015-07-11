package es.jtorrente.exercises.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jtorrente on 09/07/2015.
 */
public class UniqueBinarySearchTreesIIAttempt2 {

    /**
     * Definition for a binary tree node.*/
     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }


        public List<TreeNode> generateTrees(int n) {
            List<TreeNode>[] prevLists = new List[n+1];
            List<TreeNode> list = generateTrees(n, prevLists);
            recodeTrees(list, n);
            return list;
        }

        private void recodeTrees(List<TreeNode> list, int n) {

            for (int i=0; i<list.size(); i++) {
                list.add(i, recodeTree(list.remove(i), n, 1));
            }
        }

        private TreeNode recodeTree(TreeNode tree, int max, int min) {
            if (tree==null) {
                return null;
            }
            TreeNode recodedTree = duplicateTree(tree);
            if (tree.left == null && tree.right != null) {
                recodedTree.val = min;
                recodedTree.right = recodeTree(tree.right, max, min+1);
            }
            else if (tree.left != null && tree.right == null) {
                recodedTree.val = max;
                recodedTree.left = recodeTree(tree.left, max-1, min);
            } else if (tree.left != null && tree.right != null) {
                int median = (min+max)/2;
                recodedTree.val = median;
                recodedTree.left = recodeTree(tree.left, median-1, min);
                recodedTree.right = recodeTree(tree.right, max, median+1);
            } else {
                recodedTree.val = min;
            }
            return recodedTree;
        }

        private TreeNode duplicateTree(TreeNode tree) {
            if (tree==null) {
                return null;
            }

            TreeNode newTree = new TreeNode(tree.val);
            newTree.left = duplicateTree(tree.left);
            newTree.right = duplicateTree(tree.right);
            return newTree;
        }

        public List<TreeNode> generateTrees(int n, List<TreeNode>[] prevLists) {
            if (prevLists[n]!=null) {
                return prevLists[n];
            }

            List<TreeNode> list = new ArrayList<>();
            if (n==0 || n==1) {
                list.add(n==0 ? null : new TreeNode(1));
                prevLists[n] = list;
                return list;
            }

            // A binary search tree of n-1 nodes can be transformed into an n-node binary tree by adding one node
            List<TreeNode> smallerTrees = generateTrees(n-1);
            for (TreeNode smallTree: smallerTrees) {
                // newNode -> smallTree, null (smallTree added as left child)
                TreeNode newNode1 = new TreeNode(smallTree.val+1);
                newNode1.left = smallTree;
                // newNode -> null, smallTree (smallTree added as right child)
                TreeNode newNode2 = new TreeNode(smallTree.val-1);
                newNode2.right = smallTree;
                list.add(newNode1);
                list.add(newNode2);
            }

            // Trees formed by combining two smaller trees: tree(n)=1+tree((n-1)/2) + tree((n-1)/2)
            for (int i=1; i<n-1; i++) {
                int j=n-1-i;
                List<TreeNode> iTrees = generateTrees(i);
                List<TreeNode> jTrees = generateTrees(j);
                for (TreeNode iTree : iTrees) {
                    for (TreeNode jTree : jTrees) {
                        TreeNode newNode = new TreeNode((iTree.val+jTree.val)/2);
                        if (iTree.val < jTree.val){
                            newNode.left = iTree;
                            newNode.right = jTree;
                        } else {
                            newNode.left = jTree;
                            newNode.right = iTree;
                        }
                        list.add(newNode);
                    }
                }
            }

            prevLists[n] = list;
            return list;
        }
}
