Question:
Design and implement a TwoSum class. It should support the following operations: add and find.
add(input) – Add the number input to an internal data structure.
find(value) – Find if there exists any pair of numbers which sum is equal to the value.
For example,
add(1); add(3); add(5); find(4)->true; find(7)->false

Solution:
add – O(n) runtime, find – O(1) runtime, O(n2) space – Store pair sums in hash table:
We could store all possible pair sums into a hash table. The extra space needed is in the order of O(n2). You would also need an extra O(n) space to store the list of added numbers. Each add operation essentially go through the list and form new pair sums that go into the hash table. The find operation involves a single hash table lookup in O(1) runtime.
This method is useful if the number of find operations far exceeds the number of add operations.

add – O(log n) runtime, find – O(n) runtime, O(n) space – Binary search + Two pointers:
Maintain a sorted array of numbers. Each add operation would need O(log n) time to insert it at the correct position using a modified binary search (See Question [48. Search Insert Position]). For find operation we could then apply the [Two pointers] approach in O(n) runtime.

add – O(1) runtime, find – O(n) runtime, O(n) space – Store input in hash table:
A simpler approach is to store each input into a hash table. To find if a pair sum exists, just iterate through the hash table in O(n) runtime. Make sure you are able to handle duplicates correctly.

public class TwoSum {
    private Map<Integer, Integer> table = new HashMap<>();
    
    public void add(int input) {
	int count = table.containsKey(input) ? table.get(input) : 0	;
	table.put(input, count + 1);
    }
    
    public boolean find(int val) {
        for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
	    int num = entry.getKey();
	    int y = val - num;
    	    if (y == num) {
	        if (entry.getValue() >= 2) return true;
	    } else if (table.containsKey(y)) {
		return true;
	    }
	}
	return false;
    }
}
