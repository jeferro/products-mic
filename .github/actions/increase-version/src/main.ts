
import * as core from "@actions/core";
import VersionUpdater from "./VersionUpdater";


(async () => {
    const propertiesPatch = core.getInput("properties-path") || "gradle.properties";
    const type = core.getInput("type") || "release";

    const versionUpdater = new VersionUpdater();
    const versionStr = versionUpdater.update(propertiesPatch, type);

    core?.setOutput("version", versionStr);
})();
