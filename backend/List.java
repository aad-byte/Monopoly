package backend;


public class List {
	Node head;
	int owned;
	
	class Node{
		Node link;
		Property property;
		Node(Property in){
			link = null;
			property=in;
		}
	}
	
	public List(){
		head=null;
		owned=0;
	}
	
	//logic is flawed, missing else (new node created twie if head is null)
	public void insert(Property given) {
		  if(head==null){
              head=new Node(given);
              return;
		  }
		  Node temp = head;
		  while(temp.link!=null) temp=temp.link; //Aadya, iterating through the list of nodes until we reach the last
		  temp.link=new Node(given);
		  owned++;
	}
	
	//Updated by Aadya
	public void insertLast(Property given){
		if(head == null){
			head = new Node(given);
		}
		else{
			Node temp = head;
			while(temp.link!= null) temp=temp.link;
			temp.link = new Node(given);
		}
		owned++;
	}
	

	//figure this out, what is pos? the positon of the the property to delete?
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
	}
	
	private Node findLast(int pos) {
		int i=1;
		Node temp=head;
		while(i<pos && temp!=null) {
			temp=temp.link;
			i++;
		}
		return temp;
	}
	
	
	private void sort() {//Aadya - why dont you just sort as you insert?
		//Sort by property type
		//Then, sort by cash value
		
		boolean sorted = false;
		int k=1;
		for(Node i=findLast(this.owned); i!=head && !sorted; i=findLast(owned-k)) {
			sorted =true;
			for(Node j=head; j.link!=null; j=j.link) {
				if(j.property.getType()>j.link.property.getType() || ((j.property.getType()==j.link.property.getType()) && (j.property.getPrice()>j.link.property.getPrice()) )) {
					Property prop = j.property;
					j.property= j.link.property;
					j.link.property=prop;
					sorted=false;
				}
			}
			k++;
		}
	}
	
	public Property search(int pos) {
		boolean found = false;
		Property toReturn = null;
		for(Node temp=head; temp!=null && found==false; temp = temp.link) {
			if(temp.property.getPosition()==pos) {
				found=true;
				toReturn=temp.property;
			}
		}
		return toReturn;
	}
	
	/* 
	public void display() {
		if(head==null) return;
		int i=1;
		for(Node temp=head; temp!=null; temp = temp.link, i++) {
			System.out.println("Property #"+i);
			temp.property.display();
		}
	}*/

	//Added by Aadya, to acess list of property objects without refrence to direct link - dangerous as it allows editing of private fields
	public Property[] toArray(){
		int counter = 0; //coutning the number of properties in the array
		Node temp = head; //staart at the beginning of the list
		//count how many items are in the list
		while(temp != null){
			counter++;
			temp = temp.link;
		}

		if(counter != 0){ //cannot create an array of size 0
			Property[] properties = new Property[counter];//create an array to store property objects
			Node temp2 = head;
			int index = 0; //iterate through index of the arrays
			while(temp2.link != null){
				properties[index] = temp2.property; //store refrence of coressponding proerty in the array
				index++; //increment to next index
				temp2 = temp2.link; //iterate to next property in the list
			}

			return properties;
		}

		return null;
	}

	

	
}

