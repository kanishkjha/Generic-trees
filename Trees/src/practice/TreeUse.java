package practice;

import java.util.*;

class Pair{

	TreeNode<Integer> requiredNode=null;
	int justLarger=Integer.MAX_VALUE;
}

public class TreeUse {
	
	/* Code to find next largest node */
	public static Pair helper(TreeNode<Integer> root, int n){

		if(root.children.size()==0 && root.data>n){
			Pair ans= new Pair();
			ans.requiredNode=root;
			ans.justLarger=root.data;
			return ans;
		}

		if(root.data>n){
			Pair ans= new Pair();
			ans.requiredNode=root;
			ans.justLarger=root.data;

			for(int i=0; i<root.children.size(); i++){
				if(ans.justLarger>helper(root.children.get(i), n).justLarger){
					ans=helper(root.children.get(i), n);
				}
			}

			return ans;

		}else{

			Pair ans= new Pair();

			for(int i=0; i<root.children.size(); i++){
				if(ans.justLarger>helper(root.children.get(i), n).justLarger){
					ans=helper(root.children.get(i), n);
				}
			}	

			return ans;		
		}
	}
	
	public static TreeNode<Integer> findNextLargerNode(TreeNode<Integer> root, int n){
		
		// Write your code here
		
		Pair ans= helper(root, n);

		return ans.requiredNode;
		
		}
	
	/* Code to find the second largest */
	public static TreeNode<Integer> findSecondLargest(TreeNode<Integer> root){

		if(root.children.size() == 0){
	          return null;
	      }
	
	    Queue<TreeNode<Integer>> queue = new LinkedList<>();
	    TreeNode<Integer> firstLargest=root,secondLargest=null;
	    int data = 0;
	    queue.add(root);
	
	    while(!queue.isEmpty())
	    {
	        TreeNode<Integer> frontNode = queue.poll();
	
	            for(int i=0;i<frontNode.children.size();i++){
	                queue.add(frontNode.children.get(i));
	            }
	        
	            if(frontNode.data >  data){
	                if(frontNode.data > firstLargest.data){
	                    secondLargest = firstLargest;
	                    data = firstLargest.data;
	                    firstLargest = frontNode;
	                }
	                else if (frontNode.data < firstLargest.data){
	                    secondLargest = frontNode;
	                    data = secondLargest.data;
	                }
	            }
	    }
	        
	    
	    return secondLargest;

	}
	
	public static void preorder(TreeNode<Integer> root) {
		if(root==null) {
			return;
		}
		
		System.out.print(root.data+" ");
		
		for(int i=0; i<root.children.size(); i++) {
			preorder(root.children.get(i));
		}
	}
	
	public static void printAtDepthK(TreeNode<Integer> root, int k) {
		
		if(k<0) {
			return;
		}
		
		if(k==0) {
			System.out.println(root.data);
			return;
		}
		
		for(int i=0; i<root.children.size(); i++) {
			printAtDepthK(root.children.get(i), --k);
		}
	}
	
	public static int largest(TreeNode<Integer> root) {
		if(root==null) {
			return Integer.MIN_VALUE;
		}
		
		int ans= root.data;
		
		for(int i=0; i<root.children.size(); i++) {
			int childLargest= largest(root.children.get(i));
			if(childLargest>ans) {
				ans=childLargest;
			}
		}
		
		return ans;
	}
	
	public static int numNodes(TreeNode<Integer> root) {
		if(root==null) {
			return 0;
		}
		int count=1;
		
		for(int i=0; i<root.children.size(); i++) {
			count+=numNodes(root.children.get(i));
		}
		
		return count;
	}
	
	public static TreeNode<Integer> takeInput(Scanner s){
		int n;
		
		System.out.println("Enter next node :");
		n= s.nextInt();
		
		TreeNode<Integer> root= new TreeNode<>(n);
		
		System.out.println("Enter no. of children for "+ n);
		int childCount=s.nextInt();
		
		for(int i=0; i<childCount; i++) {
			TreeNode<Integer> child=takeInput(s);
			root.children.add(child);
		}
		
		return root;
	}
	
	public static void print(TreeNode<Integer> root) {
		String s= root.data+":";
		
		for(int i=0; i<root.children.size(); i++) {
			s+=root.children.get(i).data+",";
		}
		System.out.println(s);
		
		for(int i=0; i<root.children.size(); i++) {
			print(root.children.get(i));
		}
	}
	
	public static TreeNode<Integer> takeInputLevelWise(){
		Scanner s= new Scanner(System.in);
		
		System.out.println("Enter root data");
		int rootData= s.nextInt();
		QueueUsingLL<TreeNode<Integer>> pendingNodes= new QueueUsingLL<>(); 
		TreeNode<Integer> root= new TreeNode<Integer>(rootData);
		pendingNodes.enqueue(root);
		
		while(!pendingNodes.isEmpty()) {
			
			try {
				TreeNode<Integer> frontNode=pendingNodes.dequeue();
				System.out.println("Enter number of children of "+ frontNode.data);
				int numChildren=s.nextInt();
				
				for(int i=0; i<numChildren; i++) {
					System.out.println("Enter "+(i+1)+"th child of "+frontNode.data);
					int child= s.nextInt();
					TreeNode<Integer> childNode= new TreeNode<Integer>(child);
					frontNode.children.add(childNode);
					pendingNodes.enqueue(childNode);
				}
			
			} catch (QueueEmptyException e) {
				// We won't reach here ever
				return null;
			}
		}
		
		return root;
	}

	public static void main(String[] args) {
		
		Scanner s= new Scanner(System.in);
	
		TreeNode<Integer> root= takeInputLevelWise();
		print(root);
		System.out.println(largest(root));
		System.out.println(numNodes(root));
		printAtDepthK(root, 2);
		
//		TreeNode<Integer> root= new TreeNode<Integer>(4);
//		TreeNode<Integer> node1= new TreeNode<Integer>(2);
//		TreeNode<Integer> node2= new TreeNode<Integer>(3);
//		TreeNode<Integer> node3= new TreeNode<Integer>(5);
//		TreeNode<Integer> node4= new TreeNode<Integer>(6);
//		
//		root.children.add(node1);
//		root.children.add(node2);
//		root.children.add(node3);
//		
//		node2.children.add(node4);
//		
//		System.out.println(root);
	}

}
