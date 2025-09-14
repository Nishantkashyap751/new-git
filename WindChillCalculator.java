public class WindChillCalculator {

    public double calculateWindChill(double temperature, double windSpeed) {
        double windChill = 35.74 + 0.6215 * temperature + (0.4275 * temperature - 35.75) * Math.pow(windSpeed, 0.16);
        return windChill;
    }

    public static void main(String[] args) {
        WindChillCalculator calculator = new WindChillCalculator();

        double temperature = 30.0; 
        double windSpeed = 10.0;   

        double windChill = calculator.calculateWindChill(temperature, windSpeed);
        System.out.printf("The wind chill temperature is: %.2f°F\n", windChill);
    }
}
