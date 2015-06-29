package es.jtorrente.exercises.simple;

import java.util.Stack;

/**
 * Problem: write a program that takes an expression as input in String format and calculates its value
 *
 * Examples of valid expressions:
 * 3+2
 * 3+3*4+5-2
 * 3+3*4*5*6
 *
 * Assumptions:
 * (0) Expressions are well formed (e.g. 3* will not happen)
 * (1) Brackets are not allowed
 * (2) Supported operators: +,-,*
 * (3) + and - operators have the same priority
 * (4) * has greater priority than + and -
 *
 * Solution: This is a typical stack problem. The solution can be implemented using one stack or two stacks.
 * A solution that parses the expression into a tree and then calculates the results is also provided.
 *
 * In terms of time efficiency, the three solutions are more or less the same (O(n), where n=length of the input
 * string), being the one with one or two stacks slightly better.
 *
 * In terms of memory efficiency, the solution with one stack is better, as it takes constant time (only two
 * operations are stored in the stack at any time). The solution using two stacks is almost equivalent, although
 * having to use two structures increases memory use.
 *
 * The solution using a tree is clearly the worst in terms of memory efficiency, as it needs to store three
 * pointers per node in the tree. The benefit of this approach is that it would be easier to extend with new
 * functionality (e.g. brackets)
 *
 * Follow-up: A classic follow-up would involve how to implement parenthesis to alter the priority of operations.
 * A typical implementation would involve using a stack of stacks. This sort of implementation is provided in
 * {@link es.jtorrente.exercises.simple.Expressions.CalculatorOneStack}
 *
 * Created by jtorrente on 29/06/2015.
 */
public class Expressions {

    public interface Calculator {
        int calculate(String expression);
    }

    /**
     * SOLUTION THAT PARSES THE EXPRESSION INTO A TREE BEFORE CALCULATING
     */
    public static class CalculatorTree implements Calculator{

        static class Operation {
            Operation parent;

            Operation leftOperand = null;
            Operation rightOperand = null;
            char operator;

            void link(int position, Operation operand){
                if (position%2==0){
                    if (leftOperand!=null){
                        leftOperand.parent = null;
                    }
                    leftOperand = operand;
                    leftOperand.parent = this;
                } else {
                    if (rightOperand!=null){
                        rightOperand.parent = null;
                    }
                    rightOperand = operand;
                    rightOperand.parent = this;
                }
            }

            int calculate(){
                int leftValue = leftOperand.calculate();
                int rightValue = rightOperand.calculate();
                return makeOperation(rightValue, leftValue, operator);
            }

            int findRootCalculate(){
                Operation current = this;
                while (current.parent!=null){
                    current = current.parent;
                }
                return current.calculate();
            }
        }

        static class NumberOp extends Operation {
            int value;
            NumberOp(int value){
                this.value = value;
            }

            int calculate(){
                return value;
            }
        }

        @Override
        public int calculate(String expression) {
            Operation currentNode = new Operation();
            int currentOperand = 0;

            for (int i=0; i<expression.length(); i++){
                char currentChar = expression.charAt(i);
                // Build operand
                if (Character.isDigit(currentChar)){
                    currentOperand = buildOperand(currentOperand, currentChar);
                }

                // Operator
                else if (isOperator(currentChar)){
                    if (currentNode.leftOperand == null){
                        currentNode.link(0, new NumberOp(currentOperand));
                        currentOperand = 0;

                        currentNode.operator = currentChar;
                    }
                    else if (priority(currentNode.operator) >= priority(currentChar)){
                        currentNode.link(1, new NumberOp(currentOperand));
                        currentOperand = 0;

                        Operation newOperation = new Operation();
                        if (currentNode.parent!=null){
                            if (currentNode.parent.leftOperand == currentNode){
                                currentNode.parent.link(0, newOperation);
                            } else {
                                currentNode.parent.link(1, newOperation);
                            }
                        }
                        newOperation.link(0, currentNode);
                        newOperation.operator = currentChar;

                        currentNode = newOperation;
                    }
                    else if (priority(currentNode.operator) < priority(currentChar)){
                        Operation newOperation = new Operation();
                        newOperation.link(0, new NumberOp(currentOperand));
                        currentOperand = 0;
                        newOperation.operator = currentChar;

                        currentNode.link(1, newOperation);
                        currentNode = newOperation;
                    }
                }
            }

            currentNode.rightOperand = new NumberOp(currentOperand);
            return currentNode.findRootCalculate();
        }
    }

    /**
     * IMPLEMENTATION USING ONE STACK FOR BOTH OPERATIONS AND OPERANDS.
     *
     * Includes follow up: introduce "parenthesis". The solution is to use a stack
     * of stacks. When you read ( you push a new stack in the stack of stacks.
     * When you read ) you make that calculation, pop the current stack and push
     * the resulting value into the stack that is currently at the peek.
     */
    public static class CalculatorOneStack implements  Calculator{

        private interface OperandOperator{

        }

        private static class Operand implements OperandOperator{
            int value;
            Operand(int value){
                this.value = value;
            }
        }

