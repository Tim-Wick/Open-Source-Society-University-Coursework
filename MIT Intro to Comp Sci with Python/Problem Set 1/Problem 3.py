# Assume s is a string of lower case characters.
# 
# Write a program that prints the longest substring of s in which the letters occur in alphabetical order. For example, 
# if s = 'azcbobobegghakl', then your program should print
# 
# Longest substring in alphabetical order is: beggh
# In the case of ties, print the first substring. For example, if s = 'abcbcd', then your program should print
# 
# Longest substring in alphabetical order is: abc


# s = 'azcbobobegghakl'
s = 'abcbcd'

alphabet = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z"]
long_string = ""
temp_string = ""

for i in range(1, len(s)):
    if alphabet.index(s[i]) >= alphabet.index(s[i-1]):
        temp_string += s[i-1]
    else:
        temp_string += s[i-1]
        if len(temp_string) > len(long_string):
            long_string = temp_string
            temp_string = ""
        else:
            temp_string = ""

print(long_string)
