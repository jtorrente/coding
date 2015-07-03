package es.jtorrente.es.jtorrente.exercises.leetcode;

import es.jtorrente.exercises.leetcode.TreeNodeTraversal;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 03/07/2015.
 */
public class TestTreeNodeTraversal {

    private TreeNodeTraversal.TreeNode generateTree(int levels){
        if (levels<=1) {
            return null;
        }

        TreeNodeTraversal.TreeNode root = new TreeNodeTraversal.TreeNode(1);
        Queue<TreeNodeTraversal.TreeNode> list = new LinkedList<>();
        list.offer(root);
        int maxElem = (int) (Math.pow(2,levels-1)-1);
        for (int i=0; i<maxElem; i++){
            TreeNodeTraversal.TreeNode current = list.poll();
            TreeNodeTraversal.TreeNode left = new TreeNodeTraversal.TreeNode(current.val*2);
            TreeNodeTraversal.TreeNode right = new TreeNodeTraversal.TreeNode(current.val*2+1);
            current.left = left;
            current.right = right;
            list.offer(left);
            list.offer(right);
        }

        return root;
        // Parent i => children 2i, 2i+1
        /*
                        1
                  2           3
             4       5     6     7
            8 9    10  11 12 13 14 15
         */
    }

    @Test
    public void testPreOrder(){
        TreeNodeTraversal.TreeNode buildTree = generateTree(4);
        int[] expected = new int[]{1,2,4,8,9,5,10,11,3,6,12,13,7,14,15};
        List<Integer> results = new ArrayList<>();
        TreeNodeTraversal.doPreorderTraversalIteratively(results, buildTree);
        check(results, expected);
    }

    @Test
    public void testInOrder(){
        TreeNodeTraversal.TreeNode buildTree = generateTree(4);
        int[] expected = new int[]{8,4,9,2,10,5,11,1,12,6,13,3,14,7,15};
        List<Integer> results = new ArrayList<>();
        TreeNodeTraversal.doInorderTraversalIteratively(results, buildTree);
        check(results, expected);

        List<Integer> results2 = new ArrayList<>();
        TreeNodeTraversal.doInorderTraversal2(results2, buildTree);
        check(results2, expected);
    }

    @Test
    public void testPostOrder(){
        TreeNodeTraversal.TreeNode buildTree = generateTree(4);
        int[] expected = new int[]{8,9,4,10,11,5,2,12,13,6,14,15,7,3,1};
        List<Integer> results = new ArrayList<>();
        TreeNodeTraversal.doPostorderTraversalIteratively(results, buildTree);
        check(results, expected);

        List<Integer> results2 = new ArrayList<>();
        TreeNodeTraversal.doPostorderTraversal2(results2, buildTree);
        check(results2, expected);

        List<Integer> results3 = new ArrayList<>();
        TreeNodeTraversal.doPostorderTraversal3(results3, buildTree);
        check(results3, expected);
    }

    private void check(List<Integer> results, int[] expected){
        for (int i=0; i<results.size(); i++){
            assertEquals(expected[i], results.get(i), 0.00);
        }
    }

}
