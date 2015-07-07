package es.jtorrente.exercises.simple;

import es.jtorrente.datastructures.TreeNode;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by jtorrente on 07/07/2015.
 */
public class BinaryTreeIteratorPostOrder<T> implements Iterator<TreeNode<T>> {
    private Stack<TreeNode<T>> stack = new Stack<>();
    private TreeNode current;

    public BinaryTreeIteratorPostOrder (TreeNode<T> root) {
        current = root;
        while (current!=null) {
            stack.push(current);
            if (current.left!=null) {
                current = current.left;
            } else if (current.right!=null) {
                current = current.right;
            } else {
                current = null;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return current!=null || !stack.empty();
    }

    @Override
    public TreeNode<T> next() {
        TreeNode node = null;
        while (node==null){
            if (current!=null){
                stack.push(current);
                if (current.left!=null){
                    current = current.left;
                } else {
                    current = current.right;
                }
            } else {
                node = stack.pop();
                if (!stack.empty() && stack.peek().left == node){
                    current = stack.peek().right;
                }
            }
        }
        return node;
    }

    private void pushIfNotNull(TreeNode node) {
        if (node==null) {
            return;
        }
        stack.push(node);
    }
}
