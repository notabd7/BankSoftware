package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
	public static final double ACCOUNT_APR = 2.1;
	public static final int ACCOUNT_ID = 12345678;
	public static final String ACCOUNT_TYPE = "Savings";

	Account account;

	@BeforeEach
	public void setUp() {
		account = new SavingsAccount(ACCOUNT_APR, ACCOUNT_ID, ACCOUNT_TYPE);
	}

	@Test
	public void set_account_supplied_apr() {
		account.setApr(ACCOUNT_APR);
		double actual = account.getApr();

		assertEquals(2.1, actual);
	}

	@Test
	public void deposit_$200_in_account() {
		account.depositMoney(200);
		double actual = account.getBalance();

		assertEquals(200, actual);

	}

	@Test
	public void withdraw_$100_from_account() {
		account.depositMoney(200);
		account.withdrawMoney(100);
		double actual = account.getBalance();

		assertEquals(100, actual);

	}

	@Test
	public void when_withdrawing_balance_does_not_go_below_0() {
		account.depositMoney(200);
		account.withdrawMoney(300);

		assertEquals(0, account.getBalance());

	}

	@Test
	public void depositing_twice_into_the_same_account() {
		account.depositMoney(200);
		account.depositMoney(300);
		double actual = account.getBalance();

		assertEquals(500, actual);
	}

	@Test
	public void withdrawing_twice_from_the_same_account() {
		account.depositMoney(200);
		account.withdrawMoney(100);
		account.withdrawMoney(100);
		double actual = account.getBalance();

		assertEquals(0, actual);
	}

	@Test
	public void testAccountMutants1() {

		// Test Case 1: Negative balance (should be corrected to 0)
		account.setBalance(-100.0);
		assertEquals(0.0, account.getBalance());

	}

	@Test
	public void testAccountMutants2() {

		// Test Case N: Valid balance (should remain unchanged)
		account.setBalance(500.0);
		assertEquals(500.0, account.getBalance());
	}
}
