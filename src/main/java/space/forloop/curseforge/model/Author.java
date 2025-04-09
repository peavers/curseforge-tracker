package space.forloop.curseforge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private long id;
    private String name;
    private String url;
    private String avatarURL;
}
