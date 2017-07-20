package polymorphism;

public class Oracle implements DBMS {

	@Override
	public void start() {
		System.out.println("Start Oracle.");
	}

	@Override
	public void connect() {
		System.out.println("Connect to Oracle.");

	}

	@Override
	public void close() {
		System.out.println("Close from Oracle.");

	}

	@Override
	public void shutdown() {
		System.out.println("Shutdown Oracle");

	}

}
