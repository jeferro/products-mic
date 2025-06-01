import re
import sys
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

    def increase_minor(self):
        self.minor += 1

    def increase_patch(self):
        self.patch += 1

    def release(self):
        self.suffix = None

    def to_string(self):
        result = f"{self.major}.{self.minor}.{self.minor}"

        if self.suffix:
            result += f"-{self.suffix}"

        return result


def increase_version_2(properties_patch: str,
                       group: str,
                       release: bool) -> Version:
    with open(properties_patch, 'rb') as content:
        properties = Properties()
        properties.load(content)

        version = Version.create_from_string(properties["version"].data)

        if group is "minor":
            version.increase_minor()
        elif group is "patch":
            version.increase_patch()

        if release:
            version.release()

        return version


if __name__ == "__main__":
    properties_patch = os.getenv("INPUT_PROPERTIES_PATH", "gradle.properties")
    group = os.getenv("INPUT_GROUP", "patch")
    release = os.getenv("INPUT_RELEASE", False)

    group = sys.argv[2] if len(sys.argv) > 2 else "patch"
    release = bool(sys.argv[2]) if len(sys.argv) > 3 else False

    new_version = increase_version_2(properties_patch, group, release)

    print(new_version.to_string())
