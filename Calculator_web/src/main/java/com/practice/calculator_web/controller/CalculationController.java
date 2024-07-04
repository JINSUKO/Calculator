
package com.practice.calculator_web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Deque;
import java.util.Stack;

@RestController
@RequestMapping("/calc")
public class CalculationController {

    @PostMapping("/standard")
    public String getStandardOutput(@RequestBody String mathematicalExpression) {

        return evaluateExpression(splitExpression(mathematicalExpression));
    }

    private static Deque<String> splitExpression(String expression) {

        Deque<String> oper_queue = new LinkedList<>();

        String[] operandList = expression.split("[÷x\\-+]");

        String tmp = "";
        for (int i = 0; i < operandList.length; i++) {

            if (i == 0) {
                if (operandList[i].isEmpty()) {
                    i++;
                    oper_queue.addLast("-" + operandList[i]);
                    tmp += "-" + operandList[i];
                    continue;
                } else {
                    oper_queue.addLast(operandList[i]);
                    tmp += operandList[i];
                    continue;
                }
            }

            String operator = Character.toString(expression.charAt(tmp.length()));
            tmp += operator;
            oper_queue.addLast(operator);

            tmp += operandList[i];

            oper_queue.addLast(operandList[i]);

        }

        return oper_queue;
    }

    private static String evaluateExpression(Deque<String> queue) {

        Stack<String> stack = new Stack<>();

        Deque<String> operatorMultiandDiv = getMultiandDiv(queue);

        String tmp = null;

        while (!(queue.size() == 0 && stack.size() == 1)) {

            tmp = queue.poll();

            if (isOperator(tmp)) {

                computeWithCollections(queue, stack, tmp, operatorMultiandDiv);

            } else {
                stack.push(tmp);
            }
        }

        return stack.pop();
    }

    private static boolean isOperator(String string) {
        char ch = string.charAt(0);

         switch (ch) {
            case '-':
                if (string.length() > 1) {
                    return false;
                }
            case '+':
            case '÷':
            case 'x':
                return true;
            default:
                return false;
        }
    }

    private static Deque<String> getMultiandDiv(Deque<String> queue) {

        Deque<String> queueOpers = new LinkedList<>();

        queue.forEach(num -> {
            if (num.equals("÷")) {
                queueOpers.addLast(num);
            } else if (num.equals("x")) {
                queueOpers.addLast(num);
            }
        });

        return queueOpers;
    }

    private static void computeWithCollections(Deque<String> queue, Stack<String> stack, String operator, Deque<String> queueOpers) {
        String result;

        if (queueOpers.size() != 0 && (operator.equals("÷") || operator.equals("x"))) {

            result = compute(stack.pop(), queue.poll(), operator);
            stack.push(result);

            queueOpers.remove();

            // x ÷ 연산 후에 queue 가 비워진다.
            // + - 연산을 추가로 하기 위해서 queue 에 값을 다시 넣는다.
            if (queueOpers.size() == 0) {
                // x ÷ 모든 연산 후에 다시 + - 연산을 시작하면서 여기에 다시 걸린다.
                // 조건을 추가해야한다.
                while (stack.size() != 0) {
                    queue.addFirst(stack.pop());
                }

            }
        } else if (queueOpers.size() == 0 && (operator.equals("-") || operator.equals("+"))) {

            result = compute(stack.pop(), queue.poll(), operator);
            stack.push(result);

        // 계산이 안됐을 때, 연산자를 스택에 넣음.
        } else {
            stack.push(operator);
        }
    }

    private static String compute(String operand1, String operand2, String opertor) {

        double num1 = Double.parseDouble(operand1);
        double num2 = Double.parseDouble(operand2);

         switch (opertor) {
            case "-":
                return Double.toString(num1 - num2);
            case "+":
                return Double.toString(num1 + num2);
            case "÷":
                return Double.toString(num1 / num2);
            case "x":
                return Double.toString(num1 * num2);
        }

        return null;
    }

//[, 2130, 342, 423, 542, 4536]
//- - x ÷ -
}
