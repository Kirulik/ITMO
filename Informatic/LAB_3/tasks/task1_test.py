
from tasks.task1 import decision

def start_test():
    #1
    data = "X-{P rbvorhruhvuhX-{PhouX-{PX-{PohrehgergX-{P"
    result = 5
    print(f'task1_test1: {result==decision(data)}')

    #2
    data = ""
    result = 0
    print(f'task1_test2: {result==decision(data)}')

    #3
    data = "X- {PX- {X- {PX-{X-{ P"
    result = 0
    print(f'task1_test3: {result==decision(data)}')

    #4
    data = "X-{PX-{PX-{PX-{P"
    result = 4
    print(f'task1_test4: {result==decision(data)}')

    #5
    data = "X-{P"
    result = 1
    print(f'task1_test5: {result==decision(data)}')

if __name__ == '__main__':
    start_test()