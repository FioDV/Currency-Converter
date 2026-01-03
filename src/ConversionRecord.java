import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversionRecord {

    private final double originalAmount;
    private final String fromCurrency;
    private final double convertedAmount;
    private final String toCurrency;
    private final LocalDateTime timestamp;

    public ConversionRecord(double originalAmount, String fromCurrency,
                            double convertedAmount, String toCurrency) {
        this.originalAmount = originalAmount;
        this.fromCurrency = fromCurrency;
        this.convertedAmount = convertedAmount;
        this.toCurrency = toCurrency;
        this.timestamp = LocalDateTime.now();
    }

    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return String.format(
                "%.2f %s → %.2f %s | %s",
                originalAmount, fromCurrency,
                convertedAmount, toCurrency,
                timestamp.format(formatter)
        );
    }
}

