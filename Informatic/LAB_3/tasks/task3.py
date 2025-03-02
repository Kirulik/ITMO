
import re


def decision(line):
    '''
    Написать регулярное выражение, которое проверяет корректность email и в качестве
    ответа выдаёт почтовый сервер (почтовый сервер – часть email идущая после «@»).
    '''

    pattern = r'^[a-zA-Z0-9._]+@[a-zA-Z.]+\.[a-zA-Z]+$'
    pattern2 = r'@([a-zA-Z.]+)$'
    return   re.search(pattern2, line).group(1)  if    re.fullmatch(pattern, line) != None else "Fail!"




if __name__ == '__main__':
    print(decision(input()))