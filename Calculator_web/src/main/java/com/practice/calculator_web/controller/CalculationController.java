
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
        evaluateExpress(splitExpression(mathematicalExpression));
        return "Standard Output";
    }

    private static Queue<String> splitExpression(String expression) {

        Queue<String> oper_queue = new LinkedList<>();

        String[] operandList = expression.split("[÷x\\-+]");

        expression.charAt(operandList[0].length());

        String tmp = "";
        for (int i = 0; i < operandList.length; i++) {

            if (i == 0) {
                if (operandList[i].equals("")) {
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

        String tmp = null;

        while (queue.size() || (stack.size() != 1)) {
            tmp = queue.remove();

            if (isOperator(tmp)) {
                // x ÷ 연산자 있으면 계산
                if (operatorMultiandDiv.size()) {

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


//[, 2130, 342, 423, 542, 4536]
//- - x ÷ -
}
