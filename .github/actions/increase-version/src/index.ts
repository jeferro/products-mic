import Version from "./Version";
import core from "@actions/core";
import PropertiesReader from 'properties-reader';

const propertiesPatch = core.getInput("properties-path");
const type = core.getInput("type");

const properties = new PropertiesReader(propertiesPatch);
const version = Version.create_from_version(properties.get("version"));

if (type === "release") {
    version.bumpRelease();
}
else if(type === "hotfix") {
    version.bumpHotfix();
}
else {
    version.bumpSnapshot();
}

const versionStr = version.toString();

properties.set("version", versionStr);

properties.save(propertiesPatch);

core.setOutput("version", versionStr)
