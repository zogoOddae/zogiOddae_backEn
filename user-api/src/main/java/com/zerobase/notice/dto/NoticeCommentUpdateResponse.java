package com.zerobase.notice.dto;


import com.zerobase.notice.entity.Notice;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeCommentUpdateResponse {
    private String description;
    private LocalDateTime updatedAt;

    public NoticeCommentUpdateResponse(Notice notice) {
        this.description = notice.getDescription();
        this.updatedAt = notice.getUpdatedAt();
    }
}
