//Node class
public class Node<T>
{
	//create attributes
	private T data;
	private Node<T> next;
	//constructor that sets the data
	public Node(T data, Node<T> next)
	{
		this.data = data;
		this.next = next;
	}
	//get and set methods
	public T getData()
	{
		return data;
	}
	public Node<T> getNext()
	{
		return this.next;
	}
	public void setData(T data)
	{
		this.data = data;
	}
	public void setNext(Node<T> next)
	{
		this.next = next;
	}
	
}