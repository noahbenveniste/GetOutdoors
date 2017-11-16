package edu.ncsu.csc216.get_outdoors.util;

/**
 * 
 * @author Noah Benveniste
 * 
 * @param <E>
 */
public class SortedArrayList<E> implements SortedList {

	/** */
	private static final int RESIZE = 10;
	/** */
	private static final int DEFAULT_CAPACITY = 10;
	/** */
	private Comparable[] list;
	/** */
	private int size;
	/** */
	private int capacity;
	
	/**
	 * 
	 */
	public SortedArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 
	 * This code is reused from https://github.ncsu.edu/engr-csc216-fall2017/csc216-221-LL-8.git
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public SortedArrayList(int capacity) {
		Object[] o = new Object[capacity];
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
		Object[] o = new Object[this.capacity];
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
     * Returns true if this list contains the specified element. More formally,
     * returns true if and only if this list contains at least one element a such
     * that (o==null ? a==null : o.equals(a)).
     *
     * @param e element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
	@Override
	public boolean contains(Comparable e) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
     * Adds the specified element to list in sorted order
     *
     * Lists that support this operation may place limitations on what elements may
     * be added to this list. In particular, some lists will refuse to add null
     * elements, and others will impose restrictions on the type of elements that
     * may be added. List classes should clearly specify in their documentation any
     * restrictions on what elements may be added.
     * This code is partially reused from https://github.ncsu.edu/engr-csc216-fall2017/csc216-221-LL-8.git
     *
     * @param e element to be appended to this list
     * @return true (as specified by {@link Collection#add})
     */
	@Override
	public boolean add(Comparable e) {
		if (e == null) {
			throw new NullPointerException("Cannot add null elements");
		}
		for (int i = 0; i < this.size(); i++) {
			if (this.list[i].equals(e)) {
				throw new IllegalArgumentException("Cannot add repeat elements");
			}
		}
		if (this.size() == this.capacity) { // Grow the array if list is full
			this.resize();
		}
		//Case where the element is appended to the end of the list i.e. the element being 
		//added is lexicographically less than the last element in the list
		if (list[this.size - 1].compareTo(e) < 0) {
			
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
	public Comparable get(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the accepatble range");
		} else {
			return list[index];
		}
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
	public Comparable remove(int index) {
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
     * Returns the index of the first occurrence of the specified element in this
     * list, or -1 if this list does not contain the element. More formally, returns
     * the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or
     * -1 if there is no such index.
     *
     * @param e element to search for
     * @return the index of the first occurrence of the specified element in this
     *         list, or -1 if this list does not contain the element
     */
	@Override
	public int indexOf(Comparable e) {
		// TODO Auto-generated method stub
		return 0;
	}

}
