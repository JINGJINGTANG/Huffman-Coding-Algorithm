//Huffman tree
//import utilities
import java.io.*;
import java.util.*;
public class Huffman
{
	public static void main(String[] args) throws IOException
	{
		String fname1, fname2;
		File file;
		Scanner keyboard = new Scanner(System.in);
		Scanner inFile;
		//create  string variables to read the word
		String line, word;
		StringTokenizer token;
		//create an array to store the frequency
		int[] freqTable = new int[256];
		//ask users to enter the path of a file
		System.out.print("Enter the complete path of the file to encode: ");
		fname1 = keyboard.nextLine();
		System.out.print("Enter the complete path of the file to decode: ");
		fname2 = keyboard.nextLine();
		
		//start reading a file
		file = new File(fname1);
		inFile = new Scanner(file);
		double total = 0;
		String s = "";
		while (inFile.hasNext())
		{
			line = inFile.nextLine();
			s += line + "\n";
			token = new StringTokenizer(line, " ");
			//record the frequency of each character
			while (token.hasMoreTokens())
			{
				word = token.nextToken();
				freqTable = updateFrequencyTable(freqTable, word);
				total += word.length();
			}	
		}
		//print frequency table
		System.out.println("Table of frequencies");
		System.out.println("Character \t Frequency \n");

		for (int i = 0; i < 256; i ++)
		{
			if (freqTable[i] > 0)
				System.out.println(((char)i) + "\t" + i + "\t" + freqTable[i]/total);
		}
		
		//create a queue of  <BinaryTree<Pair>> objects, sorted in ascending order of frequencies
		Queue<BinaryTree<Pair>> S = buildQueue(freqTable);
		//add code here for printing the queue values for diagnostics 
		Queue<BinaryTree<Pair>> T = new Queue<BinaryTree<Pair>>();
		//build the huffmanTree
		BinaryTree<Pair> huffmanTree = createTree(S, T);
		String[] encodingTable = findEncoding(huffmanTree);
		//print the encoding table

		System.out.println("\nEncoding Table");	
		for (int i = 0; i < 256; i++)
		{
			if (encodingTable[i] != null)
				System.out.println(((char)i) + "\t" + encodingTable[i]);
		}

		//encoded the words
		System.out.println("\nEncoded text file: ");
		String encoded = "";
		for (int i = 0; i < s.length(); i++)
		{
			//get the position of the character
			int index = s.charAt(i);
			if (encodingTable[index] != null)
				encoded += encodingTable[index];
			else 
				encoded += s.charAt(i);
		}
		//print the encoded data
		System.out.print(encoded);
		//read the file again to build huffman tree
		file = new File(fname2);
		inFile = new Scanner(file);
		String decoded = "";
		System.out.println("\nDecoded text file: ");
		//create a root of huffmanTree
		huffmanTree = huffmanTree.root();
		while (inFile.hasNext())
		{
			line = inFile.nextLine();
			char ch;

			for (int i = 0; i < line.length(); i++)
			{
				//get the left node if it is 0
				if (line.charAt(i) == '0')
				{	
					//if the left of current is null
					if (huffmanTree.getLeft() == null)
					{
						//add the char into the string and decrease i by 1
						i--;
						ch = huffmanTree.getData().getValue();
						decoded += ch;
						huffmanTree = huffmanTree.root();
					}
					//else move to the left
					else
						huffmanTree = huffmanTree.getLeft();
				}
				//if the char equals 1
				else if (line.charAt(i) == '1')
				{
					//if the right of current is null
					if (huffmanTree.getRight() == null)
					{
						//add the char into the string and decrease i by 1
						i--;
						ch = huffmanTree.getData().getValue();
						decoded += ch;
						huffmanTree = huffmanTree.root();
					}
					//else move to the right
					else
						huffmanTree = huffmanTree.getRight();
				}
				//add the symbol(space)
				else
					decoded += line.charAt(i);	
			}	
			decoded += "\n";
		}
		//print the decoded text
		System.out.print(decoded);
		inFile.close();
	}
	//method that return the frequency of each character
	public static int[] updateFrequencyTable(int [] result, String s)
	{
		for (int i = 0; i < s.length(); i++)
			result[(byte)s.charAt(i)]++;
		return result;
	}
	//method that find the smallest value in the freqTable
	public static Pair findSmallest(int[] table)
	{
		//find smallest freq in the freq table and return a Pair object
		int value = Integer.MAX_VALUE;
		char ch = 'a';
		int index = 0;
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] > 0 && table[i] < value)
			{
				value = table[i];
				ch = (char)i;
				index = i;
			}
		}
		Pair pair = new Pair(ch, value);
		//also reset the found value in the array to zero
		table[index] = 0;
		if (value == Integer.MAX_VALUE)
			return null;
		else 
			return pair;
	
	}
	//method that add every pair object of frequencies into a queue
	public static Queue<BinaryTree<Pair>> buildQueue(int[] table)
	{
		//create a Queue of Pairs sorted from the smallest freq
		Queue <BinaryTree<Pair>> q = new Queue<BinaryTree<Pair>>();
		for (int i = 0; i < table.length; i++)
		{
			//make use of the method findSmallest
			Pair pair = findSmallest(table);
			//add the data to the queque
			if (pair != null)
			{
				BinaryTree<Pair> item = new BinaryTree<Pair>();
				item.makeRoot(pair);
				q.enqueue(item);
			}
		}
		return q;
	}
	//method that builds the Huffman Tree
	public static BinaryTree<Pair> createTree(Queue<BinaryTree<Pair>> S, Queue<BinaryTree<Pair>> T)
	{
		BinaryTree<Pair> resultTree = new BinaryTree<Pair>();
		char dummy = '0';
		//while S is not empty, keep comparing items in S and in T
		while (!S.isEmpty())
		{
			//create three bianrytree object to store each item
			BinaryTree<Pair> root = new BinaryTree<Pair>();
			BinaryTree<Pair> A = new BinaryTree<Pair>();
			BinaryTree<Pair> B = new BinaryTree<Pair>();
			//if T is empty, dequeue S
			if (T.isEmpty())
			{
				A = S.dequeue();
				//if T is still empty, dequeue S again
				if (T.isEmpty())
					B = S.dequeue();
				else 
				{
					if (!S.isEmpty())
					{
						//the first item in T is smaller than the item in S, dequeue it. else, dequeue S
						if (S.peek().getData().getFreq() >= T.peek().getData().getFreq() && S.peek().getData().getFreq() >= A.getData().getFreq())
							B = T.dequeue();
						else
							B = S.dequeue();
					}
					else
						B = T.dequeue();
				}
			}
			else
			{
				//if T is not empty, compare the first item in S and in T, dequeue the smaller one
				if (S.peek().getData().getFreq() < T.peek().getData().getFreq())
				{
					A = S.dequeue();
					if (!S.isEmpty())
					{
						//if S is not empty, compare the first item again and dequeue the samller one
						if (S.peek().getData().getFreq() <= T.peek().getData().getFreq() && S.peek().getData().getFreq() <= A.getData().getFreq())
							B = S.dequeue();	
						else
							B = T.dequeue();
					}
					else
						B = T.dequeue();
				}
				else
				{
					//the item in T is smaller than the item in S, dequeue it from T
					A = T.dequeue();
					//check if T is empty
					if (T.isEmpty())
					{
						B = S.dequeue();
					}
					else
					{
						//compare the first item in the S and T, dequeue the smaller one
						if (S.peek().getData().getFreq() < T.peek().getData().getFreq())
							B = S.dequeue();
						else
							B = T.dequeue();
					}
				}
			}
			//create a Pair object and store the values
			Pair pair = new Pair(dummy, A.getData().getFreq() + B.getData().getFreq());
			root.makeRoot(pair);
			root.attachLeft(A);
			root.attachRight(B);
			//add them to Queue T
			T.enqueue(root);
		}
		//combine two items in T until the size is 1
		while (T.size() != 1)
		{
			BinaryTree<Pair> root = new BinaryTree<Pair>();
			BinaryTree<Pair> A = new BinaryTree<Pair>();
			BinaryTree<Pair> B = new BinaryTree<Pair>();
			if (T.size() > 1)
			{
				A = T.dequeue();
				B = T.dequeue();
				Pair pair = new Pair(dummy, A.getData().getFreq() + B.getData().getFreq());
				root.makeRoot(pair);
				root.attachLeft(A);
				root.attachRight(B);
				T.enqueue(root);
			}
		}
		resultTree = T.first();
		return resultTree;
	}
	//method that encodes the message
	public static void findEncoding(BinaryTree<Pair> t, String[] a, String prefix)
	{
		if (t.getLeft() == null && t.getRight() == null)
		{
			a[(byte)(t.getData().getValue())]= prefix;
		}
		else
		{
			findEncoding(t.getLeft(), a, prefix + "0");
			findEncoding(t.getRight(), a, prefix + "1");
		}
	}
	//method that uses the previous method to encode the message
	public static String[] findEncoding(BinaryTree<Pair> t)
	{
		String[] result = new String[256];
		findEncoding(t, result, "");
		return result;
	}
}
