import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Reports {

    private static Map<String, Double> dailySales = new HashMap<>();
    private static Map<String, Double> monthlySales = new HashMap<>();

    public static void main(String[] args) {
        // Schedule the daily and monthly report generation
        scheduleDailyReport();
        scheduleMonthlyReport();
    }

    private static void generateDailyReport() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        String reportDate = currentDate.toString();

        // Get the total daily sales
        double totalDailySales = dailySales.getOrDefault(reportDate, 0.0);

        // Print or store the daily report as needed
        System.out.println("Daily Report - " + reportDate);
        System.out.println("Total Sales: $" + totalDailySales);

        // Reset daily sales for the next day
        dailySales.put(reportDate, 0.0);
    }

    private static void generateMonthlyReport() {
        // Get the current month
        Month currentMonth = LocalDate.now().getMonth();
        String reportMonth = currentMonth.toString();

        // Get the total monthly sales
        double totalMonthlySales = monthlySales.getOrDefault(reportMonth, 0.0);

        // Print or store the monthly report as needed
        System.out.println("Monthly Report - " + reportMonth);
        System.out.println("Total Sales: $" + totalMonthlySales);

        // Reset monthly sales for the next month
        monthlySales.put(reportMonth, 0.0);
    }

    private static void updateSales(List<Item> soldItems) {
        // Update daily and monthly sales based on the items sold
        for (Item item : soldItems) {
            String itemName = item.getItemname();
            double itemPrice = item.getPrice();

            // Update daily sales
            dailySales.put(itemName, dailySales.getOrDefault(itemName, 0.0) + itemPrice);

            // Update monthly sales
            String month = LocalDate.now().getMonth().toString();
            monthlySales.put(itemName, monthlySales.getOrDefault(itemName, 0.0) + itemPrice);
        }
    }

    static void scheduleDailyReport() {
        // Schedule the daily report generation at 9:00 PM
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Calculate the delay until 9:00 PM
        LocalTime targetTime = LocalTime.of(21, 0); // 9:00 PM
        LocalDateTime targetDateTime = LocalDateTime.of(LocalDate.now(), targetTime);
        long initialDelay = LocalDateTime.now().until(targetDateTime, ChronoUnit.SECONDS);

        // Schedule the daily report generation task
        scheduler.scheduleAtFixedRate(Reports::generateDailyReport, initialDelay, 24 * 60 * 60, TimeUnit.SECONDS);
    }

    static void scheduleMonthlyReport() {
        // Schedule the monthly report generation on the last day of each month at 9:00 PM
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Calculate the delay until the last day of the month at 9:00 PM
        LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        LocalTime targetTime = LocalTime.of(21, 0); // 9:00 PM
        LocalDateTime targetDateTime = LocalDateTime.of(lastDayOfMonth, targetTime);
        long initialDelay = LocalDateTime.now().until(targetDateTime, ChronoUnit.SECONDS);

        // Schedule the monthly report generation task
        scheduler.scheduleAtFixedRate(Reports::generateMonthlyReport, initialDelay, 24 * 60 * 60, TimeUnit.SECONDS);
    }
}
