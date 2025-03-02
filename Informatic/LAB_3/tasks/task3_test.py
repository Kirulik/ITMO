from tasks.task3 import decision

def start_test():
    #1
    data = "ijvjirj1111___.ber@gmail.com"
    result = "gmail.com"
    print(f'task1_test1: {result==decision(data)}')

    #2
    data = ""
    result = "Fail!"
    print(f'task1_test2: {result==decision(data)}')

    #3
    data = "fvyugeyvgg.vuiuuhv$$$@gmail.com"
    result = "Fail!"
    print(f'task1_test3: {result==decision(data)}')

    #4
    data = "1@c.c"
    result = "c.c"
    print(f'task1_test4: {result==decision(data)}')

    #5
    data = "eihvieueuvhiu"
    result = "Fail!"
    print(f'task1_test5: {result==decision(data)}')

if __name__ == '__main__':
    start_test()