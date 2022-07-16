package co.com.sofka.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StringCalculator {
    private String delimiter = "[^\\d|^-]";

    public Integer calculateAdditionFromString(String toCalculate) {
        List<Integer> numbersToAdd = this.getNumberList(toCalculate);

        return numbersToAdd.stream()
                .filter(this.numberLessThan1000())
                .reduce(this::sumNumbers)
                .orElseThrow(() -> new NumberFormatException("The numbers couldn't be negative"));
    }

    private List<Integer> getNumberList(String numbers) {
        numbers = this.establishNewDelimiters(numbers);

        return !numbers.isEmpty() ?Arrays.stream(numbers.split(delimiter))
                .filter(Predicate.not(String::isEmpty))
                .map(Integer::parseInt)
                .toList() : List.of(0);
    }

    private Integer sumNumbers (Integer number1, Integer number2) {
        return Stream.of(number1, number2).anyMatch(this.isLessThanZero()) ?
                Integer.sum(Integer.parseInt(""), number2) :
                Integer.sum(number1, number2);
    }

    private Predicate<Integer> isLessThanZero() {
        return number -> number < 0;
    }

    private Predicate<Integer> numberLessThan1000() {
        return number -> number <= 1000;
    }

    private String establishNewDelimiters(String numbers) {
        if (!numbers.matches("(^\\d+|^-)\n?.*")) {
            delimiter = "[^\\d|^-]";

            String delimiters = numbers.split("\\d")[0];
            List<String> newDelimiters = List.of(delimiters.replaceFirst("\\[", "")
                    .replace("[", " ")
                    .replace("]", "")
                    .split(" "));

            delimiter = delimiter.replace("[^", "^\\")
                    .replace("[", "");
            newDelimiters.forEach(newDelimiter -> delimiter = newDelimiter + "|" + delimiter);
            delimiter = "[".concat(delimiter);

            return numbers.substring(delimiters.length());
        }

        return numbers;
    }
}
