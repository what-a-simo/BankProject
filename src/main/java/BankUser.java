import java.util.Random;
import java.io.FileWriter;

public class BankUser {
  private String ID;
  private String userName;
  private double balance;
  private double wallet;
  private int timeSpent;
  static int nUser = 0;

  public BankUser(String userName) {
    Random rand = new Random();
    this.ID = Integer.toString(rand.nextInt(1000, 9999));
    this.userName = userName;
    this.balance = rand.nextInt(1000, 10000);
    this.wallet = 0;
    this.timeSpent = 0;
    nUser++;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void setWallet(double wallet) {
    this.wallet = wallet;
  }

  public String getID() {
    return ID;
  }

  public int getnUser() { return nUser; }

  public double getBalance() {
    return balance;
  }

  public double getWallet() {
    return wallet;
  }

  public int getTimeSpent() {
    return timeSpent;
  }

  public String getUserName() {
    return userName;
  }

  public void takeMoney(double m) {
    if (m > balance || balance <= 0 || m < 0) {
      System.out.println("Insufficient founds. The import could not be withdrew.");
    } else {
      balance -= m;
      wallet += m;
    }
    try {
      FileWriter writer = new FileWriter(userName + ".txt", true);
      writer.write("takeMoney;" + m + "\n");
      writer.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void addMoney(double m) {
    if (m > wallet || m < 0) {
      System.out.println("Insufficient founds. The import could not be deposited.");
    } else {
      balance += m;
      wallet -= m;
    }
    try {
      FileWriter writer = new FileWriter(userName + ".txt", true);
      writer.write("addMoney;" + m + "\n");
      writer.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void status() {
    System.out.println("Name: " + userName);
    System.out.println("Available balance: " + balance + "€");
    System.out.println("Available wallet: " + wallet + "€");
    System.out.println("Time spent: " + timeSpent);
  }

  public void monthBonus() {
    wallet += 100;
    System.out.println("\nA month has passed, 100€ have been deposited in your wallet.");
    try {
      FileWriter writer = new FileWriter(userName + ".txt", true);
      writer.write("monthBonus;" + 100 + "\n");
      writer.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void moveForward(int t) {
    timeSpent += t;
    for (int i = 0; i < t / 30; i++) {
      this.monthBonus();
    }
  }

  public void investment(double initialAmount, char period, char risk) {
    Random rand = new Random();
    if (initialAmount > balance || balance <= 0) {
      System.out.println("Insufficient founds");
      return;
    }
    balance -= initialAmount;
    int time = 0;
    if (period == 's') {
      time = 30;
    } else if (period == 'm') {
      time = 180;
    } else if (period == 'l') {
      time = 360;
    } else {
      System.out.println("Invalid period.");
      balance += initialAmount;
      return;
    }
    double multiplier = 0;
    if (risk == 'l') {
      multiplier = initialAmount + initialAmount * 2;
    } else if (risk == 'm') {
      multiplier = initialAmount + initialAmount * 5;
    } else if (risk == 'h') {
      multiplier = initialAmount + initialAmount * 10;
    } else {
      System.out.println("Invalid risk.");
      balance += initialAmount;
      return;
    }
    double finalAmount = initialAmount;
    timeSpent += time;
    for (int i = 1; i <= time; i++) {
      finalAmount = rand.nextInt(1000000) % multiplier;
      if (rand.nextInt(1000000) % 2 != 0) {
        finalAmount *= -1;
      }
      if (i % 30 == 0) {
        this.monthBonus();
      }
    }
    if (risk == 'l' && (finalAmount < 0.7 * initialAmount)) {
      finalAmount = 0.7 * initialAmount;
    } else if (risk == 'm' && (finalAmount < 0.4 * initialAmount)) {
      finalAmount = 0.4 * initialAmount;
    }
    System.out.print("Initial amount invested: " + initialAmount + "€");
    System.out.print("Final amount: " + finalAmount + "€");
    if (finalAmount > initialAmount) {
      System.out.print("Earned amount: " + (finalAmount - initialAmount) + "€");
    } else if (finalAmount < initialAmount) {
      System.out.print("Lost amount: " + (initialAmount - finalAmount) + "€");
    } else {
      System.out.println("No amount earned");
    }
    balance += finalAmount;
    try {
      FileWriter writer = new FileWriter(userName + ".txt", true);
      writer.write("investment;" + finalAmount + "\n");
      writer.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
