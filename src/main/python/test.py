import multiprocessing
import time
from datetime import datetime

def print_time():
    while True:
        current_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        print(f"Current Time: {current_time}")
        time.sleep(5)

if __name__ == "__main__":
    print_time()