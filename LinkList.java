public class LinkList<T> {
	/**
	 * List element
	 * 
	 * @author drunia
	 *
	 */
	private class Node<T> {
		Node<T> prev, next;
		T value;
	}

	private Node<T> currentNode;
	private Node<T> firstNode, lastNode;
	private int size;

	/**
	 * Add item to list
	 * 
	 * @param value
	 * @return
	 */
	public void add(T item) {
		Node<T> node = new Node<>();
		node.value = item;
		if (firstNode == null) {
			firstNode = node;
			currentNode = node;
		} else {
			lastNode.next = node;
			node.prev = lastNode;
			lastNode = node;
		}
		lastNode = node;
		size++;
	}

	/**
	 * Remove item from list by index
	 * 
	 * @param index
	 * @return result of remove
	 */
	public void removeItem(int index) throws Exception {
		// Set current element to element by index
		getItem(index);
		if (hasPrevItem()) {
			Node<T> tmpNode = currentNode.next;
			currentNode = currentNode.prev;
			currentNode.next = tmpNode;
			if (tmpNode != null) {
				// change reference currentNode.next.prev to this
				tmpNode.prev = currentNode;
			} else {
				// if currentNode - last element in list
				lastNode = currentNode;
			}
		} else if (size > 1) {
			// On first item
			currentNode = currentNode.next;
			currentNode.prev = null;
			firstNode = currentNode;
		} else {
			// Delete last item
			currentNode = null;
			firstNode = null;
			lastNode = null;
		}
		size--;
	}

	/**
	 * Return prev item state
	 * 
	 * @return state - exist or no prev value
	 */
	public boolean hasPrevItem() {
		return (currentNode.prev != null);
	}

	/**
	 * Return next item state
	 * 
	 * @return state - exist or no next value
	 */
	public boolean hasNextItem() {
		return (currentNode.next != null);
	}

	/**
	 * Return current node value
	 * 
	 * @return
	 */
	public T getItem() {
		return currentNode.value;
	}

	/**
	 * Return item by index
	 * 
	 * @param index
	 *            of item in list
	 * @return T
	 */
	public T getItem(int index) throws Exception {
		int maxIndex = this.size - 1;
		if (index < 0 || index > maxIndex) {
			throw new Exception("Index out of bounds!");
		}
		if (index > (maxIndex / 2)) {
			int i = maxIndex - index;
			currentNode = lastNode;
			for (; i > 0; i--) {
				currentNode = currentNode.prev;
			}
		} else {
			currentNode = firstNode;
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.next;
			}
		}
		return getItem();
	}

	/**
	 * 
	 * @return next item in list
	 * @throws Exception
	 *             when next item not exists
	 */
	public T getNextItem() throws Exception {
		if (!hasNextItem()) {
			throw new Exception("No next item!");
		}
		currentNode = currentNode.next;
		return getItem();
	}

	/**
	 * 
	 * @return previous item in list
	 * @throws Exception
	 *             when previous item not exists
	 */
	public T getPrevItem() throws Exception {
		if (!hasPrevItem()) {
			throw new Exception("No previous item!");
		}
		currentNode = currentNode.prev;
		return getItem();
	}

	/**
	 * Return list size
	 * 
	 * @return
	 */
	public int getSize() {
		return this.size;
	}

}
