Question:
Given a linked list, swap every two adjacent nodes and return its head.
For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.
Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
Example Questions Candidate Might Ask:
Q: What if the number of nodes in the linked list has only odd number of nodes? A: The last node should not be swapped.

public ListNode swapPairs(ListNode head) {
	ListNode dummy = new ListNode(0);
	dummy.next = head;
	ListNode p = head;
	ListNode prev = dummy;
	while (p != null && p.next != null) {
		ListNode q = p.next, r = p.next.next;
		prev.next = q;
		q.next = p;
		p.next = r;
		prev = p;
		p = r; 
	}
	return dummy.next;
}
