import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

public class Frame extends JFrame {
    private JFrame frame;
    private Dimension frameDimenison;
    private JTextField textfield;
    private JPanel panelTextLayer1;
    private JPanel panelNum1;
    private JPanel panelNum2;
    private JPanel panelNum3;
    private JPanel panelNum4;
    private JPanel panelNum5;
    private Container container;


    // 생성자로 frame의 위치 이동.
    public Frame(int x, int y) {
        frame = new JFrame();
        frame.setLocation(x, y);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        WindowEventListener wel = new WindowEventListener();
        frame.addWindowListener(wel);

        // 내부 dimension 인스턴스 생성
        frameDimenison = new Dimension(0, 0);
    }

    // dimension의 width, height 변수를 먼저 할당하고 frame만드는 걸로 작성함.
    public void makeCalculator() {
        frame.setLocation(300, 200);
        // Dimension 인스턴스를 Frame 인스턴스에 넣고, 
        // frame.pack() 매소드를 사용해서 활성화한다?
        frame.setPreferredSize(frameDimenison);
        frame.pack();

        setTextfield("");
        makeContainer();


        frame.add(container);

        frame.setVisible(true);
    }

    public void setDimensionSize(int x, int y) {
        frameDimenison.setSize(x, y);
    }

    public void setTextfield(String text) {
        panelTextLayer1 = new JPanel();
        textfield = new JTextField(text, 18);
        textfield.setFocusable(false);

        panelTextLayer1.add(textfield);
        panelTextLayer1.setLayout(new GridLayout(1, 1));

    }

    private JButton createButton(String num) {
        JButton button = new JButton(num);
        button.setSize(50, 50);

        Insets ins = new Insets(15, 15, 15, 15);
        button.setMargin(ins);
        addButtonclickNum(button);

        return button;
    }

    private void makePanels() {
        panelNum1 = new JPanel();
        panelNum2 = new JPanel();
        panelNum3 = new JPanel();
        panelNum4 = new JPanel();
        panelNum5 = new JPanel();

        panelNum1.setLayout(new GridLayout(1, 4, 0, 0));
        panelNum2.setLayout(new GridLayout(3, 3));
        panelNum3.setLayout(new GridLayout(2, 1));
        panelNum4.setLayout(new GridLayout(1, 2, 0, 0));

        panelNum5.setLayout(new BorderLayout());

        panelNum1.add(createButton("<"));
        panelNum1.add(createButton("/"));
        panelNum1.add(createButton("*"));
        panelNum1.add(createButton("-"));

        panelNum2.add(createButton("7"));
        panelNum2.add(createButton("8"));
        panelNum2.add(createButton("9"));
        panelNum2.add(createButton("4"));
        panelNum2.add(createButton("5"));
        panelNum2.add(createButton("6"));
        panelNum2.add(createButton("1"));
        panelNum2.add(createButton("2"));
        panelNum2.add(createButton("3"));

        panelNum3.add(createButton("+"));
        panelNum3.add(createButton("enter"));

        panelNum4.add(createButton("0"));
        panelNum4.add(createButton("."));

        panelNum1.setBorder(BorderFactory.createEmptyBorder(0, -1, 0, 0));
        panelNum2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panelNum3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panelNum4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panelNum5.add(panelNum1, BorderLayout.NORTH);
        panelNum5.add(panelNum2, BorderLayout.LINE_START);
        panelNum5.add(panelNum3, BorderLayout.CENTER);
        panelNum5.add(panelNum4, BorderLayout.SOUTH);
    }

    private void makeContainer() {
        makePanels();

        container = new Container();


        container.setLayout(new BorderLayout());

        container.add(panelTextLayer1, BorderLayout.BEFORE_FIRST_LINE);
        container.add(panelNum5);
    }

