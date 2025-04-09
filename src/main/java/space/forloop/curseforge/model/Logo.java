package space.forloop.curseforge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Logo {
    private long id;
    private long modID;
    private String title;
    private String description;
    private String thumbnailURL;
    private String url;
}
