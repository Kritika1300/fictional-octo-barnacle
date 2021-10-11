import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
    // first node
    private Node root;
    BreadthFirstSearch(){
        root = null;
    }
    // Class representing tree nodes
    static class Node{
        int value;
        Node left;
        Node right;
        Node(int value){
            this.value = value;
            left = null;
            right = null;
        }
        public void displayData(){
            System.out.print(value + " ");
        }
    }
    public void insert(int i){
        root = insert(root, i);
    }

    // Recursive insertion of a node
    public Node insert(Node node, int value){
        if(node == null){
            return new Node(value);
        }
        /* Move to the left if passed value is less than the current node */
        if(value < node.value){
            node.left = insert(node.left, value);
        }
        /* Move to the right if passed value is greater than the current node */
        else if(value > node.value){
            node.right = insert(node.right, value);
        }
        return node;
    }

    // Get height of the tree
    public int calculateTreeHeight(Node root){
        if(root == null){
            return 0;
        }else{
            // height of left subtree
            int lsh = calculateTreeHeight(root.left);
            // height of right subtree
            int rsh = calculateTreeHeight(root.right);
            // height in each recursive call
            return Math.max(lsh, rsh) + 1;
        }
    }

    public void levelOrder(){
        int height = calculateTreeHeight(root);
        for(int i = 0; i < height; i++){
            levelOrderTraversal(root, i);
        }
    }
    // Recursive Method for breadth first search
    public void levelOrderTraversal(Node node, int level){
        if(node == null){
            return;
        }
        if(level == 0){
            System.out.print(node.value + " ");
        }else{
            levelOrderTraversal(node.left, level-1);
            levelOrderTraversal(node.right, level-1);
        }
    }

    // Iterative breadth first search
    public void treeLevelOrderTraversal(Node root){
        if(root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.print(node.value + " ");
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BreadthFirstSearch bst = new BreadthFirstSearch();
        bst.insert(50);
        bst.insert(70);
        bst.insert(30);
        bst.insert(15);
        bst.insert(35);
        bst.insert(7);
        bst.insert(22);
        bst.insert(31);
        bst.insert(62);
        bst.insert(87);
        System.out.println("Height of the binary tree = " + bst.calculateTreeHeight(bst.root)+"\n");
        System.out.println("Recursive BFS traversal");
        bst.levelOrder();
        System.out.println("\n");
        System.out.println("Iterative BFS traversal");
        bst.treeLevelOrderTraversal(bst.root);
        System.out.println("\n");
    }
}