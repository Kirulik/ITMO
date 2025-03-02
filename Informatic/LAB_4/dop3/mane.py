

from pathlib import Path

import time
from mane_task.mane import parse as parse_mane_task
from dop1.mane import parse as parse_dop1
from dop2.mane import parse as parse_dop2

def check_time(function, src):
    start_time = time.time()
    for _ in range(100):
        function(src)
    print("%s seconds" % (time.time() - start_time))

if __name__ == "__main__":
    string = Path(__file__).parent.parent.joinpath("files").joinpath("Schedule_1.yaml").read_text(encoding="utf-8")

    print("\n= mane_task =")
    check_time(parse_mane_task, string)

    print("\n= dop1 =")
    check_time(parse_dop1, string)

    print("\n= dop2 =")
    check_time(parse_dop2, string)