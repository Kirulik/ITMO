


from tasks import task1_test, task2_test

task = input("Enter task number (1-3) you want to run: ")
if task == '1': task1_test.start_test()
elif task == '2': task2_test.start_test()