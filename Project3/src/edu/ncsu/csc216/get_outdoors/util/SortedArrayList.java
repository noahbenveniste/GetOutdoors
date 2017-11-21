package edu.ncsu.csc216.get_outdoors.util;

import java.util.Arrays;

/**
 * A linear data structure that uses an underlying array to store Comparable elements
 * in sorted order. SortedArrayList is capable of dynamically resizing itself if its
 * underlying array reaches capacity. Utilizes a binary search algorithm when searching
 * for elements to maximize runtime efficiency.
 * 
 * @author Noah Benveniste
 * @param <E> indicates that the list can work with any Comparable object type
 */
public class SortedArrayList<E extends Comparable<E>> implements SortedList<E> {

	/** The number of additional elements the list will store when it is resized */
	private static final int RESIZE = 10;
	/** The default starting capacity of the list */
	private static final int DEFAULT_CAPACITY = 10;
	/** The underlying array used to store elements */
	private E[] list;
	/** The number of elements in list */
	private int size;
	/** The number of elements that could potentially be stored in list before resizing */
	private int capacity;
	
	/**
	 * Constructs an empty SortedArrayList with a default starting capacity of 10. The specific
	 * type must implement the Comparable interface.
	 */
	public SortedArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructs an empty SortedArrayList. The specific type must implement the Comparable interface.
	 * This code is reused from https://github.ncsu.edu/engr-csc216-fall2017/csc216-221-LL-8.git
	 * @param capacity the capacity of the underlying array
	 */
	@SuppressWarnings("unchecked")
	public SortedArrayList(int capacity) {
		Comparable<E>[] o = new Comparable[capacity];
		this.size = 0;
		this.list = (E[]) o;
		this.capacity = list.length;
	}
	
	/**
	 * A helper method that grows the underlying list array once size == capacity.
	 * This code is reused from https://github.ncsu.edu/engr-csc216-fall2017/csc216-221-LL-8.git
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		// Update capacity
		this.capacity += RESIZE;
		// Create a new object array with the new, larger capacity
		Comparable<E>[] o = new Comparable[this.capacity];
		// Cast to generic type
		E[] temp = (E[]) o;
		// Assign the elements from the old array to the same index in the new array
		for (int i = 0; i < this.size(); i++) {
			temp[i] = this.list[i];
		}
		// Assign the new array to the list field
		this.list = temp;
	}

	/**
	 * Returns the number of the elements in the list.
     *
     * @return the number of elements in this list
     */
	@Override
	public int size() {
		return this.size;
	}

	/**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
     * Adds the specified element to list in sorted order
     * This code is partially reused from https://github.ncsu.edu/engr-csc216-fall2017/csc216-221-LL-8.git
     *
     * @param e element to be appended to this list
     * @return true if the element is added successfully
     * @throws NullPointerException if e is null
     * @throws IllegalArgumentException if e is a duplicate of an element already in the list
     */
	@Override
	public boolean add(E e) {
		//Check for null input
		if (e == null) {
			throw new NullPointerException("Cannot add null elements");
		}
		
		//Check for repeat elements
		for (int i = 0; i < this.size(); i++) {
			if (this.list[i].equals(e)) {
				throw new IllegalArgumentException("Cannot add duplicate elements.");
			}
		}
		
		//Check if the list has reached capacity
		if (this.size() == this.capacity) { // Grow the array if list is full
			this.resize();
		}
		
		//Adding to an empty list
		if (size == 0) {
			list[size] = e;
			size++;
			return true;
		} else if (list[this.size - 1].compareTo(e) < 0) { //Case where the element is appended to the end of the list i.e. the element being 
			list[size] = e; 							   //added is lexicographically less than the last element in the list
			size++; 
			return true;
		}
		
		//Find the index where the element needs to go
		int index = 0;
		for (int i = 0; i < this.size; i++) {
			//If the currently indexed element in the list is lexicographically less than the element being added,
			//the list should be right shifted and the element put at the current index.
			if (list[i].compareTo(e) >= 0) {
				index = i;
				break;
			}
		}
		
		//Right shift the array to insert the element at the necessary index
		for (int i = this.size; i > index; i--) {
			list[i] = list[i - 1];
		}
		// Add the element to the desired index
		list[index] = e;
		// Increment the size of the ArrayList
		this.size++;
		return true;
	}

