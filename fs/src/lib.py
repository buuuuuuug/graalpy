import pandas as pd
import numpy as np
def add(a: int, b: int) -> int:
    print("hhhhhhhhhhhh")
    print(np.zeros(5))
    return a + b

def pandas_test():
    # 创建一个简单的 DataFrame
    data = {'Name': ['Google', 'Runoob', 'Taobao'], 'Age': [25, 30, 35]}
    df = pd.DataFrame(data)

    # 查看 DataFrame
    print(df)