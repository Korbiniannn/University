def remove_odd_indices(lst, odd, weight):
    """
    lst is a list, odd is a boolean. Return a new list with elements from ls
    removed at certain indices: If odd is True, remove elements at odd indic
    otherwise remove even indexed elements. The remaining elements are
    multiplied by weight.
    >>> s = [1, 2, 3, 4]
    >>> t = remove_odd_indices(s, True, 1)
    >>> s
    [1, 2, 3, 4]
    >>> t
    [1, 3]
    >>> l = [5, 6, 7, 8]
    >>> m = remove_odd_indices(l, False, 4)
    >>> m
    [24, 32]
    """
    #YOUR CODE HERE
    if odd == True:
        return([i * weight for i in lst[::2]])
    else:
        return ([i * weight for i in lst[::-1][::2][::-1]])
    
def max_product(lst):
    """
    Let lst be a list of integers. Return the maximum product that can
    be formed using non-consecutive elements of lst.
    >>> max_product([10,3,1,9,2]) # 10 * 9
    90
    >>> max_product([5,10,5,10,5]) # 5 * 5 * 5
    125
    >>> max_product([])
    1
    """
    # YOUR CODE HERE
    k = []
    if len(lst) == 0:
        return 1
    elif len(lst) == 1:
        return lst[0]
    for (idx, elem) in enumerate(lst):
        if idx == 0:
            k.append(elem)
        elif idx == 1:
            k.append(max(elem, k[0]))
        else:
            k.append(max(elem * k[idx - 2], k[idx - 1]))
    return k[-1]

def merge(lst1, lst2):
    """Merges two sorted lists, lst1 and lst2. The new list contains all
    elements in sorted order.
    >>> merge([1, 3, 5], [2, 4, 6])
    [1, 2, 3, 4, 5, 6]
    >>> merge([], [2, 4, 6])
    [2, 4, 6]
    >>> merge([1, 2, 3], [])
    [1, 2, 3]
    >>> merge([5, 7], [2, 4, 6])
    [2, 4, 5, 6, 7]
    """
    # YOUR CODE HERE
    lst1.extend(lst2)
    for i in range(0, len(lst1)):
        for j in range(i+1, len(lst1)):
            if lst1[i] > lst1[j]:
                lst1[i], lst1[j] = lst1[j], lst1[i]
    return lst1

def alternatingSum(lst):
    """
    Return the alternating sum of the integer or float elements
    in the list lst. An alternating sum of a sequence is a sum
    where the sign alternates from positive to negative or
    vice versa.
    >>> alternatingSum([5, 3, 8, 4])
    6
    >>> alternatingSum([])
    0
    """
    # YOUR CODE HERE.
    new_lst = []
    for (idx, num) in enumerate(lst[::-1]):
        if idx % 2 == 0:
            num = -num
            new_lst.append(num)
        else:
            num = num
            new_lst.append(num)
    sum = 0
    for x in new_lst:
    
        sum += x
    return sum

def is_anagram(s1, s2):
    """
    Decide whether a string s1 and a string s2 are anagrams.
    Use only lists in your solution.
    >>> is_anagram("","")
    True
    >>> is_anagram("abCdabCd","abcdabcd")
    True
    >>> is_anagram("abcdaabcd","aabbcddcb")
    False
    """
    # YOUR CODE HERE
    if s1.lower() == s2.lower():
        return True
    else:
        return False

def rotateStringLeft(s, n):
    """
    The function takes a string s and a possibly-negative integer n.
    If n is non-negative, the function returns the string s rotated
    n places to the left. If n is negative, the function returns
    the string s rotated |n| places to the right.
    >>> rotateStringLeft('abcd', 1)
    'bcda'
    >>> rotateStringLeft('abcd', -1)
    'dabc'
    """
    # YOUR CODE HERE
    if n == 0:
        return s
    else:
        new_s = s[n:] + s[:n]
        new_s = str(new_s)
        return new_s
    
