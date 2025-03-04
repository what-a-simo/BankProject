import java.util.Vector;
import java.io.FileReader;
import java.io.FileWriter;

public class Bank {
  private Vector<BankUser> bankUserRecord;
  private String name;

  public Bank() {
    this.bankUserRecord = new Vector<BankUser>();
    name = "Volkswagen";
  }

  public Bank(String name) {
    this.bankUserRecord = new Vector<BankUser>();
    this.name = name;
  }

  public Bank(Vector<BankUser> bankUserRecord, String name) {
    this.bankUserRecord = bankUserRecord;
    this.name = name;
  }

  public Vector<BankUser> getBankUserRecord() {
    return bankUserRecord;
  }

  public String getName() {
    return name;
  }

  public BankUser getBankUser(int index) {
    return bankUserRecord.elementAt(index);
  }

  public void addBankUser(BankUser userName) {
    bankUserRecord.add(userName);
  }
}
