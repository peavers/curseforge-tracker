package space.forloop.curseforge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class LatestFile {
    private long id;
    private long gameID;
    private long modID;
    private boolean isAvailable;
    private String displayName;
    private String fileName;
    private long releaseType;
    private long fileStatus;
    private List<Hash> hashes;
    private OffsetDateTime fileDate;
    private long fileLength;
    private long downloadCount;
    private long fileSizeOnDisk;
    private Object downloadURL;
    private List<String> gameVersions;
    private List<SortableGameVersion> sortableGameVersions;
    private List<Object> dependencies;
    private long alternateFileID;
    private boolean isServerPack;
    private long fileFingerprint;
    private List<Module> modules;
}
