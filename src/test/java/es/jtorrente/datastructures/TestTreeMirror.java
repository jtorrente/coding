package es.jtorrente.datastructures;

import org.junit.Test;

import java.util.ArrayDeque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Created by jtorrente on 04/07/2015.
 */
public class TestTreeMirror {

    @Test
    public void testRecursively(){
        TreeMirror.TreeNode main = makeTreeAndExpectedResults(4);
        TreeMirror.TreeNode results = main.left;
        TreeMirror.TreeNode expectedMirrored = main.right;

        TreeMirror.mirrorTreeRecursively(results);
        equalsTrees(expectedMirrored, results);
    }

    @Test
    public void testIteratively(){
        TreeMirror.TreeNode main = makeTreeAndExpectedResults(4);
        TreeMirror.TreeNode results = main.left;
        TreeMirror.TreeNode expectedMirrored = main.right;

        TreeMirror.mirrorTreeIteratively(results);
        equalsTrees(expectedMirrored, results);
    }
    
    // Returns a treenode where the left child is the source tree, and the right child
    // is the expected mirrored tree
    private TreeMirror.TreeNode<Integer> makeTreeAndExpectedResults(int nLevels){
        TreeMirror.TreeNode<Integer> root = new TreeMirror.TreeNode<>(1);
        TreeMirror.TreeNode<Integer> mirroredRoot = new TreeMirror.TreeNode<>(1);
        ArrayDeque<TreeMirror.TreeNode<Integer>> q1 = new ArrayDeque<>();
        ArrayDeque<TreeMirror.TreeNode<Integer>> q2 = new ArrayDeque<>();
        q1.offer(root);
        q2.offer(mirroredRoot);

        int nTotalNodes = (int) (Math.pow(2,nLevels)-1);
        int nNodesBuilt = 1;
        while (!q1.isEmpty() && nNodesBuilt< nTotalNodes){
            TreeMirror.TreeNode<Integer> current = q1.poll();
            TreeMirror.TreeNode<Integer> mirroredCurrent = q2.poll();

            int val = current.value*2;
            int mirroredVal = mirroredCurrent.value*2;
            TreeMirror.TreeNode<Integer> left = new TreeMirror.TreeNode<>(val);
            TreeMirror.TreeNode<Integer> right = new TreeMirror.TreeNode<>(val+1);
            TreeMirror.TreeNode<Integer> mirroredleft = new TreeMirror.TreeNode<>(mirroredVal+1);
            TreeMirror.TreeNode<Integer> mirroredright = new TreeMirror.TreeNode<>(mirroredVal);
            current.left = left;
            current.right = right;
            mirroredCurrent.left = mirroredleft;
            mirroredCurrent.right = mirroredright;
            q1.offer(right);
            q1.offer(left);
            q2.offer(mirroredright);
            q2.offer(mirroredleft);

            nNodesBuilt+=2;
        }

        TreeMirror.TreeNode<Integer> main = new TreeMirror.TreeNode<>(0);
        main.left = root;
        main.right = mirroredRoot;

        return main;
    }

    private void equalsTrees(TreeMirror.TreeNode<Integer> expected, TreeMirror.TreeNode<Integer> results){
        if ((expected == null) != (results==null) ){
            fail("One tree is null, the other is not");
        }

        if (expected != null){
            assertEquals(expected.value, results.value);
            equalsTrees(expected.left, results.left);
            equalsTrees(expected.right, results.right);
        }
    }
}