def caesar_cipher(message, shift):
    """
    A Caesar Cipher is a simple cipher that works by shifting
    each letter in the given message by a certain number. For
    example, if we shift the message "We Attack At Dawn" by 1
    letter, it becomes "Xf Buubdl Bu Ebxo".

    ceasar_cipher(message, shift) shifts the given message by
    'shift' many letters. You are guaranteed that 'message' is a
    string, and that 'shift' is an integer between -25 and 25.
    Capital letters should stay capital and lowercase letters
    should stay lowercase, and non-letter characters should
    not be changed. "Z" wraps around to "A".
    >>> caesar_cipher("We Attack At Dawn", 1)
    "Xf Buubdl Bu Ebxo"
    >>> caesar_cipher("zodiac",-2)
    "xmbgya"
    """
    # YOUR CODE HERE

# DICTIONARIES

def most_frequent(L):
    """
    Return the most frequent element in a list L, resolving
    ties arbitrarily. L only has elements of scalar types.
    >>> most_frequent([2,5,3,4,6,4,2,4,5])
    4
    >>> most_frequent([2,3,4,3,5,3,6,3,7])
    3
    >>> most_frequent([42])
    42
    >>> most_frequent([])
    None
    """
    # YOUR CODE HERE
    new_dct = dict()

    if L == []:
        return print(None)
    for x in L:
        if x in new_dct:
            new_dct[x] += 1
        else:
            new_dct[x] = 1
    current_max = 0
    current_key = 0    
    for key, value in new_dct.items():
        if value > current_max:
            current_max = value
            current_key = key
    return current_key


def most_popular_names():
    """
    Using the information in the file 'popular_names.txt' (see
    files attached to this lab), build two dictionaries, one for
    male names, and one for female names, and return the two
    dictionaries.
    Come up with two reasonable doctests!
    """
    # YOUR CODE HERE
    male_name = {}
    female_name = {}
    with open('poplular_names.txt', 'r') as f:
        for line in f:
            line = line.split()
            if line[0] == '#':
                continue
            male_name[line[1]] = line[2]
            female_name[line[3]] = line[4]
    return male_name, female_name


def word_count():
    """
    Find a text file containing the complete works of
    William Shapespeare attached to this lab (see moodle course website).
    * Find the 20 most common words.
    * How many unique words are used?
    * How many words are used at least 5 times?
    * Write the 200 most common words, and their counts,
    to a file 'top200.txt'
    """
    # YOUR CODE HERE
    most_common = {}
    current_max = 0
    current_key = 0
    with open('shakespeare.txt', 'r') as f:
        # count every word
        for line in f:
            line = line.split()
            for word in line:
                if word in most_common:
                    most_common[word] += 1
                    continue
                most_common[word] = 1
        copie_most_common = most_common.copy()
        # most used word
        for key, value in most_common.items():
            if value > current_max:
                current_max = value
                current_key = key
        # Find the 20 most common words
        most_common_20 = {}
        for _ in range(20):
            maximum = 0
            key_val = 0
            for key, value in most_common.items():
                if value > maximum:
                    maximum = value
                    key_val = key
            most_common_20[key_val] = maximum
            most_common.pop(key_val)
        # return most_common_20
        # unique words
        unique_words = {}
        for key, value in most_common.items():
            if value == 1:
                unique_words[key] = 1
        # return unique_words
        #words at least 5 times
        min_5_times = 0
        for value in most_common.values():
            if value >= 5:
                min_5_times += 1
        # return min_5_times
        # write to a file
        most_200 = {}
        for _ in range(200):
            great_val = 0
            great_key = 0
            for key, value in copie_most_common.items():
                if value > great_val:
                    great_val = value
                    great_key = key
            most_200[great_key] = great_val
            copie_most_common.pop(great_key)
        with open ('top200.txt', 'w') as f:
            for k, v in most_200.items():
                f.write(f'{k}: {v}\n')
        return most_common_20, unique_words, min_5_times



