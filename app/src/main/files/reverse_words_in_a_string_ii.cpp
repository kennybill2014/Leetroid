Question:

Similar to Question [6. Reverse Words in a String], but with the following constraints:
“The input string does not contain leading or trailing spaces and the words are always separated by a single space.”
Could you do it in-place without allocating extra space?

public void reverseWords(char[] s) {
     reverse(s, 0, s.length - 1);
     for (int i = 0, j = 0; j <= s.length; j++) {
         if (j == s.length || s[j] == ‘ ‘) {
              reverse(s, i, j - 1);
              i = j + 1;
          }
     }
}

public void reverse(char[] s, int begin, int end) {
     for (int i = 0; i <= (begin + end) / 2; i++) {
         char temp = s[i];
         s[i] =  s[end - i];
         s[end - i] = temp;
     }
}
