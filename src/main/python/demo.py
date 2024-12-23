import numpy as np
from termcolor import colored

if __name__ == '__main__':
    ZEROS = np.zeros(5)
    a = [1, 2, 3, 4, 5]
    print(a)
    print(ZEROS)
    colored_text = colored("hello java", "red", attrs=["reverse", "blink"])
    print(colored_text)