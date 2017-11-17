package edu.ncsu.csc216.get_outdoors.util;

/**
 * Implementation of a SortedList with a LinkedList as its underlying 
 *   data structure. Elements stored in the list must be Comparable types,
 *   as their order in the list is determined by the return value of
 *   their compareTo() methods, relative to other elements in the list.
 * 
 * @author demills
 * @param <E> the element type to be stored in the list.
 */
public class SortedLinkedList<E extends Comparable<E>> implements SortedList<E> {

	/** A dummy node at the front of the linked list. */
	private Node head;

	/**
	 * Constructs a new SortedLinkedList.
	 */
	public SortedLinkedList() {
		head = new Node(null, null);
	}

	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return returns the size of the list.
	 */
	@Override
	public int size() {
		return head.size(0);
	}

	/**
	 * Returns whether the list is empty. Returns true if there are no elements
	 *   false if there is at least one.
	 * 
	 * @return true if list is empty; false if not.
	 */
	@Override
	public boolean isEmpty() {
		return head.next == null;
	}

	/**
	 * Returns whether the list contains the passed element. The element is 
	 *   identified as being in list if its "equals" method returns true
	 *   when passed any element in the list. A null element passed will
	 *   always return false.
	 *   
	 * @param element the element to check the list for.
	 * @return true if the list contains the element; false otherwise.
	 */
	@Override
	public boolean contains(E element) {
		// Empty list is guaranteed to not contain the element.
		if (size() == 0) {
			return false;
		}
		return head.next.contains(element);
	}

	/**
	 * Adds the passed element to the list in a position according to the natural
	 *   ordering defined by the Comparable element's compareTo() method.
	 * Duplicates are not allowed to be added to the list, as determined by contains().
	 *   
	 * @param element the element to add.
	 * @return true if the element was added to the list; false otherwise.
	 * @throws NullPointerException if the parameter is null.
	 * @throws IllegalArgumentException if the passed element is a duplicate.
	 */
	@Override
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException("Duplicate elements not allowed in list.");
		}
		if (element == null) {
			throw new NullPointerException("Null elements not allowed in list.");
		}
		return head.add(element);
	}

	/**
	 * Returns, without removing, the element at the specified index in the list.
	 * If the index is invalid, an IndexOutOfBoundsException is thrown.
	 * 
	 * @param index at which to retrieve the element.
	 * @return returns the element at the passed index.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
     *             index >= size())
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Invalid index passed. Must be >=0 and <size().");
		}
		return head.get(index);
	}

	/**
	 * Removes and returns the element at the specified index in the list.
	 * 
	 * @param the index in the list to remove the element from.
     * @return the element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
     *             index >= size())
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Invalid index passed. Must be >=0 and <size().");
		}
		return head.remove(index);
	}

	/**
	 * Returns the specified element's index in the list, if it exists. If it
	 *   it doesn't exist in the list, "-1" is returned.
	 * 
	 * @param the element to for.
	 * @return the element's index in the list.
	 */
	@Override
	public int indexOf(E e) {
		return head.indexOf(e, 0);
	}
	
	/**
	 * An inner class for the individual nodes in the linked list. Each Node stores
	 *   an element E, and a reference to the next Node in the list, which may be null.
	 *   
	 * @author demills
	 * @param <E> the element type stored in the list.
	 */
	private class Node {
		
		/** The element stored in this node of the linked list. */
		E data;
		/** The next node in the linked list. */
		Node next;

		/** 
		 * Constructs a new Node, setting its stored element to the passed element
		 *   and the passed Node as its reference to the next element in the list.
		 * Both arguments permit null parameters to be passed, but a Node(null, null) will
		 *   only constructed for the list's head, a dummy element. Otherwise, all Nodes
		 *   in the list will always have non-null pointer to an element.
		 *   
		 * @param data the stored element.
		 * @param next the next Node in the list.
		 */
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * Returns, without removing, the element at the specified index in the list.
		 * If the index is invalid, an IndexOutOfBoundsException is thrown.
		 * 
		 * @param index at which to retrieve the element.
		 * @return returns the element at the passed index.
	     */
		public E get(int index) {
			if (index == 0) {
				return next.data;
			} else {
				return next.get(index - 1);
			}
		}

		public int indexOf(E e, int index) {
			if (next.data.equals(e)) {
				return index;
			} else {
				return next.indexOf(e, index + 1);
			}
		}

		/**
		 * Adds the passed element to the list in a position according to the natural
		 *   ordering defined by the Comparable element's compareTo() method.
		 * Duplicates are not allowed to be added to the list, as determined by contains().
		 *   
		 * @param element the element to add.
		 * @return returns true if the element was added to the list.
		 */
		public boolean add(E element) {
			if (next == null || next.data.compareTo(element) < 0) {
				next = new Node(element, next);
				return true;
			} else {
				return next.add(element);
			}
		}

		/**
		 * Returns the current index of the node, if the next field is null.
		 * Since the dummy "head" node is at index 0, if it has no next element,
		 *   size() would return 0.
		 *   
		 * @param index the index in the list of the this Node.
		 * @return size of the list.
		 */
		public int size(int index) {
			// The element at the zeroth index is the "head" nodex. 
			if (next == null) {
				return index;
			} else {
				return next.size(index++);
			}
		}
		
		/**
		 * Returns whether this Node or any of its successors contain the passed element.
		 * 
		 * @param the element to check for.
		 * @return true if this Node or any successors contain the element; false otherwise.
		 */
		public boolean contains(E element) {
			// Case at the end of the list, of any size.
			if (data.equals(element)) {
				return true;
			} else if (next == null) {
				return false;
			} else {
				return next.contains(element);
			}
		}

		/**
		 * Removes and returns the element at the specified index in the list.
		 * 
		 * @param index the index at which to remove an element.
		 * @return the removed element.
		 */
		public E remove(int index) {
			// Index equaling zero indicates that the next element is the one to be removed.
			if (index == 0) {
				E temp = next.data;
				next = next.next;
				return temp;
			} else {
				return next.remove(index - 1);
			}
		}
	}
}