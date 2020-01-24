
public class SkipList<V> {
	
	public static final String SMALLEST_STRING = "\n";
	public static final String GREATEST_STRING = "~~~~~~~~~";
	
	private int numOfLevels;
	private Node<V> startNode;
	private Node<V> endNode;
	private int numOfEntries;
	
	
	protected static class Entry<V>{
		private String key;
		private V value;
		
		protected Entry(String key1, V value1)
		{
			this.key = key1;
			this.value = value1;
		}
		
		protected void setKey(String key1) {
			this.key = key1;
		}
		protected void setValue(V value1) {
			this.value = value1;
		}
		protected V getValue()
		{
			return this.value;
		}
		protected String getKey()
		{
			return this.key;
		}
		
	}
	
	
	protected static class Node<V>{
		
		Node<V> upNode = null;
		Node<V> downNode = null;
		Node<V> leftNode = null;
		Node<V> rightNode = null;
		Entry<V> data = null;
		
		protected Node(String k1, V v1, Node<V> up, Node<V> down, Node<V> left, Node<V> right) {
			this.upNode = up;
			this.downNode = down;
			this.leftNode = left;
			this.rightNode = right;
			this.data = new Entry<>(k1, v1);
		}
		
		protected void setUp(Node<V> up) {
			this.upNode = up;
		}
		
		protected Node<V> getUp(){
			return this.upNode;
		}
		
		protected void setDown(Node<V> down) {
			this.downNode = down;
		}
		
		protected Node<V> getDown(){
			return this.downNode;
		}
		
		protected void setLeft(Node<V> left) {
			this.leftNode = left;
		}
		
		protected Node<V> getLeft(){
			return this.leftNode;
		}
		
		protected void setRight(Node<V> right) {
			this.rightNode = right;
		}
		
		protected Node<V> getRight(){
			return this.rightNode;
		}
		
		protected Entry<V> getData(){
			return this.data;
		}
		protected void setData(Entry<V> data){
			this.data = data;
		}
	}
	
	
	public SkipList() {
		this.numOfLevels = 0;
		this.numOfEntries = 0;
		this.startNode = new Node<>(SkipList.SMALLEST_STRING, null, null, null, null, null);
		this.endNode = new Node<>(SkipList.GREATEST_STRING, null, null, null, this.startNode, null);
		this.startNode.setRight(this.endNode);
	}
	
	public V get(String key1) {
		
		if(key1 == null) {
			throw new IllegalArgumentException("key cant be null");
		}
		
		V result = null;
		Node<V> currentNode = this.startNode;
		
		while(currentNode != null) {
			
			int res = key1.compareTo(currentNode.getData().getKey());
			if(res == 0) {
				result = currentNode.getData().getValue();
				currentNode = null;
			} else {
				Node<V> righttNode = currentNode.getRight();
				if(righttNode == null) {
					result = null;
					currentNode = null;
				}else {
					int res1 = key1.compareTo(righttNode.getData().getKey());
					if(res1 > 0) {
						currentNode = currentNode.getRight();
					}
					if(res1 < 0) {
						currentNode = currentNode.getDown();
					}
				}
			}
			
			
		}
		return result;
	}
	
	
	public V delete(String key1) {
		
		if(key1 == null) {
			throw new IllegalArgumentException("key cant be null");
		}
		
		V result = null;
		Node<V> currentNode = this.startNode;
		
		while(currentNode != null) {
			
			int res = key1.compareTo(currentNode.getData().getKey());
			if(res == 0) {
				result = currentNode.getData().getValue();
				
				currentNode.getLeft().setRight(currentNode.getRight());
				currentNode.getRight().setLeft(currentNode.getLeft());
				
				currentNode = currentNode.getDown();
			} else {
				Node<V> righttNode = currentNode.getRight();
				if(righttNode == null) {
					result = null;
					currentNode = null;
				}else {
					int res1 = key1.compareTo(righttNode.getData().getKey());
					if(res1 > 0) {
						currentNode = currentNode.getRight();
					}
					if(res1 < 0) {
						currentNode = currentNode.getDown();
					}
				}
			}
			
			
		}
		
		if(result != null) {
			Node<V> currentNode1 = this.startNode.getDown();
			while(currentNode1.getRight().getData().getKey().equals(SkipList.GREATEST_STRING)) {
				Node<V> rightOfCurrent1 = currentNode1.getRight();
				
				currentNode1.getUp().setDown(currentNode1.getDown());
				currentNode1.getDown().setUp(currentNode1.getUp());
				rightOfCurrent1.getUp().setDown(rightOfCurrent1.getDown());
				rightOfCurrent1.getDown().setUp(rightOfCurrent1.getUp());
				
				this.numOfLevels--;
				currentNode1 = currentNode1.getDown();
			}
			
		}
		
		if(result != null) {
			numOfEntries--;
		}
		
	return result;	
	}
	
	
	public int getSize() {
		return this.numOfEntries;
	}
	
