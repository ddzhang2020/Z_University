package Calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalListener implements ActionListener {
    //Responding to listener events
    public void actionPerformed(ActionEvent e) {

        CalStans.outcome = "";

        //Clear the last input operand
        if ((e.getSource()) == CalStans.CE) {
            int pos = posOfLastOperator(CalStans.exp);
            if (pos >= 0)
                CalStans.exp = CalStans.exp.substring(0, pos + 1);
            else  //Only one operand is directly cleared
                CalStans.exp = "0";
            CalStans.show.setText(CalStans.exp);
        }
        //Empty expression
        else if ((e.getSource()) == CalStans.C) {
            CalStans.exp = "0";
            CalStans.show.setText(CalStans.exp);
        }
        //Clear the last character
        else if ((e.getSource()) == CalStans.del) {
            CalStans.exp = CalStans.exp.substring(0, CalStans.exp.length() - 1);
            if (CalStans.exp.length() == 0)  //After deleting all the input, set to display 0
                CalStans.exp = "0";
            CalStans.show.setText(CalStans.exp);
        }
        //Receive operator
        else if ((e.getSource()) == CalStans.plus || (e.getSource()) == CalStans.minus || (e.getSource()) == CalStans.mul || (e.getSource()) == CalStans.div) {
            if (CalStans.exp.length() != 0 && (!isOperator(CalStans.exp.charAt(CalStans.exp.length() - 1)))) //You must have a number to enter an operator
                CalStans.exp += e.getActionCommand();
            CalStans.show.setText(CalStans.exp);
        }
        //Infix expression is evaluated and output
        else if ((e.getSource()) == CalStans.eql) {
            CalStans.exp+='#';
            //Split the numbers from the expression
            String[] nums = CalStans.exp.split("[^.0-9]");
            System.out.println(nums);
            List<Double> numList = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {   //Convert each string of numbers to DOUBLE type
                if (!"".equals(nums[i]))
                    numList.add(Double.parseDouble(nums[i]));
            }
            double out = getValueOfMid(CalStans.exp, numList);  //Evaluation using infix
            CalStans.outcome = "" + out;  //Convert the obtained result into a string
            CalStans.exp = CalStans.exp.substring(0,CalStans.exp.length()-1);
            CalStans.showexp.setText(CalStans.exp);
            CalStans.show.setText(CalStans.outcome);
            CalStans.exp = "";
        }
        //Get the label value corresponding to the button
        else {
            if (CalStans.exp == "0")
                CalStans.exp = "";
            CalStans.exp += e.getActionCommand();
            CalStans.show.setText(CalStans.exp);
        }

    }

    //Determine whether a character is a number
    public static boolean isDigit(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    //Determine whether a string is all numbers
    public boolean isDigitString(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

    //Determine whether a character is an operator
    public boolean isOperator(char c) {
        return (c == '+') || (c == '-') || (c == '*') || (c == '/');
    }

    //Find the position of the last operator in the string
    public int posOfLastOperator(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if (isOperator(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    //Define the priority of operators in the stack and return the priority of the corresponding operator
    public static int isp(char ch) {
        switch (ch) {

            case '*':
                return 2;
            case '/':
                return 2;
            case '+':
                return 1;
            case '-':
                return 1;
            case '#':
                return 0;
        }
        return -1;
    }

    //Define the priority of operators outside the stack and return the priority of the corresponding operator
    public static int osp(char ch) {
        switch (ch) {
            case '*':
                return 2;
            case '/':
                return 2;
            case '+':
                return 1;
            case '-':
                return 1;
            case '#':
                return 0;
        }
        return 0;
    }

    //Calculate the two operands taken out and the corresponding operator and return the calculation result
    public static double compute(double a, char ch, double b) {
        switch (ch) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                break;
        }
        return 0;
    }

    ////Evaluate the input formula (infix formula)
    public static double getValueOfMid(String exp,List<Double> numList){

        //Define the operator stack
        Stack<Character> optr = new Stack<>();
        //Define the operand stack
        Stack<Double> opnd = new Stack<>();
        double outcome = 0;
        double a,b;
        char sym;
        optr.push('#');
        int i = 0,j = 0;
        while(exp.charAt(i)!='#' || optr.peek()!='#'){
            //When traversing to the number, skip the number string, and replace the previously divided double type data on the stack
            if(isDigit(exp.charAt(i))){
                while(isDigit(exp.charAt(i))) {
                    i++;
                    if(i==exp.length())
                        break;
                }
                i--;
                opnd.push(numList.get(j));
                j++;
            }

            else {
                sym = optr.peek();
                int m = isp(sym);
                int n = osp(exp.charAt(i));
                if(m<n)
                    optr.push(exp.charAt(i));   //The precedence of operators inside the stack is less than the operators outside the stack
                else{     //The precedence of operators outside the stack is less than the operators inside the stack
                    //sym = optr.peek();
                    optr.pop();   //Remove top operator
                    if(m == n || m >n){
                        b = opnd.peek();
                        opnd.pop();      //Remove top operand
                        if(!opnd.empty()){  //When the operand stack is not empty, continue to take out the number in the stack for operation
                            a = opnd.peek();
                            opnd.pop();
                            opnd.push(compute(a,sym,b));
                            continue;
                        }

                    }
                }

            }
            i++;
        }
        outcome = opnd.peek();
        return outcome;
    }

}
