import VersionUpdater from "./VersionUpdater";

(async () => {
    const versionUpdater = new VersionUpdater();
    const versionStr = await versionUpdater.update("gradle.properties", "snapshot");

    console.log(versionStr);
})();

