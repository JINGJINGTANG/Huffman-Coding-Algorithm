//BinaryTree class
public class BinaryTree<T> 
{
	//create variables
	private T data;
	private BinaryTree<T> parent;
	private BinaryTree<T> left;
	private BinaryTree<T> right;
	//constructor that sets the data
	public BinaryTree()
	{
		parent = left = right = null;
		data = null;
	}
	
	//method that makes the root of the tree
	public void makeRoot(T data)
	{
		if (this.data != null)
		{
			System.out.println("Can't make root. Already exists");
		}
		else
			this.data = data;
	}
	//method that sets the data
	public void setData(T data)
	{
		this.data = data;
	}
	//method that sets the left data
	public void setLeft(BinaryTree<T> tree)
	{
		left = tree;
	}
	//method that sets the right data
	public void setRight(BinaryTree<T> tree)
	{
		right = tree;
	}
	//method that sets the parent
	public void setParent(BinaryTree<T> tree)
	{
		parent = tree;
	}
	//method that gets the data
	public T getData()
	{
		return data;
	}
	//method that gets the parent
	public BinaryTree<T> getParent()
	{
		return parent;
	}
	//method that gets the left data
	public BinaryTree<T> getLeft()
	{
		return left;
	}
	//method that gets the right data
	public BinaryTree<T> getRight()
	{
		return right;
	}
	//method that attaches the data to the left
	public void attachLeft(BinaryTree<T> tree)
	{
		if (tree==null) return;
		else if (left!=null || tree.getParent()!=null)
		{
			System.out.println("Can't attach");
			return;
		}
		else
		{
			
				tree.setParent(this);
				left = tree;
		}
	}
	//method that attaches the data to the right
	public void attachRight(BinaryTree<T> tree)
	{
		if (tree==null) return;
		else if (right!=null || tree.getParent()!=null)
		{
			System.out.println("Can't attach");
			return;
		}
		else
		{
	
				tree.setParent(this);
				right = tree;
		}
	}
	//method that detaches the left data
	public BinaryTree<T> detachLeft()
	{
		BinaryTree<T> retLeft = left;
		left = null;
		retLeft.setParent(null);
		return retLeft;
	}
	//method that detaches the right
	public BinaryTree<T> detachRight()
	{
		BinaryTree<T> retRight = right;
		right =null;
		retRight.setParent(null);
		return retRight;
	}
	//method that checks if it is empty
	public boolean isEmpty()
	{
		if (data == null)
			return true;
		else
			return false;
	}
	//method that clear the tree
	public void clear()
	{
		left = right = parent =null;
		data = null;
	}
	//method that return the root
	public BinaryTree<T> root()
	{
		if (parent == null)
			return this;
		else
		{
			BinaryTree<T> next = parent;
			while (next.getParent()!=null)
				next = next.getParent();
			return next;
		}
	}
	//list the tree in preorder
	public static <T> void preorder(BinaryTree<T> t)
	{
		if (t!=null)
		{
			System.out.print(t.getData()+"\t");
			preorder(t.getLeft());	
			preorder(t.getRight());
		}
	}
	//list the tree in inorder
	public static <T> void inorder(BinaryTree<T> t)
	{
		if (t!=null)
		{
			inorder(t.getLeft());
			System.out.print(t.getData() + "\t");
			inorder(t.getRight());
		}
	}
	//list the tree in postorder
	public static <T> void postorder(BinaryTree<T> t)
	{
		if (t!=null)
		{
			postorder(t.getLeft());
			postorder(t.getRight());
			System.out.print(t.getData() + "\t");
		}
	}
	

}