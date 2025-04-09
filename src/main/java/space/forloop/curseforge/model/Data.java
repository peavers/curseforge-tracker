package space.forloop.curseforge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private List<Logo> screenshots;
    private long id;
    private long gameID;
    private String name;
    private String slug;
    private Links links;
    private String summary;
    private long status;
    private long downloadCount;
    private boolean isFeatured;
    private long primaryCategoryID;
    private List<Category> categories;
    private long classID;
    private List<Author> authors;
    private Logo logo;
    private long mainFileID;
    private List<LatestFile> latestFiles;
    private List<LatestFilesIndex> latestFilesIndexes;
    private List<Object> latestEarlyAccessFilesIndexes;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private OffsetDateTime dateReleased;
    private boolean allowModDistribution;
    private long gamePopularityRank;
    private boolean isAvailable;
    private boolean hasCommentsEnabled;
    private long thumbsUpCount;
    private long featuredProjectTag;
}
