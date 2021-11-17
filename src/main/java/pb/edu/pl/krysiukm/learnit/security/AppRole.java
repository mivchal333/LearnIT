package pb.edu.pl.krysiukm.learnit.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppRole {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER"),
    ROLE_MOD("ROLE_MOD");
    private String value;
}
