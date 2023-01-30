package com.zerobase.user.member.dto.form;


import com.zerobase.notice.NoticeStatus;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class AddNoticeForm {

    private Long noticeId;
    private Long memberId;

    @NotEmpty(message = "제목은 필수 입력 값입니다.")
    private String subject;

    @NotEmpty(message = "내용은 필수 입력 값입니다.")
    private String description;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    private String reply;

    private NoticeStatus status;
}
