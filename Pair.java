//Pair class
public class Pair
{
	//create variables
	private char value;
	private int freq;
	//constructor that sets the data
	public Pair(char v, int f)
	{
		value = v;
		freq = f;
	}
	//set methods
	public void setValue(char v)
	{
		value = v;
	}
	public void setFreq(int f)
	{
		freq = f;
	}
	//get methods
	public char getValue()
	{
		return value;
	}
	public int getFreq()
	{
		return freq;
	}
	//toString that returns the pair object
	public String toString()
	{
		return value + "\t" + freq + "\n";
	}
}
