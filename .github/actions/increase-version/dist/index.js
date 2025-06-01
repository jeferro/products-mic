"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const Version_1 = __importDefault(require("./Version"));
const core_1 = __importDefault(require("@actions/core"));
const properties_reader_1 = __importDefault(require("properties-reader"));
const propertiesPatch = core_1.default.getInput("properties-path");
const type = core_1.default.getInput("type");
const properties = new properties_reader_1.default(propertiesPatch);
const version = Version_1.default.create_from_version(properties.get("version"));
if (type === "release") {
    version.bumpRelease();
}
else if (type === "hotfix") {
    version.bumpHotfix();
}
else {
    version.bumpSnapshot();
}
const versionStr = version.toString();
properties.set("version", versionStr);
properties.save(propertiesPatch);
core_1.default.setOutput("version", versionStr);
//# sourceMappingURL=index.js.map