import yaml
import json
from pathlib import Path



def parse(yaml_text: str) -> str:
    data = yaml.safe_load(yaml_text)
    return json.dumps(data, indent=4, ensure_ascii=False)



def main() -> None:
    yaml_path = Path(__file__).parent.parent.joinpath("files").joinpath("Schedule_1.yaml")
    json_path = Path(__file__).parent.parent.joinpath("files").joinpath("Schedule_dop1.json")


    yaml_schedule = yaml_path.read_text(encoding="utf-8")

    parsed_data = parse(yaml_schedule)

    with json_path.open("w", encoding="utf-8") as f:
        f.write(parsed_data)




if __name__ == "__main__":
    main()