
import java.util.Scanner;
public class Balanced_Binary_Search_TREE {
	 public static void main(String[] args)
	    {
	        int a,b,c;
	        Scanner input=new Scanner(System.in);
	        function b1 = new function();
	        while (true)
	        {
	            System.out.println("Enter 1 for insert data");
	            System.out.println("Enter 2 for delete data");
	            System.out.println("Enter 3 for display data");
	            System.out.println("Enter 4 for exit");
	            a=input.nextInt();
	            switch (a)
	            {
	                case 1:
	                    System.out.println("Enter the node data");
	                    b=input.nextInt();
	                    b1.insert(b);
	                    break;
	                case 2:
	                    System.out.println("Enter the node data which u want to delete");
	                    b=input.nextInt();
	                    b1.delete(b);
	                    break;
	                case 3:
	                    System.out.println("Displaying Tree");
	                    System.out.println("Enter 1 for pre-order display");
	                    System.out.println("Enter 2 for in-order display");
	                    System.out.println("Enter 3 for post-order display");
	                    c=input.nextInt();
	                    switch(c)
	                    {
	                        case 1:
	                            b1.preOrder(b1.getroot());
	                            break;
	                        case 2:
	                            b1.inOrder(b1.getroot());
	                            break;
	                        case 3:
	                            b1.postOrder(b1.getroot());
	                            break;
	                    }
	                break;
	                case 4:
	                    System.exit(0);
	                    break;
	                    
	                    
	            }
	            
	        }    
	    }
	    
	}
	class Node {
	        public int key;
	        public int balance;
	        public Node left, right, parent;
	 
	        Node(int k, Node p) {
	            key = k;
	            parent = p;
	        }
	    }
	class function
	{
	    public Node root;
	     public boolean insert(int key) 
	     {
	        if (root == null)
	            root = new Node(key, null);
	        else {
	            Node n = root;
	            Node parent;
	            while (true) 
	            {
	                if (n.key == key)
	                    return false;
	 
	                parent = n;
	 
	                boolean goLeft = n.key > key;
	                n = goLeft ? n.left : n.right;
	 
	                if (n == null) {
	                    if (goLeft) {
	                        parent.left = new Node(key, parent);
	                    } else {
	                        parent.right = new Node(key, parent);
	                    }
	                    rebalance(parent);
	                    break;
	                }
	            }
	        }
	        return true;
	    }
	      private void rebalance(Node n) {
	        setBalance(n);
	 
	        if (n.balance == -2) {
	            if (height(n.left.left) >= height(n.left.right))
	                n = rotateRight(n);
	            else
	                n = rotateLeftThenRight(n);
	 
	        } else if (n.balance == 2) {
	            if (height(n.right.right) >= height(n.right.left))
	                n = rotateLeft(n);
	            else
	                n = rotateRightThenLeft(n);
	        }
	 
	        if (n.parent != null) {
	            rebalance(n.parent);
	        } else {
	            root = n;
	        }
	    }
	      private Node rotateLeft(Node a) {
	 
	        Node b = a.right;
	        b.parent = a.parent;
	 
	        a.right = b.left;
	 
	        if (a.right != null)
	            a.right.parent = a;
	 
	        b.left = a;
	        a.parent = b;
	 
	        if (b.parent != null) {
	            if (b.parent.right == a) {
	                b.parent.right = b;
	            } else {
	                b.parent.left = b;
	            }
	        }
	 
	        setBalance(a, b);
	 
	        return b;
	    }
	 
	    private Node rotateRight(Node a) {
	 
	        Node b = a.left;
	        b.parent = a.parent;
	 
	        a.left = b.right;
	 
	        if (a.left != null)
	            a.left.parent = a;
	 
	        b.right = a;
	        a.parent = b;
	 
	        if (b.parent != null) {
	            if (b.parent.right == a) {
	                b.parent.right = b;
	            } else {
	                b.parent.left = b;
	            }
	        }
	 
	        setBalance(a, b);
	 
	        return b;
	    }
	 
	    private Node rotateLeftThenRight(Node n) {
	        n.left = rotateLeft(n.left);
	        return rotateRight(n);
	    }
	 
	    private Node rotateRightThenLeft(Node n) {
	        n.right = rotateRight(n.right);
	        return rotateLeft(n);
	    }
	 
	    private int height(Node n) {
	        if (n == null)
	            return -1;
	        return 1 + Math.max(height(n.left), height(n.right));
	    }
	     private void setBalance(Node... nodes) {
	        for (Node n : nodes)
	            n.balance = height(n.right) - height(n.left);
	    }
	     public Node getroot()
	    {
	        return root;
	    }
	     public void delete(int delKey) 
	     {
	        if (root == null)
	            return;
	        Node n = root;
	        Node parent = root;
	        Node delNode = null;
	        Node child = root;
	 
	        while (child != null) {
	            parent = n;
	            n = child;
	            child = delKey >= n.key ? n.right : n.left;
	            if (delKey == n.key)
	                delNode = n;
	        }
	 
	        if (delNode != null) {
	            delNode.key = n.key;
	 
	            child = n.left != null ? n.left : n.right;
	 
	            if (root.key == delKey) {
	                root = child;
	            } else {
	                if (parent.left == n) {
	                    parent.left = child;
	                } else {
	                    parent.right = child;
	                }
	                rebalance(parent);
	            }
	        }
	    }
	     public void inOrder (Node n)
	{
	 
	  if(n== null) 
	      return;
	  inOrder(n.left);  
	  System.out.printf("%d\n",n.key);  
	  inOrder( n.right ); 
	  
	}
	     public void preOrder (Node n)
	{ 
	  if(n == null) 
	      return;
	  System.out.printf("%d\n",n.key);
	  inOrder(n.left);  
	  inOrder(n.right );   
	}

	public void postOrder (Node n)
	{ 
	  if(n == null) 
	      return;  
	  inOrder(n.left);  
	  inOrder(n.right ); 
	  System.out.printf("%d\n",n.key);
	}
}
