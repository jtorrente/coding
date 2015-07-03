package es.jtorrente.exercises.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jtorrente on 03/07/2015.
 */
public class TreeNodeTraversal {

     public static class TreeNode {
          public int val;
          public TreeNode left;
          public TreeNode right;
          boolean visited = false;
          public TreeNode(int x) { val = x; }
     }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        doPreorderTraversalIteratively(list, root);
        return list;
    }

    public static void doInorderTraversal2(List<Integer> list, TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p!=null){
            if (p!=null){
                stack.push(p);
                p = p.left;
            } else {
                TreeNode t = stack.pop();
                list.add(t.val);
                p=t.right;
            }
        }
    }

    public static void doPostorderTraversal3(List<Integer> list, TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode c = root;
        while (!stack.isEmpty() || c!=null){
            if (c!=null){
                stack.push(c);
                c = c.left;
            } else {
                TreeNode t = stack.pop();
                list.add(t.val);
                if (!stack.empty() && stack.peek().left == t){
                    c = stack.peek().right;
                }
            }
        }
    }

    /*
                                  1
                   2                            3
             4            5            6                7
         8      9     10    11      12   13           14   15


     */
    public static void doPostorderTraversal2(List<Integer> list, TreeNode root){
        Stack<TreeNode> q = new Stack<>();
        pushIfNotNull(q, root);
        while(!q.empty()){
            TreeNode current = q.pop();
            list.add(current.val);
            pushIfNotNull(q, current.left);
            pushIfNotNull(q, current.right);
        }

        // Reorder list
        int n = list.size();
        for (int i=0; i<n/2; i++){
            list.set(i, list.set(n-i-1, list.get(i)));
        }
    }

    public static void doInorderTraversalIteratively(List<Integer> list, TreeNode root){
        Stack<TreeNode> q = new Stack<>();
        pushIfNotNull(q, root);
        while(!q.empty()){
            TreeNode current = q.pop();
            if (current.visited) {
                list.add(current.val);
            } else {
                current.visited = true;
                pushIfNotNull(q, current.right);
                q.push(current);
                pushIfNotNull(q, current.left);
            }
        }
    }

    public static void doPostorderTraversalIteratively(List<Integer> list, TreeNode root){
        Stack<TreeNode> q = new Stack<>();
        pushIfNotNull(q,root);
        while(!q.empty()){
            TreeNode current = q.pop();
            if (current.visited) {
                list.add(current.val);
            } else {
                current.visited = true;
                q.push(current);
                pushIfNotNull(q, current.right);
                pushIfNotNull(q, current.left);
            }
        }
    }

    public static void doPreorderTraversalIteratively(List<Integer> list, TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> q = new Stack<>();
        q.push(root);
        while(!q.empty()){
            TreeNode current = q.pop();
            list.add(current.val);
            if (current.right!=null){
                q.push(current.right);
            }
            if (current.left!=null){
                q.push(current.left);
            }
        }
    }

    private static void pushIfNotNull(Stack<TreeNode> s, TreeNode node){
        if (node!=null){
            s.push(node);
        }
    }

    private static void doTraversalRecursively(List<Integer> list, TreeNode root){
        // base case: nothing to add
        if (root==null){
            return;
        }

        // recursive case
        list.add(root.val);
        doTraversalRecursively(list, root.left);
        doTraversalRecursively(list, root.right);
    }
}
