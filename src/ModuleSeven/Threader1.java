package ModuleSeven;

public class Threader1
{

    public static void main(String[] args)
    {
	Thread worker1 = new Thread(new Work1(), "worker_1");
//	Thread worker2 = new Thread(new Work(), "worker_2");
	worker1.start();
//	worker2.start();
    }

}

class Work1 implements Runnable
{
    private Integer data = null;

    synchronized void insert(int data)
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
	System.out.println(Thread.currentThread().getName());
	this.data = data;
	notify();
    }

    synchronized int read()
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

	System.out.println(Thread.currentThread().getId());
	var temp = data;
	data = null;
	notify();
	return temp;
    }

    public void run()
    {
	for (int count = 1; count <= 10; count++)
	{
	    insert(count);
	    System.out.println(read());
	}
    }
}