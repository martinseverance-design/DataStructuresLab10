import java.lang.Comparable;
import java.util.function.Consumer;


/**
   This is a smart Node that utilizes generics.
   Note how we ensured that the type T will always be comparable...

 */
public class BSTNode<T extends Comparable<T>>
{

   private T val;
   private BSTNode<T> left;
   private BSTNode<T> right;



   public BSTNode(T val)
   {
      this(val,null,null);
   }
    

   public BSTNode(T val, BSTNode<T> l,BSTNode<T> r)
   {
	   this.val = val;
	   left = l;
	   right = r;
   }



   /*
     Uses recursion to insert the target into the tree
    */
   public void insert(T target)
   {
	   if(target.compareTo(val) > 0)
	   {
		   if(right == null) {
			   right = new BSTNode<T>(target);
			   return;
		   }
		   right.insert(target);
	   }else
	   {
		   if(left == null) {
			   left = new BSTNode<T>(target);
			   return;
		   }
		   left.insert(target);
	   }
   }

   /*
     Uses recursion to retrieved the value target from the tree.  
     Returns null if it can't find the value.
    */
   public T retrieve(T target)
   {
	   if(val == target)
	   {
		   return target;
	   }else if(val.compareTo(target) < 0) 
	   {
		   if(right == null)
		   {
			   return null;
		   }
		   return right.retrieve(target);
	   }else
	   {
		   if(left == null)
		   {
			   return null;
		   }
		   return left.retrieve(target);
	   }
	   
   }


    /**
       If it is present, what level is the node?
       If it is not present, what level would it be placed.
     */
   public int retrieveDepth(T target)
   {
	   int depth = 0;
	   if(val == target)
	   {
		   return depth;
	   }else if(val.compareTo(target) < 0) 
	   {
		   if(right == null)
		   {
			   return depth + 1;
		   }
		   depth++;
		   return depth + right.retrieveDepth(target);
	   }else
	   {
		   if(left == null)
		   {
			   return depth + 1;
		   }
		   depth++;
		   return depth + left.retrieveDepth(target);
		  
	   }
   }

   /**
      Uses recursion to return the largest value in the tree
    */
   public T getLargest()
   {
	   if(right == null)
	   {
		   return val;
	   }
	   return right.getLargest();
   }


   /**
      Uses recursion to do an inorder traversals.
      consume is part of a strategy pattern, to determine what the
      "Visit" should be.

    */
   public void inOrderTraversal(Consumer<T> consume)
   {
	   if(left != null) {
		   left.inOrderTraversal(consume);
	   }
	   consume.accept(val);
	   if(right != null)
	   {
		   right.inOrderTraversal(consume);
	   }
   }


   /**
      returns true if this tree is equal to that tree.
      false otherwise.

      Note: While I must always be non-null.  
            Nothing prevents "that" from being null.
	    
	    This one is long!
    */
   public boolean myEquals(BSTNode<T> that)
   {
	   if(this.val != that.val)
	   {
		   return false;
	   }
	   if(left != null && that.left != null) {
		   if(!left.myEquals(that.left))
		   {
			   return false;
		   }
	   }
	   else if((left == null && that.left != null) || (left != null && that.left == null))
	   {
		   return false;
	   }
	   if(right != null && that.right != null) {
		   if(!right.myEquals(that.right))
		   {
			   return false;
		   }
	   }
	   else if((right == null && that.right != null) || (right != null && that.right == null))
	   {
		   return false;
	   }
	   return true;
   }

}
