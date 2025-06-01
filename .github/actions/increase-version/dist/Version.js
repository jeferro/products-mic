"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class Version {
    constructor(major, minor, patch, snapshot) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.snapshot = snapshot;
    }
    static create_from_version(value) {
        const matches = this.VERSION_REGEX.exec(value);
        console.log(matches);
        if (!matches) {
            throw new Error("Invalid version format: " + value);
        }
        return new Version(Number(matches[1]), Number(matches[2]), Number(matches[3]), matches[4] !== null);
    }
    getMajor() {
        return this.major;
    }
    getMinor() {
        return this.minor;
    }
    getPatch() {
        return this.patch;
    }
    isSnapshot() {
        return this.snapshot;
    }
    bumpRelease() {
        this.snapshot = false;
    }
    bumpHotfix() {
        this.patch += 1;
        this.snapshot = false;
    }
    bumpSnapshot() {
        this.minor += 1;
        this.patch = 0;
        this.snapshot = true;
    }
    toString() {
        let result = "${this._major}";
    }
}
Version.VERSION_REGEX = /(\d+)\.(\d+)\.(\d+)(-.*)?/;
exports.default = Version;
//# sourceMappingURL=Version.js.map