package es.jtorrente.datastructures;


import java.util.Stack;

/**
 * Created by jtorrente on 04/07/2015.
 */
public class TreeMirror {

    public static class TreeNode<T> {
        public T value;
        public TreeNode<T> left;
        public TreeNode<T> right;

        public TreeNode(T val){
            this.value = val;
        }
    }

    public static void mirrorTreeRecursively(TreeNode root){
        if (root == null){
            return;
        }

        mirrorTreeRecursively(root.left);
        mirrorTreeRecursively(root.right);
        swapChildren(root);
    }

    public static void mirrorTreeIteratively(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        pushIfNothNull(stack, root);

        while (!stack.empty()){
            TreeNode current = stack.pop();
            swapChildren(current);
            pushIfNothNull(stack, current.right);
            pushIfNothNull(stack, current.left);
        }
    }

    private static void swapChildren(TreeNode node){
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    private static void pushIfNothNull(Stack<TreeNode> stack, TreeNode node){
        if (node != null){
            stack.push(node);
        }
    }
}
