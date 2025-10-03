package cue.edu.co.jwttokenprovider.constants;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtClaim {

    ROLE("role"),
    USER_ID("userId");

    private final String claim;

}