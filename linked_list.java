public class LinkedList {

    Node head; // Head of the list

    // Inner class for Node
    static class Node {
        int data;
        Node next;

        Node(int d) { // Constructor
            data = d;
            next = null;
        }
    }

    // Method to insert a new node at the end
    public static LinkedList insert(LinkedList list, int data) {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.next = null;

        // If the Linked List is empty, then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        } else {
            // Else traverse till the last node and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            // Insert the new_node at last node
            last.next = new_node;
        }
        return list;
    }

    // Method to print the LinkedList
    public static void printList(LinkedList list) {
        Node currNode = list.head;
        System.out.print("LinkedList: ");
        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.data + " ");
            // Go to next node
            currNode = currNode.next;
        }
        System.out.println();
    }

    // Method to delete a node with given key
    public static LinkedList deleteByKey(LinkedList list, int key) {
        Node currNode = list.head;
        Node prev = null;

        // CASE 1:
        // If head node itself holds the key to be deleted
        if (currNode != null && currNode.data == key) {
            list.head = currNode.next; // Changed head
            System.out.println(key + " found and deleted");
            return list;
        }

        // CASE 2:
        // If the key is somewhere other than at head
        // Search for the key to be deleted,
        // keep track of the previous node as it is needed to change currNode.next
        while (currNode != null && currNode.data != key) {
            prev = currNode;
            currNode = currNode.next;
        }

        // If key was present, it should be at currNode
        // Therefore, currNode shall not be null
        if (currNode != null) {
            prev.next = currNode.next;
            System.out.println(key + " found and deleted");
        }

        // CASE 3: The key is not present
        if (currNode == null) {
            System.out.println(key + " not found");
        }

        return list;
    }

    public static void main(String[] args) {
        // Start with the empty list.
        LinkedList list = new LinkedList();

        // Insert the values
        list = insert(list, 1);
        list = insert(list, 2);
        list = insert(list, 3);
        list = insert(list, 4);
        list = insert(list, 5);

        // Print the LinkedList
        printList(list); // Expected: LinkedList: 1 2 3 4 5

        // Delete node with data 1
        deleteByKey(list, 1);
        printList(list); // Expected: LinkedList: 2 3 4 5

        // Delete node with data 4
        deleteByKey(list, 4);
        printList(list); // Expected: LinkedList: 2 3 5

        // Delete node with data 10 (not present)
        deleteByKey(list, 10);
        printList(list); // Expected: LinkedList: 2 3 5
    }
}

