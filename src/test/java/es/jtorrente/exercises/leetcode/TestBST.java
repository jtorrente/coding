package es.jtorrente.exercises.leetcode;

import es.jtorrente.exercises.leetcode.BST;
import org.junit.Test;

import java.util.Stack;

import static junit.framework.Assert.assertTrue;

/**
 * Created by jtorrente on 18/07/2015.
 */
public class TestBST {

    @Test
    public void test(){
        BST bst = new BST(5);
        bst.insert(3);
        bst.insert(8);
        bst.insert(12);
        bst.insert(1);
        bst.insert(4);
        bst.insert(7);
        checkBST(bst, "5(3(1,4),8(7,12))");
        bst.delete(3);
        checkBST(bst, "5(4(1,n),8(7,12))");
        bst.delete(1);
        checkBST(bst, "5(4,8(7,12))");
        bst.delete(7);
        checkBST(bst, "5(4,8(n,12))");
        bst.delete(12);
        checkBST(bst, "5(4,8)");
    }

    private void checkBST(BST bst, String s) {
        BST bst2 = buildBST(s);
        assertTrue(equalsBST(bst, bst2));
    }

    private boolean equalsBST(BST bst1, BST bst2) {
        if (bst1==null && bst2==null) {
            return true;
        }
        if ((bst1==null) != (bst2==null)) {
            return false;
        }

        return bst1.val == bst2.val && equalsBST(bst1.left, bst2.left) && equalsBST(bst1.right, bst2.right);
    }

    private BST buildBST(String s){
        Stack<BST> nodes = new Stack<>();

        BST current = new BST(0);
        BST root = current;

        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            if (c=='(') {
                nodes.push(current);
                current.left = new BST(0);
                current = current.left;
            } else if (c==',') {
                current = nodes.peek();
                current.right = new BST(0);
                current = current.right;
            } else if (c==')') {
                nodes.pop();
                if (!nodes.empty()) {
                    current = nodes.peek();
                }
            } else if (Character.isDigit(c)) {
                current.val *= 10;
                current.val += c-'0';
            } else if (c=='n'){
                if (current == nodes.peek().left){
                    nodes.peek().left = null;
                } else {
                    nodes.peek().right = null;
                }
            }
        }
        return root;
    }
}
