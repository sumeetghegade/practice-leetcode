package practice;

public class DecimalToBinary {
    public static void main(String[] args) {
        int n = 1;
        while (true) {
            String binary = decimalToBinary(n);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(binary);
            n++;
        }
    }

    // Convert a decimal number to binary without using built-in functions
    public static String decimalToBinary(int n) {
        if (n == 0) {
            return "0";
        }

        StringBuilder binary = new StringBuilder();
        while (n > 0) {
            binary.append(n % 2);
            n = n / 2;
        }

        return binary.reverse().toString();
    }

}