/*
Explanation for Linked List:

A linked list is a linear data structure where elements are not stored at contiguous memory locations. Instead, elements are linked using pointers. Each element in a linked list is called a node. A node has two parts:
1.  Data: The actual value stored in the node.
2.  Next: A pointer (or reference) to the next node in the sequence. The last node's `next` pointer is `null`.

The `head` of the linked list is a pointer to the first node. If `head` is `null`, the list is empty.

`Node` Class:
*   This static nested class defines the structure of each node in the linked list.
*   `data`: Stores the integer value.
*   `next`: A `Node` type reference that points to the next node in the list.
*   Constructor `Node(int d)`: Initializes a new node with the given `data` and sets `next` to `null`.

`insert(LinkedList list, int data)` Method:
*   Purpose: Adds a new node with the given `data` to the end of the linked list.
*   Steps:
    1.  Creates a `new_node` with the provided `data`.
    2.  If `list.head` is `null`: The list is empty. The `new_node` becomes the `head`.
    3.  Else (list is not empty):
        *   It traverses the list starting from `head` until it reaches the last node (where `last.next` is `null`).
        *   The `next` pointer of the last node is then set to the `new_node`, effectively adding it to the end.
    4.  Returns the updated `LinkedList`.

`printList(LinkedList list)` Method:
*   Purpose: Prints all the elements of the linked list.
*   Steps:
    1.  Starts from the `head` node (`currNode`).
    2.  Traverses the list using a `while` loop until `currNode` becomes `null`.
    3.  In each iteration, it prints the `data` of the `currNode` and then moves to the next node by setting `currNode = currNode.next`.

`deleteByKey(LinkedList list, int key)` Method:
*   Purpose: Deletes the first occurrence of a node with the given `key` (data value).
*   Steps:
    1.  Initializes `currNode` to `list.head` and `prev` to `null`.
    2.  Case 1: Deleting the head node:
        *   If `currNode` is not `null` AND its `data` matches `key`, then the `head` is updated to `currNode.next`. The original head is now garbage collected.
        *   A message is printed, and the list is returned.
    3.  Case 2: Deleting a node other than head:
        *   It traverses the list using a `while` loop.
        *   In each iteration, `prev` tracks the node before `currNode`.
        *   The loop continues as long as `currNode` is not `null` and its `data` does NOT match the `key`.
        *   If the `key` is found (`currNode` is not `null` after the loop):
            *   `prev.next` is set to `currNode.next`. This bypasses `currNode`, effectively removing it from the list.
            *   A message is printed.
        *   Case 3: `key` not found:
            *   If the loop finishes and `currNode` is `null`, it means the `key` was not found in the list.
            *   A message indicating `key` not found is printed.
    4.  Returns the updated `LinkedList`.

Dry Run for Linked List:

Let's trace the `main` method execution:

*   Initial: `LinkedList list = new LinkedList();`
    *   `list.head` = `null`

*   `list = insert(list, 1);`
    *   `new_node` (1, null)
    *   `list.head` is null, so `list.head` = `new_node`
    *   List: `1` -> `null`

*   `list = insert(list, 2);`
    *   `new_node` (2, null)
    *   `list.head` is not null. Traverse to last (node 1).
    *   `node1.next` = `new_node` (node 2)
    *   List: `1` -> `2` -> `null`

*   `list = insert(list, 3);`
    *   List: `1` -> `2` -> `3` -> `null`

*   `list = insert(list, 4);`
    *   List: `1` -> `2` -> `3` -> `4` -> `null`

*   `list = insert(list, 5);`
    *   List: `1` -> `2` -> `3` -> `4` -> `5` -> `null`

*   `printList(list);`
    *   Output: `LinkedList: 1 2 3 4 5`

*   `deleteByKey(list, 1);` (Delete head)
    *   `currNode` = `head` (node 1), `prev` = `null`
    *   `currNode.data == 1` is true.
    *   `list.head` = `currNode.next` (node 2)
    *   Output: `1 found and deleted`
    *   List: `2` -> `3` -> `4` -> `5` -> `null`

*   `printList(list);`
    *   Output: `LinkedList: 2 3 4 5`

*   `deleteByKey(list, 4);` (Delete middle)
    *   `currNode` = `head` (node 2), `prev` = `null`
    *   Loop:
        *   `currNode` (node 2). `data` (2) != 4. `prev` = node 2, `currNode` = node 3.
        *   `currNode` (node 3). `data` (3) != 4. `prev` = node 3, `currNode` = node 4.
        *   `currNode` (node 4). `data` (4) == 4. Loop terminates.
    *   `currNode` is not `null`.
    *   `prev.next` (node 3's `next`) = `currNode.next` (node 4's `next`, which is node 5).
    *   Output: `4 found and deleted`
    *   List: `2` -> `3` -> `5` -> `null`

*   `printList(list);`
    *   Output: `LinkedList: 2 3 5`

*   `deleteByKey(list, 10);` (Delete not found)
    *   `currNode` = `head` (node 2), `prev` = `null`
    *   Loop:
        *   `currNode` (node 2). `data` (2) != 10. `prev` = node 2, `currNode` = node 3.
        *   `currNode` (node 3). `data` (3) != 10. `prev` = node 3, `currNode` = node 5.
        *   `currNode` (node 5). `data` (5) != 10. `prev` = node 5, `currNode` = `null`.
        *   Loop terminates as `currNode` is `null`.
    *   `currNode` is `null`.
    *   Output: `10 not found`
    *   List remains: `2` -> `3` -> `5` -> `null`

*   `printList(list);`
    *   Output: `LinkedList: 2 3 5`
*/