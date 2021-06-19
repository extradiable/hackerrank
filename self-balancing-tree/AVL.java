public class AVL {
  public static int getHeight(Node n) {
    return n == null ? -1 : n.ht; 
  }
  
  public static int getBalanceFactor(Node n) {
    if (n == null) {
      return 0;
    } else {
      return getHeight(n.left) - getHeight(n.right);
    }
  }
  
  public static void updateHeight(Node n) {
    n.ht = 1 + Math.max(
      getHeight(n.left),
      getHeight(n.right)
      );
  }
  
  public static Node rotateRight(Node r) {
    Node L = r.left;
    Node LR = L.right;
    L.right = r;
    r.left = LR;
    updateHeight(r);
    updateHeight(L);
    return L;
  }

  public static Node rotateLeft(Node r) {
    Node R = r.right;
    Node RL = R.left;
    R.left = r;
    r.right = RL;
    updateHeight(r);
    updateHeight(R);
    return R;
  }

  public static Node rotateLeftThenRight(Node r) {
    r.left = rotateLeft(r.left);
    return rotateRight(r);
  }
  
  public static Node rotateRightThenLeft(Node r) {
    r.right = rotateRight(r.right);
    return rotateLeft(r);
  }
  
  public static String treeToString(Node n) {
    if (n == null) {
      return null;
    }
    boolean isLeaf = n.left == null && n.right == null;
    if (isLeaf) {
      return Integer.toString(n.val);
    } else {
      return n.val + " (" + treeToString(n.left) + ") (" + treeToString(n.right)+ ")";
    }
  }
  
  public static Node insert(Node root, int val) {
    if (root == null) {
      Node n = new Node();
      n.val = val;
      n.ht = 0;
      return n;
    }
    if (val > root.val) {
      root.right = insert(root.right, val);
    } else if (val < root.val) {
      root.left = insert(root.left, val);
    }
    updateHeight(root);
    int bf = getBalanceFactor(root);
    if (bf == 2) {
      if (getHeight(root.left.right) < getHeight(root.left.left)) {
        return rotateRight(root);
      } else {
        return rotateLeftThenRight(root);
      }
    }
    if (bf == -2) {
      if (getHeight(root.right.right) > getHeight(root.right.left)) {
        return rotateLeft(root);
      } else {
        return rotateRightThenLeft(root);
        
      }
    }
    return root;
  }
  
}
