import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

public class Main {
  public static void main(String[] args) {
    Bank B = new Bank();
    int accountIndex = 0;
    String name = "", psw;
    BankUser generalUser = null;

    System.out.println(B.getName() + " Group");
    welcomeMessage();

    Scanner input = new Scanner(System.in);

    char opt = input.next().charAt(0);

    if (opt == 'n') {
      System.out.println("Type your full name [SurnameName]: ");
      name = input.next();
      BankUser user = new BankUser(name);
      System.out.println("Type your password: ");
      psw = input.next();
      System.out.println();
      try {
        FileWriter writer = new FileWriter("account.txt", true);
        FileWriter writer1 = new FileWriter(name + ".txt", true);
        writer1.write(user.getBalance() + ";" + user.getWallet() + ";" + user.getTimeSpent());
        writer1.close();
        writer.write(name + ";" + psw + "\n");
        writer.close();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      B.addBankUser(user);
      System.out.println("The account has been created");
      accountIndex = B.getBankUserRecord().size() - 1;
      System.out.println("Welcome " + B.getBankUser(accountIndex).getUserName());
      generalUser = user;
    }
    else if (opt == 'l') {
      boolean check = true;
      while (check){
        System.out.println("Type your full name: ");
        name = input.next();
        System.out.println("Type your password: ");
        psw = input.next();
        try (BufferedReader br = new BufferedReader(new FileReader("account.txt"))) {
          String line;
          while ((line = br.readLine()) != null) {
            String nameBuffer = line.substring(0,line.indexOf(";"));
            String pswBuffer = line.substring(line.indexOf(";")+1);
            if (nameBuffer.equals(name) && pswBuffer.equals(psw)){
              System.out.println("Welcome " + name);
              BankUser user = new BankUser(name);
              B.addBankUser(user);
              try (BufferedReader userFile = new BufferedReader(new FileReader(name + ".txt"))) {
                String lineUserFile;
                lineUserFile = userFile.readLine();
                String[] values = lineUserFile.split(";");
                B.getBankUser(0).setBalance(Double.parseDouble(values[0]));
                B.getBankUser(0).setWallet(Double.parseDouble(values[1]));
                B.getBankUser(0).setID(values[2]);
                generalUser = user;
                userFile.close();
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
              check = false;
              break;
            }
          }
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
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
          String newContent = generalUser.getBalance() + ";" + generalUser.getWallet() + ";" + generalUser.getID();
          try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(name + ".txt"));
            String line;
            while ((line = reader.readLine()) != null) {
              lines.add(line);
            }
            reader.close();

            lines.set(0, newContent);

            BufferedWriter writer = new BufferedWriter(new FileWriter(name + ".txt"));
            for (String l : lines) {
              writer.write(l);
              writer.newLine();
            }
            writer.close();
            System.out.println("File updated successfully.");
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
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
    System.out.println("Already a Customer? Type 'l' to login.");
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
    System.out.println("Type 'e' to exit.");
    System.out.println("***********************************************************");
    System.out.println("Option: ");
  }
}
