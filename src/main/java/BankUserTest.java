import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankUserTest {
    private BankUser user;

    @BeforeEach
    void setUp() {
        user = new BankUser("TestUser");
        user.setBalance(100.0);
        user.setWallet(50.0);
    }

    @Test
    void testTakeMoneySuccess() {
        user.takeMoney(50.0);
        assertEquals(50.0, user.getBalance(), 0.001);
        assertEquals(100.0, user.getWallet(), 0.001);
    }

    @Test
    void testTakeMoneyInsufficientFunds() {
        user.takeMoney(150.0);
        assertEquals(100.0, user.getBalance(), 0.001);
        assertEquals(50.0, user.getWallet(), 0.001);
    }

    @Test
    void testTakeMoneyZeroBalance() {
        user.setBalance(0.0);
        user.takeMoney(10.0);
        assertEquals(0.0, user.getBalance(), 0.001);
        assertEquals(50.0, user.getWallet(), 0.001);
    }

    @Test
    void testTakeMoneyNegativeAmount() {
        user.takeMoney(-10.0);
        assertEquals(100.0, user.getBalance(), 0.001);
        assertEquals(50.0, user.getWallet(), 0.001);
    }

    @Test
    void testAddMoneySuccess() {
        user.addMoney(30.0);
        assertEquals(130.0, user.getBalance(), 0.001);
        assertEquals(20.0, user.getWallet(), 0.001);
    }

    @Test
    void testAddMoneyInsufficientWallet() {
        user.addMoney(60.0);
        assertEquals(100.0, user.getBalance(), 0.001);
        assertEquals(50.0, user.getWallet(), 0.001);
    }

    @Test
    void testAddMoneyNegativeAmount() {
        user.addMoney(-10.0);
        assertEquals(100.0, user.getBalance(), 0.001);
        assertEquals(50.0, user.getWallet(), 0.001);
    }

    @Test
    void testInvestmentSuccess() {
        user.investment(50.0, 's', 'l');
        assertTrue(user.getBalance() >= 50.0 * 0.7);
    }

    @Test
    void testInvestmentInsufficientFunds() {
        user.investment(200.0, 's', 'l');
        assertEquals(100.0, user.getBalance(), 0.001);
    }

    @Test
    void testInvestmentInvalidPeriod() {
        user.investment(50.0, 'k', 'l');
        assertEquals(100.0, user.getBalance(), 0.001);
    }

    @Test
    void testInvestmentInvalidRisk() {
        user.investment(50.0, 's', 'k');
        assertEquals(100.0, user.getBalance(), 0.001);
    }
}
