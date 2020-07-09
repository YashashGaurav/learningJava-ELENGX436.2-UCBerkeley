package ModuleSeven;

// Uses multiple threads
public class Threader3
{
    public static void main(String[] args)
    {
	var worker = new Work3();
	int[] dataArray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	// Thread that only writes data to the worker.data state variable
	new Thread(new Runnable()
	{
	    public void run()
	    {
		worker.write(dataArray);
	    }
	}).start();

	// Thread that reads when there is data in the worker.data state variable
	new Thread(new Runnable()
	{
	    public void run()
	    {
		worker.read();
	    }
	}).start();
    }

}

class Work3
{
    private Integer data = null;

    synchronized void write(int[] dataArray)
    {
	int currentWriteIndex = 0;

	try
	{
	    while (currentWriteIndex < dataArray.length)
	    {
		if (this.data != null)
		{
		    System.out.println("Waiting data to be empty");
		    notify();
		    wait();
		} else
		{
		    System.out.println("Writing data with " + Thread.currentThread().getName());
		    this.data = dataArray[currentWriteIndex];
		    currentWriteIndex++;
		}

	    }
	    notify();
	} catch (InterruptedException e)
	{
	}

    }

    synchronized void read()
    {
	boolean tryReadFlag = true;
	boolean dataEmptyOnLastTry = false;
	try
	{
	    while (tryReadFlag)
	    {
		if (data == null && !dataEmptyOnLastTry)
		{
		    System.out.println("Data Empty.");
		    dataEmptyOnLastTry = true;
		    notify();
		    wait();
		    
		} else if (data == null && dataEmptyOnLastTry) {
		    tryReadFlag = false;
		} else
		{
		    System.out.println("Reading data with " + Thread.currentThread().getName());
		    System.out.println(data);
		    data = null;
		    dataEmptyOnLastTry = false;
		}
	    }
	} catch (InterruptedException e)
	{
	}
    }
}
