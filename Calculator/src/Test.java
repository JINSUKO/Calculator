import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {

        // String[] s = numsSplit("456+489/54+8-45");

        // for (String string : s) {
        //     System.out.println(string);
        // }

        // calculator_logic_test();
        Object object = (Character) '-';
        System.out.println(((char) object == '-'));
//
//        Object object1 = (Character) '1';
//        Object object11 = (String) "d";
//        Object object2 = (Character) '*';
//        char object3 = '*';
//
//        Deque deque = new LinkedList<>();
//
//        deque.addLast(object2);
//
//        System.out.println((char) object2 == '*');
//        System.out.println(hasMultiandDiv(deque));

        // System.out.println(object1.equals('*'));
        // System.out.println(object1.toString() == object2.toString());
        // System.out.println(object2.toString() == object3.toString());
        // System.out.println(object2.toString().equals(object3.toString()));
        // System.out.println(object2.toString().equals('*'));
        // System.out.println(object1);
        // System.out.println(object2);
        // System.out.println(object1.toString());
        // System.out.println(object2.toString());
    }


    // private static String[] numsSplit(String string) {
    //     String regex = "[\\++\\-+\\*+/]";
    //     // Pattern pattern = Pattern.compile("\\+-\\*/");
    //     // Matcher matcher = pattern.matcher(string);

    //     return string.split(regex);

    // }

    private static void calculator_logic_test() {
        String str = "2561-456468*4154568+456465/1541";

        Map<Integer, Character> m = indexOperator(str);

        Object[] keyArray = m.keySet().toArray();

        Arrays.sort(keyArray);

        Deque deque1 = new LinkedList();
        Deque deque2 = new LinkedList();
        
        String token;
        for (int i = 0; i < m.size(); i++) {
            if (i == 0) {
                token = str.substring(0, (int) keyArray[i]);
                
                deque1.add(str.substring(0, (int) keyArray[i]));
                
                
            } else {
                token = str.substring((int) keyArray[i-1] + 1, (int) keyArray[i]);

                deque1.add(str.substring((int) keyArray[i-1] + 1, (int) keyArray[i]));

                
            }

            // System.out.println(token.getClass());
            
            // System.out.println(token);
            // System.out.println((Character) str.charAt((int) keyArray[i]));
            // System.out.println(((Character) str.charAt((int) keyArray[i])).getClass());
            
            deque1.add((Character) (str.charAt((int) keyArray[i])));
            System.out.println(token);
            
            
        }

        // 위 for문으로는 마지막 피연산자가 stack에 들어가지 않는다.
        // 마지막 피연산자까지 넣는 코드
        token = str.substring((int) keyArray[m.size() - 1] + 1, str.length());
        deque1.add(str.substring((int) keyArray[m.size() - 1] + 1, str.length()));  



        System.out.println(deque1);
        
        // numOroper에 요소로 숫자는 String클래스, 연산자는 Charater클래스로 들어감
        Object numOroper;
        String dum = "";
        while (true) {

            // deque1에 들어간 요소를 0번째부터 꺼내온다.
            numOroper = deque1.pollFirst();
            // String 클래스인 숫자만 꺼내서 deque2에 넣는다.
            if (isStringClass(numOroper)) {
                deque2.addLast(numOroper);
            // Character 클래스로 저장된 연산자를 이용하는 코드.
            } else {
                // 여기서 이제 +-*/로 나눠서 계산하는 코드 작성하면됨.
                if ((isMultiSign(numOroper)) || (isDivSign(numOroper))) {
                    double operand1 = Double.parseDouble( (String) deque2.pollLast());                    
                    double operand2 = Double.parseDouble( (String) deque1.pollFirst());

                    calculatMultiandDiv(operand1, operand2, numOroper);
                }
            }

            break;
        }

        
        // if (token.equals("*") || token.equals("/")) {
                        
        // }
        // System.out.println(stack.pop());

    } 

    private static Map<Integer, Character> indexOperator(String string) {

        List<Character> operators = new ArrayList<Character>();
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');

        Map<Integer, Character> indexs = new LinkedHashMap<Integer, Character>();

        char[] chs = string.toCharArray();

        for (int i = 0; i < chs.length; i++) {
            for (char operator : operators) {
                if (chs[i] == operator) {
                    indexs.put(i, operator);
                }
            }
        }


        return indexs;
    }

    private static boolean isStringClass(Object object) {

        String dum = "";

        if (object.getClass() == dum.getClass()) {
            return true;
        } else {
            return false;
        }

    }

    private static boolean isMultiSign(Object operator) {
        if ((char) operator == '*') {
            return true;
        }

        return false;
    }

    private static boolean isDivSign(Object operator) {
        if ((char) operator == '/') {
            return true;
        }


        return false;
    }

    private static double calculatMultiandDiv(double operand1, double operand2, Object operator) {

        double result;
        if (isMultiSign(operator)) {
            result = operand1 * operand2;
        } else {
            result = operand1 / operand2;
        }

        return result;
    }

    private static boolean hasMultiandDiv(Deque deque) {

        char multisign = '*';
        char divsign = '/';

        for (Object object : deque) {
            if (object.equals(multisign) || object.equals(divsign)) {
                return true;
            } 
        }
        return false;
    }
}
