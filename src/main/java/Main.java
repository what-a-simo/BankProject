import java.util.Scanner;
import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    Bank B = new Bank();
    int accountIndex = 0;

    System.out.println(B.getName() + " Group");
    welcomeMessage();

    Scanner input = new Scanner(System.in);

    char opt = input.next().charAt(0);

    if (opt == 'n') {
      System.out.println("Type your full name [Surname Name]: ");
      String name = input.next();
      BankUser user = new BankUser(name);

      B.addBankUser(user);
      accountIndex = B.getBankUserRecord().size() - 1;
    }

    System.out.print("Welcome ");
    System.out.println(B.getBankUser(accountIndex).getUserName());

    do {
      userInterface();
      opt = input.next().charAt(0);

      switch (opt) {
        case 's':
          B.getBankUser(accountIndex).status();
          break;

        case 'd':
          System.out.println("Type the desired import");
          int m1 = input.nextInt();
          B.getBankUser(accountIndex).addMoney(m1);
          break;

        case 'w':
          System.out.println("Type the desired import");
          int m2 = input.nextInt();
          B.getBankUser(accountIndex).takeMoney(m2);
          break;

        case 'i':
          System.out.println("Type the desired import");
          int a = input.nextInt();

          System.out.println("Type the desired period");
          System.out.println("Type 's' for short period investment (30 days)");
          System.out.println("Type 'm' for medium period investment (180 days)");
          System.out.println("Type 'l' for long period investment (360 days)");
          System.out.print("Option: ");
          char p = input.next().charAt(0);

          System.out.println("Type the desired risk:");
          System.out.println("Type 'l' for low risk investment (up to +100%)");
          System.out.println("Type 'm' for medium risk investment (up to +500%)");
          System.out.println("Type 'h' for high risk investment (up to +1000%)");
          System.out.print("Option: ");
          char r = input.next().charAt(0);

          B.getBankUser(accountIndex).investment(a, p, r);
          break;

        case 't':
          System.out.println("How many days would you like to spend?");
          int t = input.nextInt();

          B.getBankUser(accountIndex).moveForward(t);
          break;

        case 'e':
          System.out.println("Thanks for relying on us");
          System.out.println(B.getName() + " Group");
          break;

        default:
          System.out.println("Invalid option");
          break;
      }
    } while (opt != 'e');
  }

  static void welcomeMessage() {
    System.out.println("***********************************************************");
    System.out.println("WELCOME TO THE BANK PORTAL");
    System.out.println("New Customer? Type 'n' to create a new Account.");
    // System.out.println("Already a Customer? Type 'a' to login.");
    System.out.println("***********************************************************");
    System.out.println("Option: ");
  }

  static void userInterface() {
    System.out.println("***********************************************************");
    System.out.println("Choose the desired option:");
    System.out.println("Type 's' to view your current balance.");
    System.out.println("Type 'd' to deposit founds.");
    System.out.println("Type 'w' to withdraw founds.");
    System.out.println("Type 'i' to invest founds.");
    System.out.println("Type 't' to move time forward.");
    // System.out.println("Type 'l' to log out.");
    System.out.println("Type 'e' to exit.");
    System.out.println("***********************************************************");
    System.out.println("Option: ");

//    JFrame frame = new JFrame();
//    frame.setBounds(0,0,1920,1080);
//    frame.setVisible(true);
  }
}
