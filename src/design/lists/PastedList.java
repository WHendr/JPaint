package design.lists;

import java.util.ArrayList;

import design.shapes.Shape;

public class PastedList{
	
	private Node root;

	
	class Node{
		ArrayList<Shape> data;
		Node next;
		
		Node(ArrayList<Shape> d){
			data = d;
			next = null;
		}
		
		public ArrayList<Shape> getData(){
			return this.data;
		}
	
	}
	
	
	public PastedList(ArrayList<Shape> data){
		insert(data);
	}
	
	public void insert(ArrayList<Shape> data) {
		Node temp = new Node(data);
		if(root == null) {
			root = temp;
		}
		else {
			Node curr = root;
			while(curr.next != null) {
				curr = curr.next;
			}
			curr.next = temp;
		}
	}
	
	public ArrayList<Shape> delete(){
		Node curr = root;
		Node prev = root;
		while(curr.next != null) {
			prev = curr;
			curr = curr.next;
		}
		ArrayList<Shape> result = curr.getData();
		if(root.next == null) {
			root = null;
		}
		else {
			curr = null;
		}
		prev.next = null;
		return result;
	}
	
	public ArrayList<Shape> getIterable(){
		ArrayList<Shape> pasted = new ArrayList<Shape>();
		ArrayList<Shape> data;
		Node curr = root;
		data = root.getData();
		for(Shape shape: data) {
			pasted.add(shape);
		}
		if(curr.next != null) {
			do {
				curr = curr.next;
				data = curr.getData();
				for(Shape shape : data) {
					pasted.add(shape);
				}
			} while(curr.next != null);
		}
		return pasted;
	}
	
	public Node getRoot() {
		return this.root;
	}
}
