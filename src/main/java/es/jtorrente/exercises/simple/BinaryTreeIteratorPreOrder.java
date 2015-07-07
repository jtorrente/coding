package es.jtorrente.exercises.simple;

import es.jtorrente.datastructures.TreeNode;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by jtorrente on 07/07/2015.
 */
public class BinaryTreeIteratorPreOrder<T> implements Iterator<TreeNode<T>> {
    Stack<TreeNode> stack = new Stack<>();

    public BinaryTreeIteratorPreOrder(TreeNode root) {
        stack.push(root);
    }

    @Override
    public boolean hasNext(){
        return !stack.empty();
    }

    @Override
    public TreeNode<T> next(){
        if (stack.empty()){
            throw new NoSuchElementException("Can’t call next() before hasNext() or after it "+
                    "has returned false!");
        }

        TreeNode peek = stack.pop();
        pushIfNotNull(peek.right);
        pushIfNotNull(peek.left);
        return peek;
    }

    private void pushIfNotNull(TreeNode node){
        if (node==null){
            return;
        }
        stack.push(node);
    }
}
