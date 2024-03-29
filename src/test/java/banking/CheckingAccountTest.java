package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingAccountTest {
	public static final double ACCOUNT_APR = 2.1;
	public static final int ACCOUNT_ID = 12345678;
	public static final String ACCOUNT_TYPE = "Checking";
	SavingsAccount checkingAccount;

	@BeforeEach
	public void setUp() {
		checkingAccount = new SavingsAccount(ACCOUNT_APR, ACCOUNT_ID, ACCOUNT_TYPE);
	}

	@Test
	public void checking_account_created_with_no_balance_by_default() {
		double actual = checkingAccount.getBalance();

		assertEquals(0, actual);

	}

}
