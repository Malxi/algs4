package algs.collection;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
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
    }

    public Node put(Node r, Key key, Value val) {
        if (r == null) return new Node(key, val, 1);
        int cmp = key.compareTo(r.key);
        if (cmp < 0) r.left =  put(r.left, key, val);
        else if (cmp > 0) r.right =  put(r.right, key, val);
        else r.val = val;
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
        root = deleteMin(root);
    }

    private Node deleteMin(Node r) {
        if (r.left == null) return r.right;
        r.left = deleteMin(r.left);
        r.N = size(r.left) + size(r.right) + 1;
        return r;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node r) {
        if (r.right == null) return r.left;
        r.right = deleteMax(r.right);
        r.N = size(r.left) + size(r.right) + 1;
        return r;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node r, Key key) {
        if (r == null) return null;
        int cmp = key.compareTo(r.key);
        if (cmp < 0) {
            r.left = delete(r.left, key);
        }
        else if (cmp > 0) {
            r.right = delete(r.right, key);
        }else {
            if (r.right == null) return r.left;
            if (r.left == null) return r.right;
            Node t = r;
            Node x = min(r.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
            r = x;
        }
        r.N = size(r.left) + size(r.right) + 1;
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
}