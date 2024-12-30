//package be.springboot.pp.dsalgo.stacks;
//
//import java.util.Stack;
//
//public class S002_hr_max_ele {
//    private Stack<Pair> stack;
//
//    public S002_hr_max_ele() {
//        stack = new Stack<>();
//    }
//
//    public void push(int value) {
//        if (stack.isEmpty()) {
//            stack.push(new Pair(value, value));
//        } else {
//            int currentMax = stack.peek().maxValue;
//            int newMax = Math.max(value, currentMax);
//            stack.push(new Pair(value, newMax));
//        }
//    }
//
//    public void pop() {
//        if (!stack.isEmpty()) {
//            stack.pop();
//        } else {
//            throw new IllegalStateException("Stack is empty");
//        }
//    }
//
//    public int top() {
//        if (!stack.isEmpty()) {
//            return stack.peek().value;
//        }
//        throw new IllegalStateException("Stack is empty");
//    }
//
//    public int getMax() {
//        if (!stack.isEmpty()) {
//            return stack.peek().maxValue;
//        }
//        throw new IllegalStateException("Stack is empty");
//    }
//}
//
//class Pair {
//    int value;    // The element value
//    int maxValue; // The maximum value seen so far including this element
//
//    Pair(int value, int maxValue) {
//        this.value = value;
//        this.maxValue = maxValue;
//    }
//}
