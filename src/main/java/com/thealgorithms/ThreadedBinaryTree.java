public class ThreadedBinaryTree {

    class Node {
        int data;
        Node left, right;
        boolean leftThread;
        boolean rightThread;

        Node(int data) {
            this.data = data;
            left = right = null;
            leftThread = rightThread = false;
        }
    }

    private Node root;

    public void insert(int key) {
        Node ptr = root;
        Node parent = null;

        while (ptr != null) {
            parent = ptr;

            if (key < ptr.data) {
                if (!ptr.leftThread) {
                    ptr = ptr.left;
                } else break;
            } else {
                if (!ptr.rightThread) {
                    ptr = ptr.right;
                } else break;
            }
        }

        Node newNode = new Node(key);

        if (parent == null) {
            root = newNode;
        } else if (key < parent.data) {
            newNode.left = parent.left;
            newNode.right = parent;

            parent.leftThread = false;
            parent.left = newNode;
            newNode.leftThread = newNode.rightThread = true;

        } else {
            newNode.right = parent.right;
            newNode.left = parent;

            parent.rightThread = false;
            parent.right = newNode;
            newNode.leftThread = newNode.rightThread = true;
        }
    }

    public void inorder() {
        Node curr = leftMost(root);
        while (curr != null) {
            System.out.print(curr.data + " ");

            if (curr.rightThread)
                curr = curr.right;
            else
                curr = leftMost(curr.right);
        }
    }

    private Node leftMost(Node node) {
        if (node == null) return null;

        while (!node.leftThread)
            node = node.left;
        return node;
    }
}
