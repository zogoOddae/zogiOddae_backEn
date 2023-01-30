package com.zerobase.notice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NoticeCommentUpdateRequest {

    @NotNull
    private Long noticeId;
    @NotBlank
    private String description;

}
