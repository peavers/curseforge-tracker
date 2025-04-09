package space.forloop.curseforge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {
    private String name;
    private long fingerprint;
}
