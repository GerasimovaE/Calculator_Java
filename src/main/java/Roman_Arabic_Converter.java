import java.util.List;

public class Roman_Arabic_Converter {

    public static String ArabicToRoman(int result){

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

        if (input.length() > 1) {
            throw new CalculatorExeption(input + " cannot be converted to a Roman");
        }

        int result = 0;

        List romanNumerals = Roman.getReverseSortedValues();

        int i = 0;

        while ((input.length() > 0) && (i < romanNumerals.size())) {
            Roman symbol = (Roman)romanNumerals.get(i);
            if (input.startsWith(symbol.name())) {
                result += symbol.getValue();
                input = input.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        return result;
    }

}
