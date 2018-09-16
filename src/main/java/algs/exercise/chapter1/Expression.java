package algs.exercise.chapter1;

import java.util.Stack;

public class Expression{
    public static int calculate(String s) {
        final int BEGIN = 0, INT = 1, ADD = 2, MINUS = 3, MUL = 4, DIV = 5, END = 6, LPARENT = 7, RPARENT = 8;
        Stack<Integer> nums = new Stack<Integer>();
        Stack<Integer> ops = new Stack<Integer>();
        int[] prior = {-1, 0, 1, 1, 2, 2, 3, 0, 5};
        int num = 0, i = 0, len = s.length();
        nums.push(0);
        ops.push(BEGIN);
        while(i < len){
            char ch = s.charAt(i);
            int sy = END;
            while(ch == ' '){
                i++;
                if(i < len) ch = s.charAt(i);
                else ch = 0;
            }
            if(ch >= '0' && ch <= '9'){
                sy = INT;
                num = 0;
                while(ch >= '0' && ch <= '9'){
                    num = num * 10 + ch - '0';
                    i++;
                    if(i < len) ch = s.charAt(i);
                    else ch = 0;
                    while(ch == ' '){
                        i++;
                        if(i < len) ch = s.charAt(i);
                        else ch = 0;
                    }
                }
            }else{
                switch(ch){
                    case '+': sy = ADD;break;
                    case '-': sy = MINUS;break;
                    case '*': sy = MUL;break;
                    case '/': sy = DIV;break;
                    case '(': sy = LPARENT;break;
                    case ')': sy = RPARENT;break;
                    default : sy = END;break;
                }
                i++;
            }
            
            if(sy == INT) nums.push(num);
            else if(sy == END) break;
            else {
                if(ops.isEmpty()) ops.push(sy);
                else{
                    int sop = ops.peek();
                    if(sy == LPARENT){
                        ops.push(sy);
                        continue;
                    }
                    if(sy == RPARENT){
                        while(sop != LPARENT){
                            int a = nums.pop();
                            int b = nums.pop();
                            sop = ops.pop();
                            nums.push(cal(sop, b, a));
                            sop = ops.peek();
                        }
                        ops.pop();
                        continue;
                    }
                    if(prior[sy] > prior[sop]) ops.push(sy);
                    else{
                        while(sop != LPARENT && prior[sy] <= prior[sop]){
                            int a = nums.pop();
                            int b = nums.pop();
                            ops.pop();
                            nums.push(cal(sop, b, a));
                            sop = ops.peek();
                        }
                        ops.push(sy);
                    }
                }
            }
        }
        while(!ops.isEmpty()){
            int op = ops.pop();
            if(op == LPARENT || op == BEGIN) continue;
            int a = nums.pop();
            int b = nums.pop();
            nums.push(cal(op, b, a));
        }
        return nums.pop();
    }

    public static int cal(int op, int a, int b){
        int res = 0;
        final int BEGIN = 0, INT = 1, ADD = 2, MINUS = 3, MUL = 4, DIV = 5, END = 6, LPARENT = 7, RPARENT = 8;

        switch(op){
            case ADD: res = (a + b);break;
            case MINUS: res = (a - b);break;
            case MUL: res = (a * b);break;
            case DIV: res = (a / b);break;
            default: System.out.println("Error: undefined operator");
        }

        return res;
    }
}