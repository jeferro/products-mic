"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const Version_1 = __importDefault(require("./Version"));
test('Create from release version', () => {
    const version = Version_1.default.create_from_version("1.2.0");
    expect(version.getMajor()).toBe(1);
    expect(version.getMinor()).toBe(2);
    expect(version.getPatch()).toBe(0);
    expect(version.isSnapshot()).toBe(false);
});
test('Create from snapshot version', () => {
    const version = Version_1.default.create_from_version("1.2.0-SNAPSHOT");
    expect(version.getMajor()).toBe(1);
    expect(version.getMinor()).toBe(2);
    expect(version.getPatch()).toBe(0);
    expect(version.isSnapshot()).toBe(true);
});
test('Generate new hotfix', () => {
    const version = Version_1.default.create_from_version("1.2.0");
    version.bumpHotfix();
    expect(version.getMajor()).toBe(1);
    expect(version.getMinor()).toBe(2);
    expect(version.getPatch()).toBe(1);
    expect(version.isSnapshot()).toBe(false);
});
test('Generate new release from snapshot', () => {
    const version = Version_1.default.create_from_version("1.2.0-SNAPSHOT");
    version.bumpRelease();
    expect(version.getMajor()).toBe(1);
    expect(version.getMinor()).toBe(2);
    expect(version.getPatch()).toBe(0);
    expect(version.isSnapshot()).toBe(false);
});
test('Generate next snapshot from release', () => {
    const version = Version_1.default.create_from_version("1.2.0");
    version.bumpSnapshot();
    expect(version.getMajor()).toBe(1);
    expect(version.getMinor()).toBe(3);
    expect(version.getPatch()).toBe(0);
    expect(version.isSnapshot()).toBe(false);
});
test('Generate next snapshot from hotfix', () => {
    const version = Version_1.default.create_from_version("1.2.1");
    version.bumpSnapshot();
    expect(version.getMajor()).toBe(1);
    expect(version.getMinor()).toBe(3);
    expect(version.getPatch()).toBe(0);
    expect(version.isSnapshot()).toBe(false);
});
//# sourceMappingURL=Version.test.js.map