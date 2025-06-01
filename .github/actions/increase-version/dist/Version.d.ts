export default class Version {
    private major;
    private minor;
    private patch;
    private snapshot;
    private static readonly VERSION_REGEX;
    constructor(major: number, minor: number, patch: number, snapshot: boolean);
    static create_from_version(value: string): Version;
    getMajor(): number;
    getMinor(): number;
    getPatch(): number;
    isSnapshot(): boolean;
    bumpRelease(): void;
    bumpHotfix(): void;
    bumpSnapshot(): void;
    toString(): void;
}
