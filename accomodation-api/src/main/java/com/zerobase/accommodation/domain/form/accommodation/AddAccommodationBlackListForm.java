package com.zerobase.accommodation.domain.form.accommodation;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddAccommodationBlackListForm {

    private Long productId;

    private Long customerId;
    private String description;
}
