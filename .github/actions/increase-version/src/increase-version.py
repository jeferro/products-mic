import re
import os
from dataclasses import dataclass
from typing import Type, TypeVar

from jproperties import Properties

T = TypeVar("T", bound="Version")


@dataclass
class Version:
    major: int
    minor: int
    patch: int
    suffix: str | None

    @classmethod
    def create_from_string(cls: Type[T], value: str) -> T:
        version_match = re.match(r"(\d+)\.(\d+)\.(\d+)(-.*)", value)

        if not version_match:
            raise ValueError(f"Invalid version format: {value}")

        version_groups = version_match.groups()

        return cls(major=int(version_groups[0]),
                   minor=int(version_groups[1]),
                   patch=int(version_groups[2]),
                   suffix=version_groups[3][1:])

    def to_release(self):
        self.suffix = None

    def to_hotfix(self):
        self.patch += 1
        self.suffix = None

    def to_next_snapshot(self):
        self.minor += 1
        self.suffix = "SNAPSHOT"

    def to_string(self):
        result = f"{self.major}.{self.minor}.{self.minor}"

        if self.suffix:
            result += f"-{self.suffix}"

        return result


if __name__ == "__main__":
    properties_patch = os.getenv("INPUT_PROPERTIES_PATH", "gradle.properties")
    type = os.getenv("INPUT_TYPE", "release")

    with open(properties_patch, 'wrb') as file:
        properties = Properties()
        properties.load(file)

        version = Version.create_from_string(properties["version"].data)

        if type is "release":
            version.to_release()
        elif type is "hotfix":
            version.to_hotfix()
        else:
            version.to_next_snapshot()

        properties.store(file, encoding="utf-8")

        print(version.to_string())
