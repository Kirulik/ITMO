from pathlib import Path


def parse(fileline):
    data = _parse_lines(fileline.strip().split("\n"))
    json_schedule = _to_json(data, 0)
    return json_schedule


def _parse_lines(lines):
    result = {}
    stack = []

    for line in lines:
        line = line.rstrip()
        if not line or line.lstrip().startswith("#"):
            continue

        indent_level = _count_indent(line)
        key, value = _parse_key_value(line.lstrip())

        while len(stack) > indent_level // 2:
            stack.pop()

        if value is None:
            new_dict = {}
            if stack:
                stack[-1][key] = new_dict
            else:
                result[key] = new_dict
            stack.append(new_dict)
        else:
            if stack:
                stack[-1][key] = value
            else:
                result[key] = value

    return result


def _count_indent(line):
    return len(line) - len(line.lstrip())


def _parse_key_value(line):
    if ": " in line:
        key, value = line.split(": ", 1)
        return key.strip(), _convert_value(value.strip())
    elif line.endswith(":"):
        return line[:-1].strip(), None
    else:
        raise ValueError(f"Ошибка в синтаксисе YAML: {line}")


def _convert_value(value):
    if value.lower() in ("true", "false"):
        return value.lower() == "true"
    elif value.lower() == "null":
        return None
    elif value.isdigit():
        return int(value)
    elif _is_float(value):
        return float(value)
    else:
        return value.strip('"').strip("'")


def _is_float(value) -> bool:
    try:
        float(value)
        return True
    except ValueError:
        return False


def _to_json(obj, indent_level):
    indent = " " * (indent_level * 4)
    if isinstance(obj, dict):
        json_str = "{\n"
        items = []
        for key, value in obj.items():
            formatted_value = _to_json(value, indent_level + 1)
            items.append(f'{indent}    "{key}": {formatted_value}')
        json_str += ",\n".join(items) + "\n" + indent + "}"
        return json_str
    elif isinstance(obj, str):
        return f'"{obj}"'
    else:
        return str(obj)


def main() -> None:
    yaml_path = Path(__file__).parent.parent.joinpath("files").joinpath("Schedule_1.yaml")
    json_path = Path(__file__).parent.parent.joinpath("files").joinpath("Schedule_mane_task.json")

    yaml_schedule = yaml_path.read_text(encoding="utf-8")

    json_schedule = parse(yaml_schedule)

    json_path.write_text(json_schedule, encoding="utf-8")

    print(json_schedule)


if __name__ == "__main__":
    main()