	public V put(String key1, V value1) {
		
		if(key1 == null || value1 == null) {
			throw new IllegalArgumentException("key and value cant be null");
		}
		
		V result = null;
		V alreadyInside = this.get(key1);
		
		if(alreadyInside != null) {
			
			Node<V> currentNode = this.startNode;
			
			while(currentNode != null) {
				
				int res = key1.compareTo(currentNode.getData().getKey());
				if(res == 0) {
					result = currentNode.getData().getValue();
					currentNode.getData().setValue(value1);
					
					currentNode = currentNode.getDown();
				} else {
					Node<V> righttNode = currentNode.getRight();
					if(righttNode == null) {
						result = null;
						currentNode = null;
					}else {
						int res1 = key1.compareTo(righttNode.getData().getKey());
						if(res1 > 0) {
							currentNode = currentNode.getRight();
						}
						if(res1 < 0) {
							currentNode = currentNode.getDown();
						}
					}
				}
				
				
			}
			
		} else {
			
			numOfEntries++;
			
			Node<V> currentNode = this.startNode;
			
			Node<V> leftSideNode = null;
			Node<V> rightSideNode = null;
			
			while(currentNode != null) {
				
				int res = key1.compareTo(currentNode.getData().getKey());
				if(res == 0) {
					result = currentNode.getData().getValue();
					currentNode = currentNode.getDown();
					
				} else {
					Node<V> righttNode = currentNode.getRight();
					if(righttNode == null) {
						result = null;
						currentNode = null;
						
					}else {
						int res1 = key1.compareTo(righttNode.getData().getKey());
						if(res1 > 0) {
							currentNode = currentNode.getRight();
						}
						if(res1 < 0) {
							Node<V> tempNode = currentNode.getDown();
							
							if(tempNode == null) {
								
								leftSideNode = currentNode;
								rightSideNode = currentNode.getRight();
								currentNode = currentNode.getDown();
							
							} else {
								currentNode = currentNode.getDown();
							}
						}
					}
				}
				
				
			}
			
			int nn = 0;
			double rand = Math.random();
			while(rand < 0.5) {
				nn++;
				rand = Math.random();
			
			}
			
			while(nn >= this.numOfLevels) {
				Node<V> newStartNode = new Node<V>(SkipList.SMALLEST_STRING, null, null, this.startNode, null, null);
				Node<V> newEndNode = new Node<V>(SkipList.GREATEST_STRING, null, null, this.endNode, newStartNode, null);
				newStartNode.setRight(newEndNode);
				
				this.endNode.setUp(newEndNode);
				this.startNode.setUp(newStartNode);
				
				this.endNode = newEndNode;
				this.startNode = newStartNode;
				this.numOfLevels++;
				
			}
			
			
			Node<V> downSideNode = null;
			for(int i = 0; i<= nn ; i++) {
				
				Node<V> newNode = new Node<V>(key1, value1, null, null, null, null);
	
				if( i == 0) {
					newNode.setDown(downSideNode);
					newNode.setRight(rightSideNode);
					newNode.setLeft(leftSideNode);
				}else {
					newNode.setDown(downSideNode);
					downSideNode.setUp(newNode);
					newNode.setRight(rightSideNode);
					newNode.setLeft(leftSideNode);
					
				}
				
				while(rightSideNode.getUp() == null) {
					rightSideNode = rightSideNode.getRight();
				}
				rightSideNode = rightSideNode.getUp();
				
				while(leftSideNode.getUp() == null) {
					leftSideNode = leftSideNode.getLeft();
				}
				leftSideNode = leftSideNode.getUp();
				
				downSideNode = newNode;
				
			}
			
			
			result = value1;
			
		}
		
		
		return result;
	}
	
	
	
	public int getNumOfLevels() {
		return this.numOfLevels;
	}
	
	
	
	
	
	
	
	

}
