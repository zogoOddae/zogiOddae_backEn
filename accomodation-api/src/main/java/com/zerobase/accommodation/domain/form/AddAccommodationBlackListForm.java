package com.zerobase.accommodation.domain.form;

import lombok.Getter;

@Getter
public class AddAccommodationBlackListForm {

    private Long accommodationId;

    private Long customerId;
    private String description;
}
