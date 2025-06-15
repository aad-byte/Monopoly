
public class List {
	Node head; //Node variable to store the first property of the collection
	int owned; //Integer to track of total # of properties in the collection
	
	public class Node{
		Node link; //Pointer to next property
		Property property; //Property present in the current node
		
		Node(Property in){
			link = null;
			property=in;
		}
	}
	
	//Constructor for list class
	public List(){
		head=null;
		owned=0;
	}
	
	public void insert(Property given) {
		  if(head==null){
              head=new Node(given);
              return;
		  }
		  Node temp = head;
		  while(temp.link!=null) temp=temp.link;
		  temp.link=new Node(given);
		  owned++;
	}
	
	public void sort() {
		//Sort by property type
		//Then, sort by cash value
		
		boolean sorted = false;
		while(!sorted) {
			sorted =true;
			for(Node j=head; j.link!=null; j=j.link) {
				if(j.property.getType()>j.link.property.getType() || ( (j.property.getType()==j.link.property.getType()) && (j.property.getPrice()>j.link.property.getPrice()) )) {
					Property prop = j.property;
					j.property= j.link.property;
					j.link.property=prop;
					sorted=false;
				}
			}
		}
	}
	
	public Property[] toArray(){
		/*int counter = 0; //coutning the number of properties in the array
		Node temp = head; //staart at the beginning of the list
		//count how many items are in the list
		while(temp != null){
			counter++;
			temp = temp.link;
		}*/

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
	
	public void display() {
		if(head==null) return;
		int i=1;
		for(Node temp=head; temp!=null; temp = temp.link, i++) {
			System.out.println("Property #"+i);
			temp.property.display();
		}
	}	
	
	public double getTotalAssetValue() {
		double assetValue=0;
		Node temp=head;
		while(temp!=null) {
			assetValue+=temp.property.getAssetValue();
			temp=temp.link;
		}
		
		return assetValue;
	}
}

/*public Property search(int pos) {
	boolean found = false;
	Property toReturn = null;
	for(Node temp=head; temp!=null && found==false; temp = temp.link) {
		if(temp.property.positionOnBoard==pos) {
			found=true;
			toReturn=temp.property;
		}
	}
	return toReturn;
}

public Property delete(int pos) {
	Node nodeToDelete=head;
	if(owned==1) head=null; //Emptying list of properties when there is only one property.
	else {
		if(pos==1) head=head.link;
		else {
			Node prev=null;
			for(int i=1; i<pos; i++) {
				prev=nodeToDelete;
				nodeToDelete=nodeToDelete.link;
			}
			prev.link=nodeToDelete.link;
		}
	}
	owned--;
	return nodeToDelete.property;
}*/

