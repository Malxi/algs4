package algs.exercise.puzzled;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class WordSearch {
    
    class Node {
        int cnt;
        Node[] suffixs;
        public Node() {
            cnt = 0;
            suffixs = new Node[26];
        }
    }
    
    private List<String> ans;
    
    private Node crateTrieTree(String[] words) {
        Node root = new Node();
        for (String w : words) {
            Node p = root;
            for (int i = 0; i < w.length(); i++) {
                char ch = w.charAt(i);
                if (p.suffixs[ch-'a'] == null) {
                    p.suffixs[ch-'a'] = new Node();
                }
                p = p.suffixs[ch-'a'];
            }
            p.cnt += 1;
        }
        return root;
    }
    
    private void dfs(char[][] board, boolean[][] vis, int sx, int sy, Node p, String buf) {
        int ind = board[sx][sy]-'a';
        int m = board.length;
        int n = m > 0 ? board[0].length : 0;
        if (p.cnt > 0) {
            p.cnt = 0;
            ans.add(buf);
        }
        if (p.suffixs[ind] == null) {
            return;
        }
        vis[sx][sy] = true;
        if (sx + 1 < m && !vis[sx+1][sy]) {
            ind = board[sx+1][sy]-'a';
            dfs(board, vis, sx+1, sy, p.suffixs[ind], buf+board[sx][sy]);
        }
        if (sx - 1 >= 0 && !vis[sx-1][sy]) {
            dfs(board, vis, sx-1, sy, p.suffixs[ind], buf+board[sx][sy]);
        }
        if (sy + 1 < n && !vis[sx][sy+1]) {
            dfs(board, vis, sx, sy+1, p.suffixs[ind], buf+board[sx][sy]);
        }
        if (sy - 1 >= 0 && !vis[sx][sy-1]) {
            dfs(board, vis, sx, sy-1, p.suffixs[ind], buf+board[sx][sy]);
        }
        vis[sx][sy] = false;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = m > 0 ? board[0].length : 0;
        boolean[][] vis = new boolean[m][n];
        Node root = crateTrieTree(words);
        ans = new ArrayList<String>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int ind = board[i][j] - 'a';
                if(root.suffixs[ind] != null) 
                    dfs(board, vis, i, j, root.suffixs[ind], ""+board[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char[] r1 = new char[] {'o','a','a','n'};
        char[] r2 = new char[] {'e','t','a','e'};
        char[] r3 = new char[] {'i','h','k','r'};
        char[] r4 = new char[] {'i','f','l','v'};
        char[][] board = new char[][] {r1, r2, r3, r4};
        String[] words = new String[] {"oath","pea","eat","rain"};
        char[][] b = new char[][] {new char[] {'a'}};
        String[] w = new String[] {"a"};
        ws.findWords(board, words);
    }
}

