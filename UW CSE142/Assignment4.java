import java.util.*;

public class Budgeter {
    public static final int DAYS = 31;

    public static void main(String[] args) {
        giveIntro();
        Scanner console = new Scanner(System.in);
        double income = getMoney(console, "income");
        double expense = getMoney(console, "expense");
        reportMoney(income, "income");
        reportMoney(expense, "expenses");
        reportBudget(income, expense);
    }

    public static void giveIntro() {
        System.out.println("This program asks for your monthly income and\n" +
                "expenses, then tells you your net monthly income.");
        System.out.println();
    }

    public static double getMoney(Scanner console, String moneyType) {
        int monthDay = 0;
        if (moneyType.equals("expense")) {
            System.out.print("Enter 1) monthly or 2) daily expenses? ");
            monthDay = console.nextInt();
        }
        System.out.print("How many categories of " + moneyType + "? ");
        double categories = console.nextInt();
        double total = 0;
        for (int i = 0; i < categories; i++) {
            System.out.print("\tNext " + moneyType + " amount? $");
            total += console.nextDouble();
        }
        System.out.println();
        if (monthDay == 2) {
            total *= DAYS;
        }
        return total;
    }

    public static void reportMoney(double money, String type) {
        System.out.printf("Total " + type + " = $%.2f ($%.2f/day)", money, money / DAYS);
        System.out.println();
    }

    public static void reportBudget(double income, double expense) {
        System.out.println();
        double budgetResult = income - expense;
        if (budgetResult > 0) {
            System.out.printf("You earned $%.2f more than you spent this month.", budgetResult);
        } else {
            System.out.printf("You spent $%.2f more than you earned this month.", budgetResult);
        }
        System.out.println();
        String saverSpender;
        String customMessage;
        if (budgetResult > 250) {
            saverSpender = "big saver";
            customMessage = "Decent I guess.";
        } else if (budgetResult > 0 && budgetResult <= 250) {
            saverSpender = "saver";
            customMessage = "Oof, getting down there.";
        } else if (budgetResult > -250 && budgetResult >= 0) {
            saverSpender = "spender";
            customMessage = "Whoa there, spend less!";
        } else {
            saverSpender = "big spender";
            customMessage = "Ya, you're in debt.";
        }
        System.out.println("You're a " + saverSpender + ".");
        System.out.print(customMessage);
    }
}

