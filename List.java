
public class List {
	Node head; //Node variable to store the first property of the collection
	int owned; //Integer to track of total # of properties in the collection
	
	public class Node{
		Node link; //Pointer to next property
		Property property; //Property present in the current node
		
		Node(Property in){
			link = null; //Sets the link to null assuming there are no properties after
			property=in; //Node contains property passed in
		}
	}
	
	//Constructor for list class
	public List(){
		head=null; //Sets head to null
		owned=0; //Sets # of properties owned to 0
	}
	
	public void insert(Property given) {
		//Inserts a property bought at the end of the list
		//Doesn't return anything
		
		//If there are no properties in the list
		if(head==null) head=new Node(given); //Make the first element contain the property
	  
	  else{
		  Node temp = head; //Start at the beginning of the list
		  while(temp.link!=null) temp=temp.link; //Traverse through list to get the last element
		  temp.link=new Node(given); //Link the property bought to occur after the last property
	  }
	  
	  owned++; //Increase # of properties owned by 1
	}
	
	public void sort() {
		//Method to sort the property collection of a player 1st by property type and then by price
		//Uses bubble sort as it is a stable sort, useful for multi-level sorting (as the order of previously sorted elements doesn't chance)
		//Doesn't return anything
		
		boolean sorted = false; //Set sorted to false
		while(!sorted) { //While the list is not sorted
			sorted =true; //Set sorted to true to detect any swaps
			for(Node j=head; j.link!=null; j=j.link) { //Iterating through the list 
				
				//Checks if current property's type is greater than the next one OR if the types are equal but 
				//the price of the current property is greater than the next one
				if(j.property.getType()>j.link.property.getType() || ( (j.property.getType()==j.link.property.getType()) 
						&& (j.property.getPrice()>j.link.property.getPrice()) )) {
					
					//Swapping the current property with the next one
					Property prop = j.property;
					j.property= j.link.property;
					j.link.property=prop;
					sorted=false; //Setting sorted to false when a swap has occurred
				}
			}
		}
	}
	
	public String display() {
		//Method to iterate through player's collection & return the information of each property in the player's collection
		
		if(head==null) return ""; //Program exits when there are no properties to display
		else {
			int i=1; //Variable to display property #
			String info="";
			for(Node temp=head; temp!=null; temp = temp.link, i++) { 
				//Iterates through list of properties and appends the string to hold each property's info
				info+="Property #"+i+"\n"+temp.property.getInfo()+"\n";
			}
			return info; //Returning string containing all property info
		}
	}	
	
	public double getTotalAssetValue() {
		//Calculates and returns the asset values of all the properties belonging to the player's collection
		double assetValue=0; // Sets asset value to 0;
		Node temp=head; //Starting at the beginning of the list
		while(temp!=null) { //Check if the current item is null
			assetValue+=temp.property.getAssetValue(); //Adding asset value of the current property
			temp=temp.link; //Moving to next element on the list
		}
		
		return assetValue; //Returning asset value
	}
	
	public Property[] toArray(){
		//Added by Aadya
		//Modified by Yuva
		
		if(owned != 0){ //cannot create an array of size 0
			Property[] properties = new Property[owned];//create an array to store property objects
			Node temp2 = head;
			int index = 0; //iterate through index of the arrays
			while(temp2.link != null){
				properties[index] = temp2.property; //store refrence of coressponding proerty in the array
				index++; //increment to next index
				temp2 = temp2.link; //iterate to next property in the list
			}
			return properties;
		}
		else return null;
	}
	
	public int search(int type) {
		//Searches for the 1st instance where the property of the received type is found in the player's collection;
		//Returns the 1st instance (the position) or -1 when it is not found
		int position = -1; //Sets position found to -1;
		int i=1; //Counter variable to track position
		
		//Iterates through the list until there are no items left or until the type is found
		for(Node temp=head; temp!=null && position==-1; temp = temp.link, i++) {
			if(temp.property.getType()==type) position=i; //Setting position to i when the types of the current property matches the property received
		}
		return position; //Returning position
	} 
	
	public Property delete(int pos) {
		//Receives a position and deletes the property at the position
		//Returns the property deleted
		
		Node nodeToDelete=head; //Variable to hold the node that will be deleted
		if(owned==1) head=null; //Emptying list of properties when there is only one property.
		else {
			if(pos==1) head=head.link; //Skipping over the head node when 1st property needs to be deleted
			else {
				Node prev=null; //Variable to track previous node
				for(int i=1; i<pos; i++) { //Iterating through the list until the position is found
					prev=nodeToDelete; //Moving pointer of previous node to the current node
					nodeToDelete=nodeToDelete.link;// Moving pointer of current node to the next node
				} 
				prev.link=nodeToDelete.link; //Adjusting links so that previous node skips over the node to be deleted
			}
		}
		owned--; //Decreasing # of properties owned by 1
		return nodeToDelete.property; //Returning the property deleted
	}
}



