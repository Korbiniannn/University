import random as r
from time import perf_counter

sample1 = r.sample(range(0,1000000), 1000000)



def binary_search(L,x):
    """
    >>> binary_search([1,2,3,4,5,6,7,8], 6)
    True
    >>> binary_search([], 5)
    False
    >>> binary_search([4,5,6,7,8,9,10], 10)
    True
    >>> binary_search([4,5,6,7,8,9,], 10)
    False
    """
    if L ==[]:
        return False
    if len(L) == 1:
        if L[0] == x:
            return True
        else:
            return False
    else:
        middle = len(L) // 2
        if L[middle] > x:
            return binary_search(L[:middle], x)
        elif L[middle] < x:
            return binary_search(L[middle:], x)
        else:
            return True

def linear_search(L, x):
    for elem in L:
        if elem == x:
            return True
    return False

def bubb_sort(L1):
    """
    >>> bubb_sort([3, 2, 4, 1])
    [1, 2, 3, 4]
    >>> bubb_sort([1, 3, 5, 7])
    [1, 3, 5, 7]
    """
    for idx in range(0, len(L1)):
        for idx2 in range(idx + 1, len(L1)):
            if L1[idx] > L1[idx2]:
                L1[idx], L1[idx2] = L1[idx2], L1[idx]

    return L1

def select_sort(L1):
    """
    >>> select_sort([2, 1, 3, 4, 1, 6, 2, 1, 4, 8])
    [1, 1, 1, 2, 2, 3, 4, 4, 6, 8]
    >>> select_sort([3, 2, 4, 1])
    [1, 2, 3, 4]
    >>> select_sort([1, 3, 5, 7])
    [1, 3, 5, 7]
    """
    i = 0
    for idx_elem in range(len(L1)):
        min_elem = idx_elem
        for pos in range(i, len(L1)):
            if L1[min_elem] > L1[pos]:
                min_elem = pos
        i += 1
        L1[idx_elem], L1[min_elem] = L1[min_elem], L1[idx_elem]

    return L1

def insert_sort(L1):
    """
    >>> insert_sort([2, 1, 3, 4, 1, 6, 2, 1, 4, 8])
    [1, 1, 1, 2, 2, 3, 4, 4, 6, 8]
    >>> insert_sort([3, 2, 4, 1])
    [1, 2, 3, 4]
    >>> insert_sort([1, 3, 5, 7])
    [1, 3, 5, 7]
    """
    i = 0
    while i < len(L1):
        key = i
        while key > 0:
            if L1[key - 1] > L1[key]:
                L1[key -1], L1[key] = L1[key], L1[key -1]
            key -= 1
        i += 1
    return L1

def merge_sort(L):
    if len(L) < 2:
        return L[:]
    else:
        middle = len(L) // 2
        left = merge_sort(L[:middle])
        right = merge_sort(L[middle:])
        return merge(left, right)
    
def merge(left, right):
    result = []
    i, j = 0, 0 
    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    while i < len(left):
        result.append(left[i])
        i += 1
    while j < len(right):
        result.append(right[j])
        j += 1
    return result