package space.forloop.curseforge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class SortableGameVersion {
    private String gameVersionName;
    private String gameVersionPadded;
    private String gameVersion;
    private OffsetDateTime gameVersionReleaseDate;
    private long gameVersionTypeID;
}
