
import re

def decision(line):
    """ Возвращает количество смайликов вида X-{P в строке
    471872 % 6 = 2 => Глаза: X
    471872 % 5 = 2 => Нос: -{
    471872 % 7 = 2 => Рот: P
    """
    pattern = "X-{P"
    return len(re.findall(pattern, line))


if __name__ == '__main__':
    print(decision(input()))
