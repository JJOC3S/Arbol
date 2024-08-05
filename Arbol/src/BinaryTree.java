import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private Node deleteRec(Node root, int value) {
        if (root == null) {
            return root;
        }
        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }
        return root;
    }

    private int minValue(Node root) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    public List<Integer> preOrder(Node node) {
        List<Integer> list = new ArrayList<>();
        preOrderHelper(node, list);
        return list;
    }

    private void preOrderHelper(Node node, List<Integer> list) {
        if (node != null) {
            list.add(node.value);
            preOrderHelper(node.left, list);
            preOrderHelper(node.right, list);
        }
    }

    public List<Integer> inOrder(Node node) {
        List<Integer> list = new ArrayList<>();
        inOrderHelper(node, list);
        return list;
    }

    private void inOrderHelper(Node node, List<Integer> list) {
        if (node != null) {
            inOrderHelper(node.left, list);
            list.add(node.value);
            inOrderHelper(node.right, list);
        }
    }

    public List<Integer> postOrder(Node node) {
        List<Integer> list = new ArrayList<>();
        postOrderHelper(node, list);
        return list;
    }

    private void postOrderHelper(Node node, List<Integer> list) {
        if (node != null) {
            postOrderHelper(node.left, list);
            postOrderHelper(node.right, list);
            list.add(node.value);
        }
    }
}
