package ModuleSeven;

// Uses two threads
public class Threader
{
    public static void main(String[] args)
    {
	var worker = new Work();
	int[] dataArray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -1 };

	// Thread that only writes data to the worker.data state variable
	new Thread(new Runnable()
	{
	    public void run()
	    {
		for (int data : dataArray)
		{
		    worker.write(data);
		}
	    }
	}, "WriterThread").start();

	// Thread that reads when there is data in the worker.data state variable
	new Thread(new Runnable()
	{
	    public void run()
	    {
		worker.read();
	    }
	}, "ReaderThread").start();
    }

}

class Work
{
    private Integer data = null;

    synchronized void write(int data)
    {
	try
	{
	    if (this.data != null)
	    {
		// Wait for data to be empty
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
	boolean tryReadFlag = true;

	try
	{
	    while (tryReadFlag)
	    {
		if (data == null)
		{
		    // state variable Empty.
		    notify();
		    wait();
		}
		if ( data < 0) {
		    System.out.println("Delimiter encountered. " + Thread.currentThread().getName() + " Exiting.");
		    break;
		}

		System.out.println("Reading data with " + Thread.currentThread().getName());
		System.out.println(data);
		data = null;
		notify();

	    }    
	} catch (InterruptedException e)
	{
	}
	
    }
}