    private void addButtonclickNum(JButton button) {
        button.addActionListener(e -> {
            JButton button1 = (JButton) e.getSource();
            String defaultnum = button1.getText();
            String currentNum = textfield.getText();



            int currentLength = currentNum.length();
            //
            if (currentLength >= 1) {
                char lastInput = currentNum.charAt(currentNum.length() - 1);

                if (!(defaultnum.equals("<")) && ((int) lastInput < 48 || (int) lastInput > 57) && ((int) defaultnum.charAt(0) < 48 || (int) defaultnum.charAt(0) > 57)) {
                    return;
                }

            }

            // < 버튼 눌렀을 때 기능
            if (defaultnum.equals("<")) {
                if (currentNum.length() != 0) {
                    currentNum = currentNum.substring(0, currentNum.length() - 1);

                    textfield.setText(currentNum);
                }
            // enter 버튼 눌렀을 때
            } else if (defaultnum.equals("enter")) {
                System.out.println(existOperator(currentNum));
                Deque tokens = new LinkedList();

                if (existOperator(currentNum)) {

                    tokens = splitOperands(currentNum);

                    checkFirstToken(tokens);

                    checkLastToken(tokens);

                    double result = operate(tokens);
                    String parseResult = String.format("%.6f", result);
                    System.out.println(parseResult);

                    textfield.setText(parseResult);


                } else {
                    tokens.addLast(currentNum);
                }
            // . 버튼 눌렀을 때
            } else if (defaultnum.equals(".")) {

                // 토큰 별로 .이 이미 있는지 구별해야한다.
                // 토큰이 여러 개면 토큰별로 .이 중복인지 검사.
                if (existOperator(currentNum)) {

                    Deque tokens = splitOperands(currentNum);

                    if (isPeriodDuplicated((String) tokens.pollLast())) {
                        return;
                    }


                // 토큰이 하나면
                } else {
                    if (isPeriodDuplicated(currentNum)) {
                        return;
                    }

                }

                currentNum = currentNum.concat(defaultnum);

                textfield.setText(currentNum);

            // 그외 버튼 눌렀을 때
            } else {
                currentNum = currentNum.concat(defaultnum);

                textfield.setText(currentNum);

            }


        });
    }

    private boolean isPeriodDuplicated(String string) {
        char[] charList = string.toCharArray();

        for (char ch : charList) {
            if (ch == '.') {

                return true;
            }
        }

        return false;
    }

    private void checkFirstToken(Deque tokens) {

        Object firstToken = tokens.pollFirst();

        if (firstToken.equals("")) {

        } else if (isStringClass(firstToken) > 0) {
            tokens.addFirst(firstToken);
            return;
        } else if (isStringClass(firstToken) == -1) {
            double secondToken = Double.parseDouble((String) tokens.pollFirst());
            tokens.addFirst(Double.toString(secondToken * -1));
            return;
        }

        checkFirstToken(tokens);


    }

    private void checkLastToken(Deque tokens) {

        Object lastToken = tokens.pollLast();

        if (lastToken.equals("")) {

        } else if (isStringClass(lastToken) > 0) {
            tokens.addLast(lastToken);
            return;
        } else {
            return;
        }

        checkLastToken(tokens);

    }

