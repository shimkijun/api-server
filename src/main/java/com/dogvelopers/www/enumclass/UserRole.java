package com.dogvelopers.www.enumclass;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN(0, "ROLE_ADMIN","운영자"),
    USER(1,"ROLE_USER","일반 사용자");

    private Integer id;

    private String title;

    private String description;

    public boolean isCorrectName(String name){
        return name.equalsIgnoreCase(this.title);
    }

    public static UserRole getRoleByName(String title){
        return Arrays.stream(UserRole.values()).filter(r->r.isCorrectName(title)).findFirst().orElseThrow(
                () -> new NoSuchElementException("검색된 권한이 없습니다.")
        );
    }

}
