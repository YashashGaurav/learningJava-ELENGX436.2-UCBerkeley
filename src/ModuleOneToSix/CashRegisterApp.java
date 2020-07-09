package ModuleOneToSix;

public class CashRegisterApp {

	public static void main(String[] args) {
		
		// Register 1 operations
		CashRegister register1 = new CashRegister();
		System.out.println("Register1 created with total: " + register1.Total());
			// Add transactions and test totals
		register1.AddTransaction(100);
		System.out.println("Register1 + 100, total: " + register1.Total());
		register1.AddTransaction(200.00);
		System.out.println("Register1 + 200.00, total: " + register1.Total());
			// Clear transactions and test output
		register1.ResetTransactions();
		System.out.println("Register1 reset, total: " + register1.Total());
			// Show Register Count
		System.out.println("Current register count: " + CashRegister.RegisterCount());
		
		System.out.println("-------------------------");
		
		// Register 2 operations
		CashRegister register2 = new CashRegister();
		System.out.println("Register2 created with total: " + register2.Total());
			// Add transactions and test totals
		register2.AddTransaction(255);
		System.out.println("Register2 + 255, total: " + register2.Total());
		register2.AddTransaction(1729);
		System.out.println("Register2 + 1729, total: " + register2.Total());
		register2.AddTransaction(341);
		System.out.println("Register2 + 341, total: " + register2.Total());
			// Show number of transactions
		System.out.println("Total number of transactions in Register2: " + register2.TransactionCount());
			// Clear transactions and test output
		register2.ResetTransactions();
		System.out.println("Register2 reset, total: " + register2.Total());
			// Show Register Count
		System.out.println("Current register count: " + CashRegister.RegisterCount());
		
	}

}


class CashRegister {
	
	static int registerCount;
	
	static {
		registerCount = 0;
	}
	
	private double _runningTotal;
	private int _transactionCount;
	
	
	CashRegister() {
		this._runningTotal = 0;
		this._transactionCount = 0;
		// Increment cash register count
		registerCount++;
	}
	
	public void AddTransaction(double amount) {
		this._transactionCount++;
		this._runningTotal += amount;
	}
	
	public void AddTransaction(int amount) {
		this._transactionCount++;
		this._runningTotal += (double)amount;
	}
	
	public int TransactionCount() {
		return this._transactionCount;
	}
	
	public double Total() {
		return this._runningTotal;
	}
	
	public void ResetTransactions() {
		this._runningTotal = 0.0;
		this._transactionCount = 0;
	}
	
	public static int RegisterCount() {
		return registerCount;
	}
	
}