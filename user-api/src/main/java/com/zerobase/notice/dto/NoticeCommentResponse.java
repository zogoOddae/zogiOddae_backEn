package com.zerobase.notice.dto;


import com.zerobase.notice.entity.Notice;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeCommentResponse {
    private Long memberId;
    private String description;
    private Long parentId;
    private LocalDateTime createdAt;

    public NoticeCommentResponse(Notice notice) {
        this.memberId = notice.getMemberId().getId();
        this.description = notice.getDescription();
        this.parentId = notice.getParentId();
        this.createdAt = notice.getCreatedAt();
    }
}
