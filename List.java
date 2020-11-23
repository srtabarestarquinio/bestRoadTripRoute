//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route - List.java class

public class List<E>{

	E[] arr;
	int size;

	public List(){
		arr = (E[])new Object[15];//array of size 15
		size = 0;
	}
	public List(int size){
		arr = (E[])new Object[size];
		size = size;
	}
	public int getSize(){
		return size;
	}
	public E get(int position){
		if(position<0||position>=size){
			System.out.println("Invalid position");
			return null;
		}
		return arr[position];
	}
	private void grow_list(){
		E [] new_arr=(E[])new Object[arr.length*2];
		for(int i=0; i< arr.length; i++){
			new_arr[i]= arr[i];
		}
		arr=new_arr;
	}
	public void add (E value){
		if(size == arr.length){
			grow_list();
		}
		arr[size++]=value;
	}
	public void add(int position, E value){
		for(int i = size; i>position; i--){
			arr[i]=arr[i-1];
		}
		arr[position]=value;
		++size;
	}
	public E remove(int position){
		if(position<0 && position>=size){
			System.out.println("Invalid index");
		}
		E value = arr[position];
		for(int i=position+1; i<size; i++){
			arr[i-1]=arr[i];
		}
		size--;
		return value;
	}
}