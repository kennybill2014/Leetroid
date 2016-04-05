Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

UPDATE (2016/2/13):
The return format had been changed to zero-based indices. Please read the above updated description carefully.


public int[] twoSum(int[] numbers, int target) {
   Map<Integer, Integer> map = new HashMap<>();
   for (int i = 0; i < numbers.length; i++) {
      int x = numbers[i];
      if (map.containsKey(target - x)) {
         return new int[] { map.get(target - x), i};
      }
      map.put(x, i);
   }
   throw new IllegalArgumentException("No two sum solution");
}
