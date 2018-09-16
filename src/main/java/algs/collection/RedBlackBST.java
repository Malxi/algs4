package algs.collection;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private Node root;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        private boolean color;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = BLACK;
        }

        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node r) {
        if (r == null) return false;
        return r.color == RED;
    }

    private Node rotateLeft(Node h) {
        /* h.right is red */
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        /* h.left is red */
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node blance(Node h) {
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        return h;
    }

    private Node moveRedLeft(Node h) {
        /* let left child to a 3 or 4 node */
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        /* let right child to a 3 or 4 node */
        flipColors(h);
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    public int size() {
        return size(root);
    }

    public int size(Node r) {
        if (r == null){
            return 0;
        }
        return r.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node r, Key key) {
        if (r == null) return null;
        int cmp = key.compareTo(r.key);
        if (cmp < 0) return get(r.left, key);
        else if (cmp > 0) return get(r.right, key);
        else return r.val;
    }

    public void put(Key key, Value val){
        root = put(root, key, val);
        root.color = BLACK;
    }

    public Node put(Node r, Key key, Value val) {
        if (r == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(r.key);
        if (cmp < 0) r.left =  put(r.left, key, val);
        else if (cmp > 0) r.right =  put(r.right, key, val);
        else r.val = val;

        /* blance */
        r = blance(r);
        
        r.N = size(r.left) + size(r.right) + 1;
        return r;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node r) {
        if (r.left == null) return r;
        return min(r.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node r) {
        if (r.right == null) return r;
        return max(r.right);
    }

    public Key floor(Key key) {
        Node r = floor(root, key);
        if (r == null) return null;
        return r.key;
    }

    private Node floor(Node r, Key key) {
        if (r == null) return null;
        int cmp = key.compareTo(r.key);
        if (cmp == 0) return r;
        if (cmp < 0) return floor(r.left, key);
        Node t = floor(r.right, key);
        if (t != null) return t;
        else return r;
    }

    public Key ceiling(Key key) {
        Node r = ceiling(root, key);
        if (r == null) return null;
        return r.key;
    }

    private Node ceiling(Node r, Key key) {
        if (r == null) return null;
        int cmp = key.compareTo(r.key);
        if (cmp == 0) return r;
        if (cmp > 0) return ceiling(r.right, key);
        Node t = ceiling(r.left, key);
        if (t != null) return t;
        else return r;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node r, int k) {
        if (r == null) return null;
        int t = size(r.left);
        if (t > k) return select(r.left, k);
        else if (t < k) return select(r.right, k-t-1);
        else return r;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node r, Key key) {
        if (r == null) return 0;
        int cmp = key.compareTo(r.key);
        if (cmp < 0) return rank(r.left, key);
        else if (cmp > 0) return 1 + size(r.left) + rank(r.right, key);
        else return size(r.left);
    }

    public void deleteMin() {
        /* from root start must set root to be Red */
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (isRed(root)) root.color = BLACK;
    }

    private Node deleteMin(Node r) {
        if (r.left == null) return null;
        /* 
            The virtue keeps current node is a 3 or 4 node, 
            when left node and its left node are not red, we get a 2 node left. 
        */
        if (!isRed(r.left) && !isRed(r.left.left)) {
            r = moveRedLeft(r);
        }

        r.left = deleteMin(r.left);
        r.N = size(r.left) + size(r.right) + 1;

        /* blance */
        r = blance(r);
        
        return r;
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (isRed(root)) root.color = BLACK;
    }

    private Node deleteMax(Node r) {
        /* rotate the left to right */
        if (isRed(r.left)) r = rotateRight(r);
        if (r.right == null) return null;
        /* 
            The virtue keeps current node is a 3 or 4 node, 
            when right node and its left node are not red (only left can be red), we get a 2 node left. 
        */
        if (!isRed(r.right) && !isRed(r.right.left)) {
            r = moveRedRight(r);
        }

        r.right = deleteMax(r.right);
        r.N = size(r.left) + size(r.right) + 1;

        /* blance */
        r = blance(r);

        return r;
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (isRed(root)) root.color = BLACK;
    }

    private Node delete(Node r, Key key) {
        if (r == null) return null;

        if (key.compareTo(r.key) < 0) {
            /* same as delete min */
            if (!isRed(r.left) && !isRed(r.left.left)) {
                r = moveRedLeft(r);
            }
            r.left = delete(r.left, key);

        } else {
            /* keep the right child is red */
            if (isRed(r.left)) r = rotateRight(r);

            /* the delete node is at bottom */
            if (key.compareTo(r.key) == 0 && r.right == null) return null;

            /* the delete node is not at bottom */
            if (key.compareTo(r.key) == 0) {
                Node t = r;
                Node x = min(r.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
                r = x;
            }else {
                /* same as delete max */
                if (!isRed(r.right) && !isRed(r.right.left)) {
                    r = moveRedRight(r);
                }
            }
        }
        r.N = size(r.left) + size(r.right) + 1;
        /* blance */
        r = blance(r);

        return r;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node r, Queue<Key> queue, Key lo, Key hi) {
        if (r == null) return;
        int cmplo = lo.compareTo(r.key);
        int cmphi = hi.compareTo(r.key);
        if (cmplo < 0) keys(r.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(r.key);
        if (cmphi > 0) keys(r.right, queue, lo, hi);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> mp = new RedBlackBST<Integer, Integer>();
        for (int i = 0; i < 20; i++) {
            mp.put(i, i);
        }

        for (int k : mp.keys()) {
            System.out.print(k + "-" + mp.get(k) + " ");
        }

        for (int i = 0; i < 20; i++) {
            System.out.println(mp.min());
            mp.delete(i);
        }
    }
}