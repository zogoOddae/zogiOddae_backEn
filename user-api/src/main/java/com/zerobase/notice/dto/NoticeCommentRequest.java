package com.zerobase.notice.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeCommentRequest {

    @NotNull
    private Long parentId;
    @NotNull
    private Long memberId;
    @NotBlank
    private String description;


}
