
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
	
	
	private void sort() {
		//Sort by property type
		//Then, sort by cash value
		
		boolean sorted = false;
		int k=1;
		for(Node i=findLast(this.owned); i!=head && !sorted; i=findLast(owned-k)) {
			sorted =true;
			for(Node j=head; j.link!=null; j=j.link) {
				if(j.property.type>j.link.property.type || ((j.property.type==j.link.property.type) && (j.property.getPrice()>j.link.property.getPrice()) )) {
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
			if(temp.property.positionOnBoard==pos) {
				found=true;
				toReturn=temp.property;
			}
		}
		return toReturn;
	}
	
	public void display() {
		if(head==null) return;
		int i=1;
		for(Node temp=head; temp!=null; temp = temp.link, i++) {
			System.out.println("Property #"+i);
			temp.property.display();
		}
	}
	
}