	/**
	 * Removes the element at the specified position in this list (optional
	 * operation). Shifts any subsequent elements to the left (subtracts one from
	 * their indices). Returns the element that was removed from the list.
	 * This code is reused from https://github.ncsu.edu/engr-csc216-fall2017/csc216-221-LL-8.git
	 *
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *             index >= size())
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the accepatble range");
		}
		// Get the element at the specified index
		E temp = list[index];
		for (int i = index; i < this.size() - 1; i++) {
			list[i] = list[i + 1];
		}
		// Set the repeated element at the end of the list to null
		list[this.size() - 1] = null;
		// Decrement the size
		this.size--;
		// Return the removed element
		return temp;
	}

	/**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
     *             index >= size())
     */
	@Override
	public E get(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the accepatble range");
		} else {
			return list[index];
		}
	}

	/**
	 * Returns true if this list contains the specified element.
	 *
	 * @param e element whose presence in this list is to be tested
	 * @return true if this list contains the specified element, false otherwise
	 */
	@Override
	public boolean contains(E e) {
		return (binarySearch(e) != -1);
	}

	/**
     * Returns the index of the first occurrence of the specified element in this
     * list, or -1 if this list does not contain the element.
     *
     * @param e element to search for
     * @return the index of the first occurrence of the specified element in this
     *         list, or -1 if this list does not contain the element
     */
	@Override
	public int indexOf(E e) {
		return binarySearch(e);
	}
	
	/**
	 * Searches the list for a specified element and gets its index if it is actually in the list.
	 * Precondition: the elements in the list must be in sorted order
	 * Code taken from https://pages.github.ncsu.edu/engr-csc216-staff/CSC216-SE-Materials/lectures/Heckman/slides/25_Searching.pdf
	 * 
	 * @return the index of the element in the list, or -1 if the list does not contain the element
	 */
	private int binarySearch(E e) {
		//If the element being searched for is null
		if (e == null) {
			throw new NullPointerException("List cannot have null elements");
		//If the list is empty
		} else if (size == 0) {
			return -1;
		}
		
		//Initialize the min and max indices 
		int min = 0;
		int max = size - 1;
		
		while (min <= max) {
			int mid = (min + max)/2;
			//If the currently indexed element in the list lexicographically precedes the element being compared
			if (get(mid).compareTo(e) < 0) {
				min = mid + 1;
			//The currently indexed element in the list lexicographically follows the element being compared
			} else if (get(mid).compareTo(e) > 0) {
				max = mid - 1;
			//The elements are equal
			} else {
				return mid;
			}
		}
		//The element wasn't found in the list
		return -1;
	}
	
	/**
	 * Returns a comma-separated string representation of the data in the SortedArrayList
	 * 
	 * @return the string representation of the list
	 */
	@Override
	public String toString() {
		if (size() == 0) {
			return "[]";
		}
		//Fence-post
		String out = "[" + get(0).toString();
		//Concatenate the string
		for (int i = 1; i < size; i++) {
			out += ", " + get(i);
		}
		out += "]";
		return out;
	}

	/**
	 * Generates a unique hash value for the object
	 * 
	 * @return the hash value
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + Arrays.hashCode(list);
		result = prime * result + size;
		return result;
	}

	/**
	 * Compares two SortedArrayLists for equality based on their contained data.
	 * 
	 * @return true if the SortedArrayLists are equal, false if they are not or the
	 * 		   passed object is not of type SortedArrayList
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SortedArrayList<E> other = (SortedArrayList<E>) obj;
		if (capacity != other.capacity)
			return false;
		if (!Arrays.equals(list, other.list))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	
}
