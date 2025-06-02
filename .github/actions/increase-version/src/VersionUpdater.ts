import PropertiesReader from 'properties-reader';

import Version from "./Version";

export default class VersionUpdater {

    public async update(propertiesPatch: string,
        type: string): Promise<string> {

        const properties = PropertiesReader(propertiesPatch);
        const version = Version.create_from_version(<string>properties.getRaw("version"));

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

        await properties.save(propertiesPatch);

        return versionStr;
    }
}