package com.zerobase.leisure.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddLeisureBlackListForm {

    private Long productId;

    private Long customerId;
    private String description;
}
