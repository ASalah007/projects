package com.example.functionplotter;


import java.util.*;

public class Function{
    private String eqn;
    private Function(String eqn){
        this.eqn = eqn;
    };

    public static Function parseFunction(String eqn){
        if(checkFunction(eqn) != -1)
            return null;

        return new Function(eqn.replaceAll("\\s", ""));
    }

    public static boolean isOperand(char x){
        if(Character.isDigit(x)) return true;

        if(Character.isLetter(x)) return true;

        if(x == '-') return true;

        return false;
    }

    public static boolean isOperation(char x){
        return switch(x){
            case '+', '*', '/', '-', '^'-> true;
            default -> false;
        };
    }

    /**return -1 if it is valid equation 
     * return -2 if it is null or blank
     * return -3 if there is more than one vairable
     * otherwise return the index of the invalid character */
    public static int checkFunction(String func){
        if(func == null || func.isBlank()){
            return -2;
        }


        Character var = null;
        for(int i=0 ;i<func.length(); i++){
            if(!Character.isLetter(func.charAt(i)))
                continue;
            if(var != null && func.charAt(i) != var)
                return -3;

            var = func.charAt(i);
        }


        boolean expectingOperand = true;
        int divideOperations =0;

        for(int i=0; i<func.length(); i++){
            char c = func.charAt(i);
            if(c == ' ') continue;

            if(isOperand(c) && expectingOperand){
                expectingOperand = false;

                if(Character.isLetter(c)){
                    continue;
                }

                if(c == '-'){
                    if(i==func.length()-1 || isOperation(func.charAt(i+1)) ) // e.g: "2*x -", "2-*2"
                        return i;

                    i++;

                    if(Character.isLetter(func.charAt(i))){
                        continue;
                    }

                    if(!Character.isDigit(func.charAt(i))) 
                        return i;
                }
                    
                while(i<func.length() && Character.isDigit(func.charAt(i))) 
                    i++; // for multicharacter numbers
                i--; 

            }
            else if(isOperation(c) && !expectingOperand){

                if(func.charAt(i) == '/')
                    divideOperations++;
            
                if(divideOperations > 1)
                    return i;

                expectingOperand = true;
            }
            else{
                return i;
            }
        }

        if(expectingOperand)
            return func.length()-1;
        return -1;
    }

    public double solveFor(double x){

        Deque<String> operandsStack = new ArrayDeque<>();
        Deque<Character> operationsStack = new ArrayDeque<>();

        for(int i=0; i<eqn.length(); i++){

            if(isOperand(eqn.charAt(i))){
                StringBuilder sb = new StringBuilder();
                while(i<eqn.length() && isOperand(eqn.charAt(i))){
                    sb.append(eqn.charAt(i));
                    i++;
                }
                i--;
                operandsStack.push(sb.toString());
                continue;
            }

            if(operationsStack.isEmpty() || precedence(eqn.charAt(i)) > precedence(operationsStack.peek())){
                operationsStack.push(eqn.charAt(i));
                continue;
            }

            String operand2 = operandsStack.pop();
            String operand1 = operandsStack.pop();
            char operation = operationsStack.pop();
            operandsStack.push(eval(operand1, operation, operand2, x));
            i--;
        }
        while(!operationsStack.isEmpty()){
            String operand2 = operandsStack.pop();
            String operand1 = operandsStack.pop();
            char operation = operationsStack.pop();
            operandsStack.push(eval(operand1, operation, operand2, x));
        }

        return Double.parseDouble(operandsStack.pop());
    }
    
    private int precedence(char operation){
        return switch(operation){
            case '/' ->0;
            case '-', '+' -> 1;
            case '*' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    private String eval(String op1, char operation, String op2, double x){
        
        double operand1;
        double operand2;

        if(Character.isLetter(op1.charAt(0)))
            operand1 = x;
        else if (op1.charAt(0) == '-' && Character.isLetter(op1.charAt(1)))
            operand1 = -x;
        else 
            operand1 = Double.parseDouble(op1);

        if(Character.isLetter(op2.charAt(0)))
            operand2 = x;
        else if (op2.charAt(0) == '-' && Character.isLetter(op2.charAt(1)))
            operand2 = -x;
        else 
            operand2 = Double.parseDouble(op2);

        return switch(operation){
            case '+' -> String.valueOf(operand1 + operand2);
            case '-' -> String.valueOf(operand1 - operand2);
            case '*' -> String.valueOf(operand1 * operand2);
            case '/' -> String.valueOf(operand1 / operand2);
            case '^' -> String.valueOf(Math.pow(operand1, operand2));
            default -> "";
        };
    }


    public static void main(String[] args){
        System.out.println("-1 -> "+ checkFunction("x*x"));
        System.out.println("-1 -> "+ checkFunction("x"));
        System.out.println("-1 -> "+ checkFunction("2"));
        System.out.println("-1 -> "+ checkFunction("2+2"));
        System.out.println("-1 -> "+ checkFunction("2*x"));
        System.out.println("-1 -> "+ checkFunction("x^2 +2"));
        System.out.println("-1 -> "+ checkFunction("-x"));
        System.out.println("-1 -> "+ checkFunction("2+y+2"));
        System.out.println("-1 -> "+ checkFunction("x-x+2"));
        System.out.println("-1 -> "+ checkFunction("-2*c"));


        System.out.println("-3 -> "+ checkFunction("-2*c + a"));

        System.out.println(" " + checkFunction("2*x*"));


    } 

}
