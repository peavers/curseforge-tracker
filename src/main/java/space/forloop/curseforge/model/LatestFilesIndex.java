package space.forloop.curseforge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class LatestFilesIndex {
    private String gameVersion;
    private long fileID;
    private String filename;
    private long releaseType;
    private long gameVersionTypeID;
}
