
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuperCalculator {

    private int A;
    private int B;
    private int result;
    private Action action;
    private boolean isRoman = false;

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        SuperCalculator calc = new SuperCalculator();
        calc.calculateMain(scanner.nextLine());
        scanner.close();
    }

    public void calculateMain(String s) {

        try {
            parseString(s);
            Calculation calculation = new Calculation(this);
            calculation.calculate();

            if (isRoman) {
                System.out.println(Roman_Arabic_Converter.ArabicToRoman(result));
            }else{
                System.out.println(result);
            }

        }catch (CalculatorExeption e){
            System.out.println(e.getMessage());
        }

    }


    private void parseString(String s) throws CalculatorExeption {

        if (checkString(s, "[ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩ]* [-,+,*,/]? [ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩ]*")) {
            isRoman = true;
        }else if (!checkString(s, "[0123456789]* [-,+,*,/]? [0123456789]*")){
            throw new CalculatorExeption("The input format is not correct. Check the input data.");
        }

        String[] array = s.split(" ");

        try {
            A = isRoman ? Roman_Arabic_Converter.RomanToArabic(array[0]) : Integer.parseInt(array[0]);
            B = isRoman ? Roman_Arabic_Converter.RomanToArabic(array[2]): Integer.parseInt(array[2]);
        }catch (CalculatorExeption e){
            throw  e;
        }


        switch (array[1]){
            case "+": action = Action.ADDITION;
                break;
            case "-": action = Action.SUBTRACTION;
                break;
            case "*": action = Action.MULTIPLICATION;
                break;
            case "/": action = Action.DIV;
                break;
        }

    }

    private boolean checkString(String s, String pattern) {

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);

        String[] array = s.split(" ");
        int a = array[0].charAt(0);

        return m.matches();
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Action getAction() {
        return action;
    }
}

