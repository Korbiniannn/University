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