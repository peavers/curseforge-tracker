package space.forloop.curseforge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private long id;
    private long gameID;
    private String name;
    private String slug;
    private String url;
    private String iconURL;
    private OffsetDateTime dateModified;
    private boolean isClass;
    private long classID;
    private long parentCategoryID;
}
