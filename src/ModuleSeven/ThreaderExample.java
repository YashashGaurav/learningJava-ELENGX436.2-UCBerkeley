package ModuleSeven;

import java.util.concurrent.ThreadLocalRandom;

public class ThreaderExample
{

    public static void main(String[] args)
    {
	Data data = new Data();
	Thread sender = new Thread(new Sender(data));
	Thread receiver = new Thread(new Receiver(data));

	sender.start();
	receiver.start();
    }
}

class Data
{
    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;

    public synchronized void send(String packet)
    {
	while (!transfer)
	{
	    try
	    {
		wait();
	    } catch (InterruptedException e)
	    {
		Thread.currentThread().interrupt();
//                Log.error("Thread interrupted", e); 
	    }
	}
	transfer = false;

	this.packet = packet;
	notifyAll();
    }

    public synchronized String receive()
    {
	while (transfer)
	{
	    try
	    {
		wait();
	    } catch (InterruptedException e)
	    {
		Thread.currentThread().interrupt();
//                Log.error("Thread interrupted", e); 
	    }
	}
	transfer = true;

	notifyAll();
	return packet;
    }
}

class Sender implements Runnable
{
    private Data data;

    public Sender(Data data)
    {
	this.data = data;
    }

    // standard constructors

    public void run()
    {
	String packets[] = { "First packet", "Second packet", "Third packet", "Fourth packet", "End" };

	for (String packet : packets)
	{
	    data.send(packet);

	    // Thread.sleep() to mimic heavy server-side processing
	    try
	    {
		Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
	    } catch (InterruptedException e)
	    {
		Thread.currentThread().interrupt();
//                Log.error("Thread interrupted", e); 
	    }
	}
    }
}

class Receiver implements Runnable
{
    private Data load;

    public Receiver(Data data)
    {
	this.load = data;
    }

    public void run()
    {
	for (String receivedMessage = load.receive(); !"End".equals(receivedMessage); receivedMessage = load.receive())
	{

	    System.out.println(receivedMessage);

	    // ...
	    try
	    {
		Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
	    } catch (InterruptedException e)
	    {
		Thread.currentThread().interrupt();
//                Log.error("Thread interrupted", e); 
	    }
	}
    }
}