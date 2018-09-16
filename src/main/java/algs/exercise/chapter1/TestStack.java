package algs.exercise.chapter1;

import algs.collection.Stack;

public class TestStack{
    public static int scoreOfParentheses(String S) {
        Stack<Integer> st = new Stack<Integer>();
        st.push(0);
        for(int i = 0; i < S.length(); i++){
            char ch = S.charAt(i);
            if(ch == '('){
                st.push(0);
            }else{
                int cur = st.pop();
                st.push(st.pop() + (cur == 0 ? 1 : 2*cur));
            }
        }
        return st.pop();
    }

    public static int prior(char ch){
        int p = 0;
        switch(ch){
            case '+': 
            case '-': p = 1;break;
            case '*':
            case '/': p = 2;break;
        }
        return p;
    }

    public static String convert(String s){
        StringBuilder cs = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')'){
                if(cs.length() > 0)cs.append(' ');
                cs.append(ch);
                if(i < s.length() - 1) cs.append(' ');
            }else if(ch >= '0' && ch <= '9'){
                cs.append(ch);
            }
        }
        return cs.toString();
    }

    public static String infixToPostfix(String s){
        Stack<Integer> stVal = new Stack<Integer>();
        Stack<Character> stOp = new Stack<Character>();
        s = convert(s);
        String[] elements = s.split("\\s+");
        String[] temp = new String[elements.length];
        int len = 0;
        String res = "";

        for(String item : elements){
            char ch = item.charAt(0);
            if(ch >= '0' && ch <= '9'){
                int num = Integer.parseInt(item);
                stVal.push(num);
                temp[len++] = "" + num;
            }else{
                if(stOp.isEmpty()){
                    stOp.push(ch);
                    continue;
                }
                char pk = stOp.peek();
                if(ch == '('){
                    stOp.push(ch);
                    continue;
                }else if(ch == ')'){
                    while(pk != '('){
                        pk = stOp.pop();
                        temp[len++] = "" + pk;
                        pk = stOp.peek();
                    }
                    stOp.pop();
                }else{
                    while(!stOp.isEmpty() && prior(ch) <= prior(pk)){
                        pk = stOp.pop();
                        temp[len++] = "" + pk;
                        if(stOp.isEmpty()) break;
                        pk = stOp.peek();
                    }
                    stOp.push(ch);
                }
            }
        }
        while(!stOp.isEmpty()){
            char pk = stOp.pop();
            temp[len++] = "" + pk;
        }

        for(int i = 0; i < len; i++){
            if(i < len - 1) res += temp[i] +  " ";
            else res += temp[i];
        }

        return res;
    }

    public static int evaluatePostfix(String s){
        Stack<Integer> stVal = new Stack<Integer>();
        String[] elements = s.split("\\s+");

        for(String item : elements){
            char ch = item.charAt(0);
            if(ch >= '0' && ch <= '9'){
                int num = Integer.parseInt(item);
                stVal.push(num);
            }else{
                int b = stVal.pop();
                int a = stVal.pop();
                int r = 0;
                switch(ch){
                    case '+': r = a + b;break;
                    case '-': r = a - b;break;
                    case '*': r = a * b;break;
                    case '/': r = b == 0 ? 0 : a / b;break;
                }
                stVal.push(r);
            }
        }
        return stVal.pop();
    }

    public static String decodeString(String s) {
        int nums = 0;
        s = "1[" + s + "]";
        Stack<Integer> kstack = new Stack<Integer>();
        Stack<String> sstack = new Stack<String>();
        sstack.push("");
        String part = "";
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch >= '0' && ch <= '9'){
                if(!part.equals("")){
                    sstack.push(sstack.pop() + part);
                    part = "";
                }
                nums = nums * 10 + ch - '0';
            }else if(ch == '['){
                kstack.push(nums);
                sstack.push("");
                nums = 0;
            }else if(ch == ']'){
                if(!part.equals("")){
                    sstack.push(sstack.pop() + part);
                    part = "";
                }
                int k = kstack.pop();
                String sp = sstack.pop();
                String tmp = "";
                while(k > 0){
                    tmp = tmp + sp;
                    k--;
                }
                sstack.push(sstack.pop() + tmp);
                part = "";
            }else{
                part += ch;
            }
        }
        return sstack.pop();
    }

    public static int pos;
    public static String recursion(char[] chars, int k){
        if(pos >= chars.length) return "";
        if(chars[pos] == ']'){
            pos++;
            return "";
        }
        StringBuilder tres = new StringBuilder();
        while(chars[pos] != ']'){
            if(chars[pos] >= '0' && chars[pos] <= '9'){
                int tk = 0;
                while(chars[pos] >= '0' && chars[pos] <= '9'){
                    tk = tk * 10 + chars[pos] - '0';
                    pos++;
                }
                pos++;
                tres.append(recursion(chars, tk));
            }else{
                tres.append(chars[pos]);
                pos++;
            }
        }
        pos++;
        StringBuilder res = new StringBuilder();
        while(k > 0){
            res.append(tres);
            k--;
        }
        return res.toString();
    }
    
    public static String recursion(String s){
        char[] chars = new char[s.length() + 1];
        int i;
        for(i = 0; i < s.length(); i++){
            chars[i] = s.charAt(i);
        }
        chars[i] = ']';
        pos = 0;
        return recursion(chars, 1);
    }

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println(recursion(s));
    }
}