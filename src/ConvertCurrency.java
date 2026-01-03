import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertCurrency {
    public double convertFromUSD(String currency, double value){
        double rate = getRate(currency);
        BigDecimal result = BigDecimal.valueOf(value * rate)
                .setScale(2, RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    public double convertToUSD(String currency, double value){
        double rate = getRate(currency);
        BigDecimal result = BigDecimal.valueOf(value / rate)
                .setScale(2, RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    private double getRate(String currency) {
        try {
            MyCurrency exchangeRate = ExchangeRateAPI.getRates();
            return exchangeRate.conversion_rates().get(currency);
        } catch (Exception e) {
            System.out.println("Error getting exchange rate.");
            return 0;
        }
    }

}
