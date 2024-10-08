import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numberButton = new JButton[10];
    JButton[] operatorButton = new JButton[14];
    JButton percButton, CEButton, CButton, delButton, fracButton, sqrButton, dotButton;
    JButton rootButton, divButton, mulButton, subButton, addButton, equButton, plmnButton;
    JPanel panel;

    Font buttonsFont = new Font("Arial", Font.PLAIN, 20);
    Font tfFont = new Font("Arial", Font.BOLD, 70);

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean isOperationEnded = false;

    Calculator() {

        frame = new JFrame("Kalkulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(570, 810);
        frame.setLayout(null);

        textField = new JTextField("");
        textField.setBounds(5, 95, 545, 100);
        textField.setFont(tfFont);
        textField.setFocusable(false);
        textField.setBorder(null);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);

        percButton = new JButton("%");
        CEButton = new JButton("CE");
        CButton = new JButton("C");
        delButton = new JButton("DEL");
        fracButton = new JButton("1/x");
        sqrButton = new JButton("x^2");
        rootButton = new JButton("Root");
        divButton = new JButton("/");
        mulButton = new JButton("*");
        subButton = new JButton("-");
        addButton = new JButton("+");
        equButton = new JButton("=");
        dotButton = new JButton(",");
        plmnButton = new JButton("+/-");

        operatorButton[0] = percButton;
        operatorButton[1] = CEButton;
        operatorButton[2] = CButton;
        operatorButton[3] = delButton;
        operatorButton[4] = fracButton;
        operatorButton[5] = sqrButton;
        operatorButton[6] = rootButton;
        operatorButton[7] = divButton;
        operatorButton[8] = mulButton;
        operatorButton[9] = subButton;
        operatorButton[10] = addButton;
        operatorButton[11] = equButton;
        operatorButton[12] = dotButton;
        operatorButton[13] = plmnButton;

        for (int i = 0; i < 14; i++) {
            operatorButton[i].addActionListener(this);
            operatorButton[i].setFont(buttonsFont);
            operatorButton[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButton[i] = new JButton(String.valueOf(i));
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(buttonsFont);
            numberButton[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(5, 258, 545, 508);
        panel.setLayout(new GridLayout(6, 4, 5, 5));

        panel.add(percButton);
        panel.add(CEButton);
        panel.add(CButton);
        panel.add(delButton);
        panel.add(fracButton);
        panel.add(sqrButton);
        panel.add(rootButton);
        panel.add(divButton);
        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);
        panel.add(mulButton);
        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);
        panel.add(subButton);
        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);
        panel.add(addButton);
        panel.add(plmnButton);
        panel.add(numberButton[0]);
        panel.add(dotButton);
        panel.add(equButton);

        frame.add(panel);
        frame.add(textField);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static void main(String[] args) {

        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (isOperationEnded) {
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButton[i]) {
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    textField.setText("");
                    isOperationEnded = false;
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButton[i]) {
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }
        }
        if (e.getSource() == percButton) {
            num2 = Double.parseDouble(textField.getText());
            num2 = num2 / 100;
            textField.setText(String.valueOf(num2));
        }

        if (e.getSource() == CEButton) {
            textField.setText("");
        }

        if (e.getSource() == CButton) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            isOperationEnded = false;
        }

        if (e.getSource() == delButton) {
            String string = textField.getText();
            textField.setText("");
            for (int j = 0; j < string.length() - 1; j++) {
                textField.setText(textField.getText() + string.charAt(j));
            }
        }

        if (e.getSource() == fracButton) {
            double num = Double.parseDouble(textField.getText());
            result = 1 / num;
            textField.setText(String.valueOf(result));
            isOperationEnded = true;
        }

        if (e.getSource() == sqrButton) {
            num1 = Double.parseDouble(textField.getText());
            result = num1 * num1;
            textField.setText(String.valueOf(result));
            isOperationEnded = true;
        }

        if (e.getSource() == rootButton) {
            num1 = Double.parseDouble(textField.getText());
            result = Math.sqrt(num1);
            textField.setText(String.valueOf(result));
            System.out.println(result);
            isOperationEnded = true;
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
            isOperationEnded = false;
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
            isOperationEnded = false;
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
            isOperationEnded = false;
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
            isOperationEnded = false;
        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(result));
            isOperationEnded = true;
        }

        String currentText = textField.getText();
        if (currentText.length() > 10) {
            textField.setText(currentText.substring(0, 10));
        }

    }
}