    // 버튼이벤트에서 enter를 누르면 연산자가 있는지 검사하는 코드가 있음.
    // 그래서, 연산자 검사 안해도 됨. 
    private Map<Integer, Character> indexOperator(String string) {

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

    private boolean existOperator(String string) {
        List<String> operators = new ArrayList<String>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        System.out.println(string);
        for (String operator : operators) {
            System.out.printf("입력한 내용에 연산자가 있는지 확인 %s\n", string.contains(operator));
            if (string.contains(operator)) {
                return true;
            }
        }
        return false;
    }

    // 버튼이벤트에서 enter를 누르면 연산자가 있는지 검사하는 코드가 있음.
    // 그래서, 연산자 유무 검사 안해도 됨.

    //! 맨 마지막 과 맨 처음에 . 들어갔을 때 처리와
    //! .이 textField에 두 개가 들어갔을 때의 처리를 해야한다.
    //! 또, 연산자가 연속으로 두개 이상 들어갈때 뒤의 연산자로바꾸는 처리도 해야 한다.
    private Deque splitOperands(String string) {
        Map<Integer, Character> indexs = indexOperator(string);

        Object[] keyArray = indexs.keySet().toArray();

        Arrays.sort(keyArray);

        Deque deque = new LinkedList();

        for (int i = 0; i < indexs.size(); i++) {
            if (i == 0) {
                deque.add(string.substring(0, (int) keyArray[i]));
            } else {
                deque.add(string.substring((int) keyArray[i - 1] + 1, (int) keyArray[i]));
            }

            deque.add(string.charAt((int) keyArray[i]));
        }

        // 위 for문으로는 마지막 피연산자가 stack에 들어가지 않는다.
        // 마지막 피연산자까지 넣는 코드
        deque.add(string.substring((int) keyArray[indexs.size() - 1] + 1, string.length()));


        return deque;

    }


    /**
     * @param object
     * @return boolean 을 반환하지 않고 -1, 0, 1을 반환한다.
     * 첫 문자가 -일 때 첫 토큰은 음수이기 때문에
     * 첫 문자가 -인 상황도 따로 구별해줘야한다.
     */
    private static int isStringClass(Object object) {

        String dum = "";

        if (object.getClass() == dum.getClass()) {

            return 1;
        } else {
            if ((char) object == '-') {
                return -1;
            } else if ((char) object >= 48 && (char) object <= 57) {
                return 1;
            }
            return 0;
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

    private double operate(Deque deque) {
        Object numOroper = null;
        double result = 0;

        Deque dequeTmp = new LinkedList();

        while (isEnd(deque, dequeTmp)) {

            // if 해서 deque가 *와 /를 가지고 있는지 검사한다.
            if (hasMultiandDiv(deque)) {

                // deque에 들어간 요소를 0번째부터 꺼내온다.
                numOroper = deque.pollFirst();
                // String 클래스인 숫자만 꺼내서 deque2에 넣는다.
                if (isStringClass(numOroper) == 1) {
                    dequeTmp.addLast(numOroper);
                    // Character 클래스로 저장된 연산자를 이용하는 코드.
                } else {

                    if ((isMultiSign(numOroper)) || (isDivSign(numOroper))) {
                        numOroper = calculateDeques(deque, dequeTmp, numOroper);


                    } else {
                        dequeTmp.addLast(numOroper);
                    }
                }

            } else {

                int len = dequeTmp.size();
                System.out.println(len);

                if (len != 0) {
                    for (int i = 0; i < len; i++) {
                        deque.addFirst(dequeTmp.pollLast());
                    }
                } else {
                    calculateDeques(deque, dequeTmp, numOroper);

                }
                // if 에서 *,/ 연산자를 다 계산하거나,
                // 처음부터 없으면 여기로 온다.
                //dequeTmp에 있는 거를 다시 deque에 전부 넣는다.

            }


        }

        result = Double.parseDouble((String) deque.poll());

        numOroper = null;

        System.out.println(result);

        return result;
    }

    /**
     * @param deque
     * @param dequeTmp
     * @return deque1의 두번째 피연산자를 계산할때 deque의 길이가 1이 되고,
     * deque2의 길이는 0이 되지 않는다.
     */
    private boolean isEnd(Deque deque, Deque dequeTmp) {

        int len1 = deque.size();
        int len2 = dequeTmp.size();

        if ((len1 == 1) && (len2 == 0)) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * @param deque
     * @param dequeTmp
     * @param operator
     * @return 계산하는 함수가 완료되면 null을 반환한다.
     * 계산 후에 사용된 operator에 할당된 값을 없애기 위함이다.
     */
    private Object calculateDeques(Deque deque, Deque dequeTmp, Object operator) {

        System.out.println(deque);
        System.out.println(dequeTmp);

        if (operator == null) {
            dequeTmp.addLast(deque.pollFirst());
            operator = deque.pollFirst();
        }

        double operand1 = Double.parseDouble((String) dequeTmp.pollLast());
        double operand2 = Double.parseDouble((String) deque.pollFirst());

        System.out.println(operand1);
        System.out.println(operand2);

        String result = Double.toString(calculation(operand1, operand2, (char) operator));

        deque.addFirst(result);

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

    private double calculation(double num1, double num2, char operater) {
        switch (operater) {

            case '+':
                double sum = Double.sum(num1, num2);

                return sum;

            case '-':
                double diff = num1 - num2;

                return diff;

            case '*':
                double multi = num1 * num2;

                return multi;

            case '/':
                double div = num1 / num2;

                return div;

            default:

                return 1 / 0;
        }
    }


}
