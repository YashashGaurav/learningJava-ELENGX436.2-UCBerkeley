package ModuleSeven;

// Uses multiple threads
public class Threader2
{
    public static void main(String[] args)
    {
	var worker = new Work2();

	for (int count = 1; count <= 10; count++)
	{
	    worker.writeThreader(count);
	    worker.readThreader();
	}
    }

}

class Work2
{
    private Integer data = null;

    synchronized void write(int data)
    {

	try
	{
	    while (this.data != null)
	    {
		notify();
		wait();
	    }
	} catch (InterruptedException e)
	{
	}
	System.out.println("Writing data with " + Thread.currentThread().getName());
	this.data = data;
	notify();
    }

    synchronized void read()
    {
	try
	{
	    while (data == null)
	    {
		System.out.println("Data Empty.");
		notify();
		wait();
	    }
	} catch (InterruptedException e)
	{
	}

	System.out.println("Reading data with " + Thread.currentThread().getName());
	System.out.println(data);
	data = null;
	notify();

    }

    public void readThreader()
    {
	new Thread(new Runnable()
	{
	    public void run()
	    {
		read();
	    }
	}).start();
    }

    public void writeThreader(int data)
    {
	new Thread(new Runnable()
	{
	    public void run()
	    {
		write(data);
	    }
	}).start();
    }
}
