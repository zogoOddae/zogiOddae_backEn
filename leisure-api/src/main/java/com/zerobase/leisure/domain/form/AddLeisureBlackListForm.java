package com.zerobase.leisure.domain.form;

import lombok.Getter;

@Getter
public class AddLeisureBlackListForm {

    private Long leisureId;

    private Long customerId;
    private String description;
}
