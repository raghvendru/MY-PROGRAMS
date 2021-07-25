#include <stdio.h>
int check_anagram(char [], char []);

int main()
{
  char a[1000], b[1000];

  printf("Enter two strings\n");
  gets(a);
  gets(b);

  if (check_anagram(a, b))
    printf("The strings are anagrams.\n");
  else
    printf("The strings aren't anagrams.\n");

  return 0;
}

int check_anagram(char a[], char b[])
{
  int first[26] = {0}, second[26] = {0}, c=0;

  // Calculating frequency of characters of the first string

  while (a[c] != '\0') {
    first[a[c]-'a']++;
    c++;
  }

  c = 0;

  while (b[c] != '\0') {
    second[b[c]-'a']++;
    c++;
  }

  // Comparing the frequency of characters

  for (c = 0; c < 26; c++)
    if (first[c] != second[c])
      return 0;

  return 1;
}
/*
what is anagram?
Anagram program in C to check whether two strings are anagrams or not. They are assumed to contain only lower case letters. They are anagrams of each other if the letters of one of them can be rearranged to form the other. So, in anagram strings, all characters occur the same number of times. For example, "ABC" and "CAB" are anagrams, as every character, 'A,' 'B,' and 'C' occur the same number of times (one time here) in both the strings.

A user inputs two strings, we count how many times each letter ('a' to 'z') appear in them and then compare their corresponding counts. The frequency of an alphabet in a string is how many times it appears in it. For example, the frequency of the "m alphabet" in the string "programming" is '2' as it's present two times in it.