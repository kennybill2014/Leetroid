Question:
A linked list is given such that each node contains an additional random pointer that could point to any node in the list or null.
Return a deep copy of the list.

public RandomListNode copyRandomList(RandomListNode head) {
   RandomListNode p = head;
   while (p != null) {
      RandomListNode next = p.next;
      RandomListNode copy = new RandomListNode(p.label);
      p.next = copy;
      copy.next = next;
      p = next;
   }
   p = head;
   while (p != null) {
      p.next.random = (p.random != null) ? p.random.next : null;
      p = p.next.next;
   }
   p = head;
   RandomListNode headCopy = (p != null) ? p.next : null;
   while (p != null) {
      RandomListNode copy = p.next;
      p.next = copy.next;
      p = p.next;
      copy.next = (p != null) ? p.next : null;
}
   return headCopy;
}
