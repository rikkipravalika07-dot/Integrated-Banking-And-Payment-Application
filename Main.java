import java.util.*;

// Bank ADT (CO2)
class Bank {
    String name;
    double home, car, personal, education;

    Bank(String name, double home, double car, double personal, double education) {
        this.name = name;
        this.home = home;
        this.car = car;
        this.personal = personal;
        this.education = education;
    }
}

public class Main {

    // CO2 LinkedList
    static LinkedList<Bank> banks = new LinkedList<>();

    // CO4 HashMap
    static HashMap<String, String> users = new HashMap<>();

    // CO3 Queue
    static Queue<String> chatHistory = new LinkedList<>();

    // CO3 Stack
    static Stack<Double> stack = new Stack<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        initializeData();

        System.out.println("===== Advanced Multi Banking System =====");

        if (login()) {
            dashboard();
        } else {
            System.out.println("Invalid Login");
        }
    }

    // Initialize data
    static void initializeData() {

        // Login Credentials
        users.put("2520030402", "12345");

        banks.add(new Bank("SBI", 8.5, 9.1, 11.2, 8.0));
        banks.add(new Bank("HDFC", 8.7, 9.5, 12.0, 9.0));
        banks.add(new Bank("ICICI", 8.9, 9.3, 11.8, 9.2));
        banks.add(new Bank("Axis", 9.0, 9.7, 12.5, 9.5));
        banks.add(new Bank("PNB", 8.4, 9.2, 11.0, 8.5));
    }

    // Login system
    static boolean login() {

        System.out.print("Enter Login ID: ");
        String user = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        return users.containsKey(user) && users.get(user).equals(pass);
    }

    // Dashboard menu
    static void dashboard() {

        while (true) {

            System.out.println("\n---- Dashboard ----");
            System.out.println("1. Loan Analysis");
            System.out.println("2. Calculator");
            System.out.println("3. Currency Converter");
            System.out.println("4. Chatbot");
            System.out.println("5. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    analyzeLoan();
                    break;

                case 2:
                    calculator();
                    break;

                case 3:
                    currencyConverter();
                    break;

                case 4:
                    chatbot();
                    break;

                case 5:
                    System.out.println("Thank you for using Banking System");
                    return;

                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    // CO1 Loan EMI analysis
    static void analyzeLoan() {

        System.out.print("Enter Loan Amount: ");
        double amount = sc.nextDouble();

        System.out.print("Enter Tenure (Years): ");
        int years = sc.nextInt();

        int months = years * 12;

        double lowestEMI = Double.MAX_VALUE;
        String bestBank = "";

        System.out.println("\nBank EMI Comparison:");

        for (Bank b : banks) {

            double R = b.home / 12 / 100;

            double EMI = (amount * R * Math.pow(1 + R, months))
                    / (Math.pow(1 + R, months) - 1);

            System.out.println(b.name + " EMI: ₹" + (int) EMI);

            if (EMI < lowestEMI) {
                lowestEMI = EMI;
                bestBank = b.name;
            }
        }

        System.out.println("\nRecommended Bank: " + bestBank);
        System.out.println("Lowest EMI: ₹" + (int) lowestEMI);
    }

    // CO3 Stack Calculator
    static void calculator() {

        System.out.println("Enter numbers separated by space to add:");

        String line = sc.nextLine();

        String[] nums = line.split(" ");

        for (String n : nums) {
            stack.push(Double.parseDouble(n));
        }

        double sum = 0;

        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        System.out.println("Result: " + sum);
    }

    // CO4 Currency Converter
    static void currencyConverter() {

        HashMap<String, Double> rates = new HashMap<>();

        rates.put("USD", 83.0);
        rates.put("EUR", 90.0);

        System.out.print("Enter INR Amount: ");
        double inr = sc.nextDouble();

        System.out.println("USD: " + (inr / rates.get("USD")));
        System.out.println("EUR: " + (inr / rates.get("EUR")));
    }

    // CO3 Queue Chatbot
    static void chatbot() {

        System.out.print("Ask about loans: ");
        String q = sc.nextLine().toLowerCase();

        chatHistory.add(q);

        if (q.contains("home")) {
            System.out.println("SBI offers lowest home loan interest.");
        } else if (q.contains("car")) {
            System.out.println("ICICI offers good car loan interest.");
        } else if (q.contains("personal")) {
            System.out.println("PNB offers lowest personal loan interest.");
        } else {
            System.out.println("Try asking about home, car or personal loan.");
        }
    }
}