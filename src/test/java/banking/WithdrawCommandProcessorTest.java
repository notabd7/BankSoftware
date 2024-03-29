package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandProcessorTest {

	private DepositCommandProcessor depositCommandProcessor;
	private Bank bank;
	private CreateCommandProcessor createCommandProcessor;
	private WithdrawCommandProcessor withdrawCommandProcessor;
	private TransferCommandProcessor transferCommandProcessor;
	private PassCommandProcessor passCommandProcessor;
	private CommandProcessor commandProcessor;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		depositCommandProcessor = new DepositCommandProcessor(bank, depositCommandProcessor, createCommandProcessor,
				withdrawCommandProcessor, transferCommandProcessor, passCommandProcessor);
		createCommandProcessor = new CreateCommandProcessor(bank, depositCommandProcessor, createCommandProcessor,
				withdrawCommandProcessor, transferCommandProcessor, passCommandProcessor);
		withdrawCommandProcessor = new WithdrawCommandProcessor(bank, depositCommandProcessor, createCommandProcessor,
				withdrawCommandProcessor, transferCommandProcessor, passCommandProcessor);
		transferCommandProcessor = new TransferCommandProcessor(bank, depositCommandProcessor, createCommandProcessor,
				withdrawCommandProcessor, transferCommandProcessor, passCommandProcessor);
		passCommandProcessor = new PassCommandProcessor(bank, depositCommandProcessor, createCommandProcessor,
				withdrawCommandProcessor, transferCommandProcessor, passCommandProcessor);
		commandProcessor = new CommandProcessor(bank, depositCommandProcessor, createCommandProcessor,
				withdrawCommandProcessor, transferCommandProcessor, passCommandProcessor);
	}

	@Test
	void withdraw_200_from_checking_account_with_300_balance() {
		bank.addAccount("CHECKING", 12345678, 9.5, 0);
		bank.depositMoneyFromBank(12345678, 300);
		commandProcessor.commandParser("withdraw 12345678 200");
		assertEquals(100, bank.getBalance(12345678));
	}

	@Test
	void withdraw_200_from_savings_account_with_300_balance() {
		bank.addAccount("SAVINGS", 12345678, 9.5, 0);
		bank.depositMoneyFromBank(12345678, 300);
		commandProcessor.commandParser("withdraw 12345678 200");
		assertEquals(100, bank.getBalance(12345678));
	}

	@Test
	void withdrawing_0_is_valid() {
		bank.addAccount("CHECKING", 12345678, 9.5, 0);
		bank.depositMoneyFromBank(12345678, 300);
		commandProcessor.commandParser("withdraw 12345678 0");
		assertEquals(300, bank.getBalance(12345678));
	}

	@Test
	void withdraw_200_from_account_with_0_balance_should_leave_balance_0() {
		bank.addAccount("CHECKING", 12345678, 9.5, 0);
		bank.depositMoneyFromBank(12345678, 0);
		commandProcessor.commandParser("withdraw 12345678 200");
		assertEquals(0, bank.getBalance(12345678));
	}

	@Test
	void withdraw_200_from_account_with_100_balance_should_leave_balance_0() {
		bank.addAccount("CHECKING", 12345678, 9.5, 0);
		bank.depositMoneyFromBank(12345678, 100);
		commandProcessor.commandParser("withdraw 12345678 200");
		assertEquals(0, bank.getBalance(12345678));
	}

}
