//Queue<T> class
public class Queue<T>
{
	private LinkedList<T> list;
	int cursor; //the cursor is mainly used for the first and the next methods
	//constructor that creates a queue list
	public Queue()
	{
		list = new LinkedList<T>();
		cursor = -1;
	}
	//add an item into the queue
	public void enqueue(T item)
	{
		list.addToEnd(item);
	}
	public T dequeue()
	{
		return list.removeAt(0);
	}
	//return the size of the queue list
	public int size()
	{
		return list.size();
	}
	//check if the list is empty
	public boolean isEmpty()
	{
		if (list.size() == 0)
			return true;
		else
			return false;
	}
	//clear the list
	public void clear()
	{
		list.clear();
	}
	//return the first item in the queue
	public T peek()
	{
		if (list.size() == 0)
			return null;
		else
			return list.getAt(0);
	}
	//find the index of a specified item
	public int positionOf(T item)
	{
		if (list.size() == 0)
			return -1;
		else
			return list.indexOf(item);
	}
	//remove a given item
	public void remove(T item)
	{
		list.remove(item);
	}
	//return the first item
	public T first()
	{
		if (list.size() == 0)
			return null;
		cursor = 0;
		return list.getAt(cursor);
	}
	//return the next item
	public T next()
	{
		if (cursor < 0 || cursor == (list.size() - 1))
			return null;
		cursor++;
		return list.getAt(cursor);
	}
}