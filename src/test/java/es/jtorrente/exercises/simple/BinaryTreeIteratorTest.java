package es.jtorrente.exercises.simple;

import es.jtorrente.datastructures.TreeNode;
import es.jtorrente.datastructures.dictionaries.TreeGenerator;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertTrue;

/**
 * Question from Careercup: http://www.careercup.com/question?id=6312861737418752
 * Given a binary tree, implement an iterator that will iterate through its elements.
 * Question with a lot of ambiguity
 *
 * Created by jtorrente on 07/07/2015.
 */
public class BinaryTreeIteratorTest {

    @Test
    public void testPreOrder(){
        /*
                        1
                  2           3
             4       5     6     7
            8 9    10  11 12 13 14 15
         */
        TreeNode<Integer> root = TreeGenerator.generateIntTree(4);
        BinaryTreeIteratorPreOrder<Integer> iterator = new BinaryTreeIteratorPreOrder<>(root);
        int[] nodes = new int[15];
        int[] expectedResults = {1,2,4,8,9,5,10,11,3,6,12,13,7,14,15};
        int i=0;
        while (iterator.hasNext()){
            nodes[i] = iterator.next().val;
            i++;
        }

        assertTrue(Arrays.equals(nodes, expectedResults));
    }

    @Test
    public void testPostOrder(){
        /*
                        1
                  2           3
             4       5     6     7
            8 9    10  11 12 13 14 15
         */
        TreeNode<Integer> root = TreeGenerator.generateIntTree(4);
        BinaryTreeIteratorPostOrder<Integer> iterator = new BinaryTreeIteratorPostOrder<>(root);
        int[] nodes = new int[15];
        int[] expectedResults = {8, 9, 4, 10, 11, 5, 2, 12, 13, 6, 14, 15, 7, 3, 1};
        int i=0;
        while (iterator.hasNext()){
            nodes[i] = iterator.next().val;
            i++;
        }

        assertTrue(Arrays.equals(nodes, expectedResults));
    }

}
