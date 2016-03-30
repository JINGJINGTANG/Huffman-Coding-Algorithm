//LinkedList class
public class LinkedList<T>
{
	//attributes
	private Node<T> head;
	private int count;
	
	//constructor
	public LinkedList()
	{
		head = null;
		count=0;
	}
	
	//adds an item to the front of the linked list
	public void add(T item)
	{
		Node<T> itemnode = new Node<T>(item, head);
		head = itemnode;
		count++;
	}
	//adds item to the end of the linked list
	public void addToEnd(T item)
	{
		Node<T> itemnode = new Node<T>(item, null);
		if (isEmpty())
			head = itemnode;
		else
		{
			Node<T> curr = head;
			while (curr.getNext()!=null)
			{
				curr= curr.getNext();	
			}
				curr.setNext(itemnode);
		}
		count++;
	}
	
	//returns the current size of the linked list
	public int size()
	{
		return count;
	}
	
	//clears the linked list
	public void clear()
	{
		head = null;
		count=0;
	}
	
	//returns true if the linked list is empty
	public boolean isEmpty()
	{
		return (count==0);
	}
	
	//scans the linked list and prints the data
	public void enumerate()
	{
		Node<T> curr = head;
	
		while (curr!=null)
		{
			System.out.println(curr.getData());
			curr = curr.getNext();
		}
		
	}
	
	//returns the data at a given index
	public T getAt(int index)
	{
		Node<T> curr = head;
		if (index<0||index>=count)
		{
			System.out.println("Can't get. Index out of bounds");
			System.exit(0);
		}
		else
		{
			
			for(int i=0; i<index; i++)
				curr = curr.getNext();
			
		}
		return curr.getData();
			

	}
	
	//inserts an item at a given index
	public void insertAt(T item, int index)
	{
		if (index<0||index>count)
		{
			System.out.println("Can't insert. Index out of bounds.");
			System.exit(0);
		}
		else
		{
			Node<T> prev = head;
			if (index==0)
			{
				add(item);
				return;
			}
			for(int i=0;i<index-1;i++)
				prev = prev.getNext();
			Node<T> itemnode = new Node<T>(item,prev.getNext());
			prev.setNext(itemnode);
			count++;
		}
	}
	
	//sets the data at a node at a given index
	public void setAt(T item, int index)
	{
		if (index<0||index>=count)
		{
			System.out.println("Can't set. Index out of bounds");
			System.exit(0);
		}
		else
		{
			Node<T> curr = head;
			for(int i=0;i<index;i++)
				curr = curr.getNext();
			curr.setData(item);
		}
	}
	
	//returns the index of the first occurrence of a given item, -1 if not found
	public int indexOf(T item)
	{
		if (count==0)
			return -1;
		Node<T> curr = head;
		for(int i=0;i<count;i++)
		{
			if (item.equals(curr.getData()))
				return i;
			curr = curr.getNext();
		}
		return -1;
	}
	
	//removes the node at a given index
	public T removeAt(int index)
	{
		T ret = null;
		if (index<0||index>=count)
		{
			System.out.println("Can't remove. Index out of bounds");
			System.exit(0);
		}
		else
		{
			
			if (index==0)
			{
				ret = head.getData();
				head = head.getNext();
			}
			else
			{
				Node<T> prev = head;
				for(int i=0;i<index-1;i++)
					prev = prev.getNext();
				Node<T> curr = prev.getNext();
				ret=curr.getData();
				prev.setNext(curr.getNext());
				curr.setNext(null);	
			}
			count--;
		}
		return ret;
	}
	
	//removes the node containing the first occurrence of a given item
	public void remove (T item)
	{
		int i = indexOf(item);
		if (i==-1)
		{
			System.out.println("No such item");
			System.exit(0);
		}
		else
			removeAt(i);
	}
	
	//Removes all nodes containing a given item
	//Does it in one scan (O(n))
	public void removeAll(T item)
	{
		
		Node<T> curr, prev=null;
		curr = head;
		while (curr!=null)
		{
			if (head.getData().equals(item))
			{
				head = head.getNext();
				curr = curr.getNext();
				count--;
			}
			else if (curr == head)
			{
				prev = curr;
				curr = curr.getNext();
			}
			else
			{
				if (curr!=null)
				{
					if (curr.getData().equals(item))
					{
						prev.setNext(curr.getNext());
						curr= prev.getNext();
						count--;
					}
					else
					{
					prev = curr;
					curr = curr.getNext();
					}
				}
			}
		}
	}
	
}