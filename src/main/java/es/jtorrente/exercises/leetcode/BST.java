package es.jtorrente.exercises.leetcode;

/**
 * Created by jtorrente on 18/07/2015.
 */
public class BST {
    public int val;
    public BST left;
    public BST right;

    public BST(int v) {
        val = v;
    }

    public boolean containsValueInRange(int lower, int upper) {
        BST p = this;
        while (p!=null) {
            if (p.val >= lower && p.val <= upper) {
                return true;
            } else if (p.val<lower) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        return false;
    }

    public BST insert(int value) {
        return insert(value, this);
    }

    public BST delete(int value) {
        return delete(value, this);
    }

    public static BST insert(int value, BST t) {
        if (t==null) {
            return new BST(value);
        }
        if (value <= t.val) {
            t.left = insert(value, t.left);
        }
        else {
            t.right = insert(value, t.right);
        }
        return t;
    }

    public static BST delete(int value, BST t) {
        if (t==null) {
            return null;
        }
        if (t.val == value) {
            if (t.right!=null) {
                // Find the left-most leaf in t.right
                BST c = t.right;
                BST p = t;
                while (c!=null) {
                    p = c;
                    c = c.left;
                }
                p.left = t.left;
                p.right = t.right.right;
                return p;
            } else if (t.left!=null) {
                // Find the riht-most leaf in t.left
                BST c = t.left;
                BST p = t;
                while (c!=null) {
                    p = c;
                    c = c.right;
                }
                p.right = t.right;
                p.left = t.left.left;
                return p;
            } else {
                return null;
            }
        } else if (value < t.val) {
            t.left = delete(value, t.left);
        } else {
            t.right = delete(value, t.right);
        }
        return t;
    }
}
