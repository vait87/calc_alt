import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите выражение (для выхода введите 'exit'): ");
            String input = scanner.nextLine().trim();

            if (input.equals("exit")) {
                break;
            }

            try {
                String result = evaluateExpression(input);
                System.out.println("Результат: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    public static String evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Недопустимое выражение");
        }

        String operand1 = tokens[0];
        String operator = tokens[1];
        String operand2 = tokens[2];

        if (operand1.length() > 10 || operand2.length() > 10) {
            throw new IllegalArgumentException("Длина строк не должна превышать 10 символов");
        }

        String resultStr = "";

        if (operator.equals("+")) {
            resultStr = operand1 + operand2;
        } else if (operator.equals("-")) {
            resultStr = operand1.replace(operand2, "");
        } else if (operator.equals("*")) {
            int n = Integer.parseInt(operand2);
            StringBuilder repeated = new StringBuilder();
            for (int i = 0; i < n; i++) {
                repeated.append(operand1);
            }
            resultStr = repeated.toString();
        } else if (operator.equals("/")) {
            int n = Integer.parseInt(operand2);
            int len = Math.min(operand1.length(), n);
            resultStr = operand1.substring(0, len);
        } else {
            throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        }

        if (resultStr.length() > 40) {
            resultStr = resultStr.substring(0, 40) + "...";
        }

        return resultStr;
    }
}