        private static class Operator implements OperandOperator{
            char operator;
            Operator(char operator){
                this.operator = operator;
            }
        }

        @Override
        public int calculate(String expression) {
            // Clean all whites
            expression = expression.replaceAll("\\s", "");
            // Initialize stacks and variables
            Stack<Stack<OperandOperator>> stackOfOperandOperators = new Stack<>();
            stackOfOperandOperators.push(new Stack<>());
            Stack<OperandOperator> operandOperators = stackOfOperandOperators.peek();
            int currentOperand = 0;

            for (int i=0; i<expression.length(); i++){
                char currentChar = expression.charAt(i);
                // Open parenthesis
                if (currentChar == '('){
                    stackOfOperandOperators.push(new Stack<>());
                    operandOperators = stackOfOperandOperators.peek();
                }
                // Close parenthesis
                else if (currentChar == ')'){
                    stackOfOperandOperators.pop();
                    operandOperators.push(new Operand(currentOperand));
                    currentOperand = makeLastOperation(operandOperators);
                    operandOperators = stackOfOperandOperators.peek();
                }
                // Build operand
                else if (Character.isDigit(currentChar)){
                    currentOperand = buildOperand(currentOperand, currentChar);
                }
                // Operator
                else if (isOperator(currentChar)){
                    while (!operandOperators.isEmpty() && operandOperators.peek() instanceof Operator){
                        Character previousOperator = ((Operator)operandOperators.peek()).operator;
                        if (priority(previousOperator)>=priority(currentChar)){
                            operandOperators.pop();
                            int leftOperand = ((Operand)operandOperators.pop()).value;
                            currentOperand = makeOperation(currentOperand, leftOperand, previousOperator);
                        } else {
                            break;
                        }
                    }
                    operandOperators.push(new Operand(currentOperand));
                    currentOperand = 0;
                    operandOperators.push(new Operator(currentChar));
                }
            }
            // Make last operation and return
            operandOperators.push(new Operand(currentOperand));
            return makeLastOperation(operandOperators);
        }

        private int makeLastOperation(Stack<OperandOperator> operandOperators){
            while (operandOperators.size()>=3) {
                int rightOperand = ((Operand) operandOperators.pop()).value;
                char operator = ((Operator) operandOperators.pop()).operator;
                int leftOperand = ((Operand) operandOperators.pop()).value;
                operandOperators.push(new Operand(makeOperation(rightOperand, leftOperand, operator)));
            }
            return ((Operand)operandOperators.pop()).value;
        }
    }

    /**
     * SOLUTION USING TWO STACKS, ONE FOR OPERANDS, ANOTHER FOR OPERATIONS
     */
    public static class CalculatorTwoStacks implements Calculator{

        @Override
        public int calculate(String expression) {
            // Clean all whites
            expression = expression.replaceAll("\\s", "");
            // Initialize stacks and variables
            Stack<Integer> operands = new Stack<>();
            Stack<Character> operators = new Stack<>();
            int currentOperand = 0;
            for (int i=0; i<expression.length(); i++){
                char currentChar = expression.charAt(i);
                // Build operand
                if (Character.isDigit(currentChar)){
                    currentOperand = buildOperand(currentOperand, currentChar);
                }
                // Operator
                else if (isOperator(currentChar)) {
                    // Stack current operand
                    operands.push(currentOperand);
                    currentOperand = 0;
                    // Check if there are operations pending. If so, compare operators' priority
                    // then decide if operation must be performed
                    while (!operators.isEmpty() && priority(operators.peek())>=priority(currentChar)){
                        // Make operation and push result back
                        operands.push(makeOperation(operands.pop(), operands.pop(), operators.pop()));
                    }
                    // Stack current operator
                    operators.push(currentChar);
                }
            }
            // Make last operation and return
            operands.push(currentOperand);
            //return makeOperation(operands.pop(), operands.pop(), operators.pop());
            return makeLastOperation(operands, operators);
        }

        private int makeLastOperation(Stack<Integer> operands, Stack<Character> operators){
            while (!operators.isEmpty()){
                operands.push(makeOperation(operands.pop(), operands.pop(), operators.pop()));
            }
            return operands.pop();
        }
    }


    //////////////////////////////////////////////////////////////////
    // COMMON METHODS
    /////////////////////////////////////////////////////////////////

    private static int makeOperation(int rightOperand, int leftOperand, char operator){
        if (operator == '+'){
            return leftOperand+rightOperand;
        }
        if (operator == '-'){
            return leftOperand-rightOperand;
        }
        if (operator == '*'){
            return leftOperand*rightOperand;
        }
        return Integer.MIN_VALUE;
    }

    private static boolean isOperator(char character){
        return character =='+' || character =='*' || character =='-';
    }

    private static int buildOperand(int currentOperand, char currentChar){
        currentOperand *=10;
        currentOperand += currentChar-'0';
        return currentOperand;
    }

    private static int priority(char operator){
        if (operator == '*'){
            return 1;
        }
        if (operator == '+' || operator == '-'){
            return 0;
        }
        return -1;
    }

}
