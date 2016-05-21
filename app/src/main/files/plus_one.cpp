Question:
Given a number represented as an array of digits, plus one to the number.
Example Questions Candidate Might Ask:
Q: Could the number be negative?
A: No. Assume it is a non-negative number.
Q: How are the digits ordered in the list? For example, is the number 12 represented by [1,2] or [2,1]?
A: The digits are stored such that the most significant digit is at the head of the list.
Q: Could the number contain leading zeros, such as [0,0,1]? 
A: No.

public void plusOne(List<Integer> digits) {
	for (int i = digits.size() - 1; i >= 0; i--) {
		int digit = digits.get(i);
		if (digit < 9) {
			digits.set(i, digit + 1);
			return;
		} else {
			digits.set(i, 0);
		}
	}
	digits.add(0);
	digits.set(0, 1);
}
