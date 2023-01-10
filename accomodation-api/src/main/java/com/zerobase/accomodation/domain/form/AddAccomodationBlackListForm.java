package com.zerobase.accomodation.domain.form;

import lombok.Getter;

@Getter
public class AddAccomodationBlackListForm {

    private Long accomodationId;

    private Long customerId;
    private String description;
}
