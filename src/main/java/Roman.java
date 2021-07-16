import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Roman {

    Ⅰ(1),
    Ⅱ(2),
    Ⅲ(3),
    Ⅳ(4),
    Ⅴ(5),
    Ⅵ(6),
    Ⅶ(7),
    Ⅷ(8),
    Ⅸ(9),
    Ⅹ(10);

    private final int value;

    Roman(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((Roman e) -> e.value).reversed())
                .collect(Collectors.toList());
    }

}
