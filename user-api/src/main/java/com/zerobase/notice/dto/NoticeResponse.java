package com.zerobase.notice.dto;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class NoticeResponse {
    private Long noticeId;
    private String description;
    private LocalDateTime createdAt;

    private Comment comment;


    public NoticeResponse(Long noticeId, String description, LocalDateTime createdAt) {
        this.noticeId = noticeId;
        this.description = description;
        this.createdAt = createdAt;

    }

    @Data
    @AllArgsConstructor
    public static class Comment {

        private Long noticeId;
        private Long parentId;
        private LocalDateTime updatedAt;
        private String description;


    }
}
