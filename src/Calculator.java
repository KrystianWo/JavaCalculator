import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] operatorButtons = new JButton[14];
    JButton percButton, CEButton, CButton, delButton, fracButton, sqrButton, decimalButton;
    JButton rootButton, divButton, mulButton, subButton, addButton, equButton, changeSignButton;
    JPanel panel;

    Font buttonsFont = new Font("Arial", Font.PLAIN, 20);
    Font textFieldFont = new Font("Arial", Font.BOLD, 70);

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean isOperationEnded = true;

    public Calculator() {
        createJFrame();
        createJTextField();
        createJButtons();
        assignButtons();
        createPanel();
        frame.add(panel);
        frame.add(textField);
        setFrameConfigs();
    }

    private void createJFrame() {
        frame = new JFrame("Kalkulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(570, 810);
        frame.setLayout(null);
    }

    private void createJTextField() {
        textField = new JTextField("0");
        textField.setBounds(5, 95, 545, 100);
        textField.setFont(textFieldFont);
        textField.setFocusable(false);
        textField.setBorder(null);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void createJButtons() {
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
        decimalButton = new JButton(".");
        changeSignButton = new JButton("+/-");
    }

    private void assignButtons() {
        operatorButtons[0] = percButton;
        operatorButtons[1] = CEButton;
        operatorButtons[2] = CButton;
        operatorButtons[3] = delButton;
        operatorButtons[4] = fracButton;
        operatorButtons[5] = sqrButton;
        operatorButtons[6] = rootButton;
        operatorButtons[7] = divButton;
        operatorButtons[8] = mulButton;
        operatorButtons[9] = subButton;
        operatorButtons[10] = addButton;
        operatorButtons[11] = equButton;
        operatorButtons[12] = decimalButton;
        operatorButtons[13] = changeSignButton;

        for (int i = 0; i < 14; i++) {
            operatorButtons[i].addActionListener(this);
            operatorButtons[i].setFont(buttonsFont);
            operatorButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(buttonsFont);
            numberButtons[i].setFocusable(false);
        }
    }

    private void createPanel() {
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
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(changeSignButton);
        panel.add(numberButtons[0]);
        panel.add(decimalButton);
        panel.add(equButton);
    }

    private void setFrameConfigs() {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonClicked(e);
        setTextLength();
    }

    private void buttonClicked(ActionEvent e) {
        if (isOperationEnded) {
            clearData(e);
        }
        joinNumberIfPressed(e);
        var source = e.getSource();
        switch (source) {
            case JButton b when b == percButton -> percentFunction();
            case JButton b when b == CEButton -> clearElementFunction();
            case JButton b when b == CButton -> clearFunction();
            case JButton b when b == delButton -> delFunction();
            case JButton b when b == fracButton -> fracFunction();
            case JButton b when b == sqrButton -> sqrFunction();
            case JButton b when b == rootButton -> rootFunction();
            case JButton b when b == divButton -> divFunction();
            case JButton b when b == mulButton -> mulFunction();
            case JButton b when b == subButton -> subFunction();
            case JButton b when b == addButton -> addFunction();
            case JButton b when b == changeSignButton -> changeSignFunction();
            case JButton b when b == decimalButton -> decimalFunction();
            case JButton b when b == equButton -> equFunction();
            default -> {}
        }
    }

    private void setTextLength() {
        String currentText = textField.getText();
        if (currentText.length() > 10) {
            String updatedText = currentText.substring(0, 10);
            textField.setText(updatedText);
        }
    }

    private void trimDecimal() {
        setTextLength();
        String number = textField.getText();
        String decimalSeparator = ".";
        String[] parts = number.split("\\" + decimalSeparator);

        if (parts.length > 1) {
            String integerPart = parts[0];
            String decimalPart = parts[1];
            String trimmedDecimalPart = decimalPart.replaceAll("0+$", "");
            if (trimmedDecimalPart.isEmpty()) {
                textField.setText(integerPart);
            } else {
                textField.setText(integerPart + decimalSeparator + trimmedDecimalPart);
            }
        }
    }

    private void clearData(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                num1 = 0;
                num2 = 0;
                result = 0;
                textField.setText("");
                isOperationEnded = false;
            }
        }
    }

    private void joinNumberIfPressed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
    }

    private void percentFunction() {
        num1 = Double.parseDouble(textField.getText());
        num1 = num1 / 100;
        textField.setText(String.valueOf(num1));
    }

    private void clearElementFunction() {
        if (num1 != 0) {
            num2 = 0;
            textField.setText("0");
            isOperationEnded = false;
        } else {
            num1 = 0;
            textField.setText("0");
        }
    }

    private void clearFunction() {
        num1 = 0;
        num2 = 0;
        result = 0;
        textField.setText("0");
        isOperationEnded = true;
    }

    private void delFunction() {
        String string = textField.getText();
        if (!string.isEmpty() & string.length() > 1) {
            textField.setText("");
            isOperationEnded = false;
            for (int j = 0; j < string.length() - 1; j++) {
                textField.setText(textField.getText() + string.charAt(j));
            }
        } else {
            textField.setText("0");
            isOperationEnded = true;
        }
    }

    private void fracFunction() {
        double num = Double.parseDouble(textField.getText());
        result = 1 / num;
        textField.setText(String.valueOf(result));
        isOperationEnded = true;
        trimDecimal();
    }

    private void sqrFunction() {
        num1 = Double.parseDouble(textField.getText());
        result = num1 * num1;
        textField.setText(String.valueOf(result));
        isOperationEnded = true;
        trimDecimal();
    }

    private void rootFunction() {
        num1 = Double.parseDouble(textField.getText());
        result = Math.sqrt(num1);
        textField.setText(String.valueOf(result));
        isOperationEnded = true;
        num1 = 0;
        trimDecimal();
    }

    private void divFunction() {
        num1 = Double.parseDouble(textField.getText());
        operator = '/';
        textField.setText("");
        isOperationEnded = false;
    }

    private void mulFunction() {
        num1 = Double.parseDouble(textField.getText());
        operator = '*';
        textField.setText("");
        isOperationEnded = false;
    }

    private void subFunction() {
        num1 = Double.parseDouble(textField.getText());
        operator = '-';
        textField.setText("");
        isOperationEnded = false;
    }

    private void addFunction() {
        num1 = Double.parseDouble(textField.getText());
        operator = '+';
        textField.setText("");
        isOperationEnded = false;
    }

    private void changeSignFunction() {
        Double sign = Double.parseDouble(textField.getText());
        if (sign > 0) {
            textField.setText("-" + sign);
        } else if (sign < 0) {
            sign = -sign;
            textField.setText(String.valueOf(sign));
        }
    }

    private void decimalFunction() {
        String isDecimal = textField.getText();
        if (!isDecimal.contains(".")) {
            textField.setText(Integer.parseInt(isDecimal) + ".");
        }
    }

    private void equFunction() {
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
        trimDecimal();
        num1 = 0;
        num2 = 0;
    }
}