package data_structures;

import java.util.Iterator;

/**
 *
 * @author mert
 */
public class ExpressionEvaluator {

    /**
     * process input
     * @param IOText
     * @return ERROR or result of the expression 
     */
    public String processInput(String IOText) {
        Queue<String> infix = toToken(IOText);
        Queue<String> postfix = infixToPostfix(infix);
        String token;
        Stack<Double> stack = new Stack<>();
        Double num1, num2, result = 0.0;
        char c;
        try {
            while (postfix.isEmpty() == false) {
                token = postfix.dequeue();
                if (isOperator(token)) {
                    num1 = stack.pop();
                    num2 = stack.pop();
                    c = token.charAt(0);
                    switch (c) {
                        case '+':
                            result = num2 + num1;
                            break;
                        case '-':
                            result = num2 - num1;
                            break;
                        case '*':
                            result = num2 * num1;
                            break;
                        case '/':
                            result = num2 / num1;
                            break;
                        case '^':
                            result = Math.pow(num2, num1);
                            break;
                    }
                    stack.push(result);
                } else {
                    stack.push(Double.parseDouble(token));
                }
            }
        } catch (Exception e) {
            return "ERROR";
        }

        return stack.pop() + "";
    }

    /**
     * turn infix to postfix
     * @param infix
     * @return null when there is any error
     *          queue of token when the input is legal
     */
    private Queue<String> infixToPostfix(Queue<String> infix) {
        Queue<String> postfix = new Queue<>();
        Stack<String> stack = new Stack<>();
        Iterator<String> it = infix.iterator();
        int pdc;
        double number;

        try {
            while (it.hasNext()) {
                String token = it.next();
                if (token.equals("(")) {
                    stack.push(token);
                } else if (token.equals(")")) {
                    while (stack.isEmpty() == false
                            && stack.peek().equals("(") == false) {
                        postfix.enqueue(stack.pop());
                    }
                    if (stack.isEmpty()) {
                        throw new NullPointerException();
                    }
                    stack.pop();
                } else if (isOperator(token)) {
                    pdc = precedence(token);
                    while (stack.isEmpty() == false
                            && stack.peek().equals("(") == false
                            && pdc <= precedence(stack.peek())) {
                        postfix.enqueue(stack.pop());
                    }
                    stack.push(token);
                } else {
                    number = Double.parseDouble(token);
                    postfix.enqueue(token);
                }
            }
        } catch (Exception e) {
            return null;
        }

        while (stack.isEmpty() == false) {
            postfix.enqueue(stack.pop());
        }
        return postfix;
    }

    /**
     * 
     * @param op
     * @return 1 to + and -, 2 to * / and ^, other signs get 0
     */
    private int precedence(String op) {
        if (op.equals("+") || op.equals("-")) {
            return 1;
        }
        if (op.equals("*") || op.equals("/") || op.equals("^")) {
            return 2;
        }

        return 0;
    }

    /**
     * test character for operator 
     * @param op
     * @return boolean
     */
    private boolean isOperator(String op) {
        return op.equals("+")
                || op.equals("-")
                || op.equals("*")
                || op.equals("/")
                || op.equals("^");
    }

    /**
     * turn input string to queue of token
     * @param text
     * @return null or a queue of token
     */
    private Queue<String> toToken(String text) {
        text = text.trim();
        Queue<String> tokens = new Queue<>();

        String token = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // case 1: a space
            if (Character.isSpaceChar(c)) {
                if (token.isEmpty() == false) {
                    // there is something in the token
                    tokens.enqueue(token);
                    token = "";
                }
            } else if (Character.isDigit(c) // for number
                    || c == '.') { // for . in double value
                // add character to token
                token += c;
            } else {
                // add current token to queue
                if (token.isEmpty() == false) {
                    tokens.enqueue(token);
                    token = "";
                }
                // add operator to queue
                tokens.enqueue(c + "");
            }

        }
        // put the last token to queue
        if (token.isEmpty() == false) {
            tokens.enqueue(token);
        }
        return tokens;
    }

}
