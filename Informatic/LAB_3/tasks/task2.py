
import re


def decision(line):
    '''
    Дан текст. Необходимо найти в нём каждый фрагмент, где сначала идёт слово «ВТ»,
    затем не более 4 слов, и после этого идёт слово «ИТМО».
    '''

    pattern = '(ВТ\\W+(?:\\w+\\W+){1,4}ИТМО)'
    return re.findall(pattern, line)




if __name__ == '__main__':
    print(decision(input()))