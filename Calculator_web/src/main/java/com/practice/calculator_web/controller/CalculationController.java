
package com.practice.calculator_web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@RestController
@RequestMapping("/calc")
public class CalculationController {

    @PostMapping("/standard")
    public String getStandardOutput(@RequestBody String mathematicalExpression) {
        evaluateExpression(splitExpression(mathematicalExpression));
        return "Standard Output";
    }

    private static Queue<String> splitExpression(String expression) {

        Queue<String> oper_queue = new LinkedList<>();

        String[] operandList = expression.split("[÷x\\-+]");

        String tmp = "";
        for (int i = 0; i < operandList.length; i++) {

            if (i == 0) {
                if (operandList[i].isEmpty()) {
                    i++;
                    oper_queue.add("-" + operandList[i]);
                    tmp += "-" + operandList[i];
                    continue;
                } else {
                    oper_queue.add(operandList[i]);
                    tmp += operandList[i];
                    continue;
                }
            }

            String operator = Character.toString(expression.charAt(tmp.length()));
            tmp += operator;
            oper_queue.add(operator);

            tmp += operandList[i];

            oper_queue.add(operandList[i]);

        }

        return oper_queue;
    }

    private static void evaluateExpression(Queue<String> queue) {
        System.out.println(queue);

        Stack<String> stack = new Stack<>();

        Queue<String> operatorMultiandDiv = getMultiandDiv(queue);

        String tmp;

        while (queue.size() != 0 || stack.size() != 1) {
            tmp = queue.poll();

            if (isOperator(tmp)) {
                // x ÷ 연산자 있으면 계산
                if (operatorMultiandDiv.size() != 0) {

                // x ÷ 연산자 없으면 - + 연산자 계산
                } else {

                }


            } else {
                 stack.push(tmp);
            }

        }

    }

    private static boolean isOperator(String string) {
        char ch = string.charAt(0);
        System.out.println(ch);

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

    private static Queue<String> getMultiandDiv(Queue<String> queue) {

        Queue<String> queueOpers = new LinkedList<>();

        queue.forEach(num -> {
            if (num.equals('÷')) {
                queueOpers.add(num);
            } else if (num.equals('x')) {
                queueOpers.add(num);
            }
        });

        return queueOpers;
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
