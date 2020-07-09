package ModuleSeven;

public class InflatableBalloon
{

    private final int MAX_PRESSURE = 10;
    private int currentPressure;

    public InflatableBalloon()
    {
	this.currentPressure = 0;
    }

    public void inflate() throws BalloonBurstException
    {
	if (getCurrentPressure() >= MAX_PRESSURE)
	{
	    throw new BalloonBurstException("Balloon bursts!");
	}

	this.currentPressure++;
    }

    public int getCurrentPressure()
    {
	return this.currentPressure;
    }

    public static void main(String[] args)
    {
	
	InflatableBalloon balloon = new InflatableBalloon();
	
	try
	{
	    for(int count = 0; count <= 10; count++) {
		balloon.inflate();
		System.out.println("Balloon inflated, pressure now: " + balloon.getCurrentPressure());
	    }
	    
	} catch (BalloonBurstException e) {
	    System.out.println(e.getMessage());
	}

    }

}

class BalloonBurstException extends Exception
{
	private static final long serialVersionUID = 1L;

	public BalloonBurstException(String message)
    {
	super(message);
    }
}