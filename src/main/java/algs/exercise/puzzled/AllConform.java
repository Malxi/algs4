package algs.exercise.puzzled;

public class AllConform {
    public static void pleaseConformOnePass(String [] caps){
        for(int i = 1; i <= caps.length; i++){
            String curent = i == caps.length ? caps[0] : caps[i];
            if(!curent.equals(caps[i-1])){
                if(!curent.equals(caps[0])){
                    System.out.print("People in position: [" + i + ",");
                }else{
                    System.out.println(i - 1 + "] flip your caps!");
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] caps = {"F", "B", "B", "F", "B", "F", "F", "B"};
        pleaseConformOnePass(caps);
    }
}