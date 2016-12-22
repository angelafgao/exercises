import string

def word_count(s):
    s = clean_up(s)
    word_list = str.split(s) # create an array of words
    n = len(word_list)
    d = dict() # store words with counts
    for i in range(0,n):
        if word_list[i] == "i": # exception to lowercase rule
            word_list[i] = "I"
        if word_list[i] not in d: # add word to dictionary when not there
            d[word_list[i]] = 0
        d[word_list[i]] += 1 # increment word count by one
    for key in d:
        print (d[key], key)
    return d

# helper function for word_count use to make all words lowercase
# and get rid of punctuation
def clean_up(s):
    for char in string.punctuation:
        s = s.replace("%s" %char, " ") # replace punctuation with space
    return s.lower() # so The and the are counted as the same word


def test_word_count(): # test function
    assert(word_count("hello") == {"hello":1})
    assert(word_count("I") == {"I":1})
    assert(word_count("") == {})
    assert(word_count(" ") == {})
    assert(word_count(";") == {})
    assert(word_count("\n") == {})
    assert(word_count("\n@#$#%@#%!#\t") == {})
    assert(word_count("hello I am Angela") == {"hello":1, "I":1, "am":1, "angela":1})
    assert(word_count("hello I\nam\tAngela") == {"hello":1, "I":1, "am":1, "angela":1})
    assert(word_count("hello! I. am Angela;") == {"hello":1, "I":1, "am":1, "angela":1})
    assert(word_count("hello!\nI am I am I am Angela") == {"hello":1, "I":3, "am":3, "angela":1})
    assert(word_count("hello!\nI am I am I am Angela a a") == {"hello":1, "I":3, "am":3, "angela":1, "a":2})

test_word_count()