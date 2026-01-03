import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    ConvertCurrency convert = new ConvertCurrency();
    Scanner scanner = new Scanner(System.in);
    List<ConversionRecord> history = new ArrayList<>();

    public void start() {
        int option;

        do {
            System.out.println("""
                    *********************************************************
                    Welcome to Currency Exchange App =]
                    
                    1. Dollar → Argentine pesos
                    2. Argentine pesos → Dollar
                    3. Dollar → Brazilian real
                    4. Brazilian real → Dollar
                    5. Dollar → Colombian pesos
                    6. Colombian pesos → Dollar
                    7. Show conversion record
                    8. Exit
                    Select a valid option:
                    *********************************************************
                    """);
            option = scanner.nextInt();

            if (option == 8) break;
            if (option == 7){
                showHistory();
                continue;
            };

            System.out.print("Enter the amount: ");
            double value = scanner.nextDouble();

            double result = exchange(option, value);
            String currencyName = getCurrencyName(option);
            System.out.printf("Result: %.2f %s%n", result, currencyName );

            String fromCurrency = getFromCurrency(option);
            String toCurrency = getToCurrency(option);

            history.add(new ConversionRecord(
                    value, fromCurrency,
                    result, toCurrency
            ));

        } while (true);
    }

    double exchange(int option, double value) {
        return switch (option) {
            case 1 -> convert.convertFromUSD("ARS", value);
            case 2 -> convert.convertToUSD("ARS", value);
            case 3 -> convert.convertFromUSD("BRL", value);
            case 4 -> convert.convertToUSD("BRL", value);
            case 5 -> convert.convertFromUSD("COP", value);
            case 6 -> convert.convertToUSD("COP", value);
            default -> 0;
        };
    }

    private String getCurrencyName(int option) {
        return switch (option) {
            case 1 -> "argentine pesos";
            case 2, 4, 6 -> "american dollars";
            case 3 -> "brazilian reals";
            case 5 -> "colombian pesos";
            default -> "";
        };
    }

    private String getFromCurrency(int option) {
        return switch (option) {
            case 1, 3, 5 -> "USD";
            case 2 -> "ARS";
            case 4 -> "BRL";
            case 6 -> "COP";
            default -> "";
        };
    }

    private String getToCurrency(int option) {
        return switch (option) {
            case 1 -> "ARS";
            case 2, 4, 6 -> "USD";
            case 3 -> "BRL";
            case 5 -> "COP";
            default -> "";
        };
    }

    private void showHistory() {
        if (history.isEmpty()) {
            System.out.println("There are no conversions recorded.");
            return;
        }

        System.out.println("----- Conversion Records -----");
        history.forEach(System.out::println);
    }



}
