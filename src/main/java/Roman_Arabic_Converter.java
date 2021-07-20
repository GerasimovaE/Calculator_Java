import java.util.List;

public class Roman_Arabic_Converter {

    public static String ArabicToRoman(int result) throws CalculatorExeption {

        int resultCopy = result;
        List romanNumerals = Roman.getReverseSortedValues();
        int i = 0;
        StringBuilder sb = new StringBuilder();

        while (i < romanNumerals.size()) {
            Roman currentSymbol = (Roman) romanNumerals.get(i);
            if (currentSymbol.getValue() <= resultCopy) {
                sb.append(currentSymbol.name());
                resultCopy -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static int RomanToArabic(String input) throws CalculatorExeption {

        int result = 0;

        try {
            result = Roman.valueOf(input).getValue();
        } catch (Exception e){
            throw new CalculatorExeption(input + " cannot be converted to a Roman");
        }

        return result;
    }

}
