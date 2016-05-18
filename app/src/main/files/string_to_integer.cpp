Question: 
Implement atoi to convert a string to an integer. 
The atoi function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value. 
The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function. 
If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed. 
If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, the maximum integer value (2147483647) or the minimum integer value (–2147483648) is returned. 

private static final int maxDiv10 = Integer.MAX_VALUE / 10;
public int atoi(String str) {
	int i = 0, n = str.length();
	while (i < n && Character.isWhitespace(str.charAt(i))) i++;
	int sign = 1;
	if (i < n && str.charAt(i) == '+') {
		i++;
	} else if (i < n && str.charAt(i) == '-') {
		sign = -1;
		i++; 
	}
	int num = 0;
	while (i < n && Character.isDigit(str.charAt(i))) {
		int digit = Character.getNumericValue(str.charAt(i));
		if (num > maxDiv10 || num == maxDiv10 && digit >= 8) {
			return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}
		num = num * 10 + digit;
		i++; 
	}
	return sign * num;
}
