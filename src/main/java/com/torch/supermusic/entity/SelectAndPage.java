package com.torch.supermusic.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelectAndPage {

    private int currentPage;
    private int pageSize;
    private String arg;
    private String arg2;

    public String getArg() {
        if (arg==null)
            return "";
        return arg;
    }

    public String getArg2() {
        if (arg2==null)
            return "";
        return arg2;
    }
}
