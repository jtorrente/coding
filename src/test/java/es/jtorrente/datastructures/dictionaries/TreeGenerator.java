package es.jtorrente.datastructures.dictionaries;

import es.jtorrente.datastructures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jtorrente on 07/07/2015.
 */
public class TreeGenerator {

    private static class TreeNodeInt extends TreeNode<Integer> {
        TreeNodeInt (int val){
            super.val = val;
        }
    }

    public static TreeNode<Integer> generateIntTree(int levels){
        if (levels<=1) {
            return null;
        }

        TreeNodeInt root = new TreeNodeInt(1);
        Queue<TreeNodeInt> list = new LinkedList<>();
        list.offer(root);
        int maxElem = (int) (Math.pow(2,levels-1)-1);
        for (int i=0; i<maxElem; i++){
            TreeNodeInt current = list.poll();
            TreeNodeInt left = new TreeNodeInt(current.val*2);
            TreeNodeInt right = new TreeNodeInt(current.val*2+1);
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
}
