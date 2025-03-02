
from tasks.task2 import decision

def start_test():
    #1
    data = "VT отдел ИТМО и ВТ лаборатория ИТМО представили совместный проект"
    result = ['ВТ лаборатория ИТМО']
    print(f'task2_test1: {result==decision(data)}')

    #2
    data = "Студенты ВТ специальности ИТМО, работающие в ВТ центре ИТМО, добились успеха"
    result = ['ВТ специальности ИТМО', 'ВТ центре ИТМО']
    print(f'task2_test2: {result==decision(data)}')

    #3
    data = "На конференции ВТ специалисты ИТМО рассказали о новых технологиях, разработанных в  ВТ отделе itmo"
    result = ['ВТ специалисты ИТМО']
    print(f'task2_test3: {result==decision(data)}')

    #4
    data = "ВТ факультет ИТМО и ВТ кафедра ИТМО активно сотрудничают, результаты их совместной работы —  ВТ разработки itmo"
    result = ['ВТ факультет ИТМО', 'ВТ кафедра ИТМО']
    print(f'task2_test4: {result==decision(data)}')

    #5
    data = "ВТ проекты itmo, реализованные ВТ командой ИТМО и ВТ отделом ИТМО, получили высокую оценку экспертов"
    result = ['ВТ командой ИТМО', 'ВТ отделом ИТМО']
    print(f'task2_test5: {result==decision(data)}')

if __name__ == '__main__':
    start_test()