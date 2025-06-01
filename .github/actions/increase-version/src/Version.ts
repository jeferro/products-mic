
export default class Version {

    private static readonly VERSION_REGEX: RegExp = /(\d+)\.(\d+)\.(\d+)(-.*)?/;

    constructor(
        private major: number,
        private minor: number,
        private patch: number,
        private snapshot: boolean
    ) {
    }

    static create_from_version(value: string): Version {
        const matches = this.VERSION_REGEX.exec(value);

        console.log(matches);

        if (!matches) {
            throw new Error("Invalid version format: " + value);
        }

        return new Version(
            Number(matches[1]),
            Number(matches[2]),
            Number(matches[3]),
            matches[4] !== null
        );
    }

    public getMajor(): number {
        return this.major;
    }

    public getMinor(): number {
        return this.minor;
    }

    public getPatch(): number {
        return this.patch;
    }

    public isSnapshot(): boolean {
        return this.snapshot;
    }

    public bumpRelease() {
        this.snapshot = false;
    }

    public bumpHotfix() {
        this.patch += 1;
        this.snapshot = false;
    }

    public bumpSnapshot() {
        this.minor += 1;
        this.patch = 0;
        this.snapshot = true;
    }

    public toString() {
        let result = "${this._major}";
    }
}