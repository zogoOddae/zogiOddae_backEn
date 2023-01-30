package com.zerobase.notice.dto;


import com.zerobase.common.type.MemberRole;
import com.zerobase.notice.NoticeStatus;
import com.zerobase.notice.entity.Notice;
import com.zerobase.user.member.entity.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {

    @Id
    private Long Id;
    private Member memberId;



    @NotEmpty(message = "제목은 필수 입력 값입니다.")
    private String subject;

    @NotEmpty(message = "내용은 필수 입력 값입니다.")
    private String description;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    private String reply;

    private NoticeStatus status;

    private MemberRole memberRole;

    /*
    private static ModelMapper modelMapper = new ModelMapper();

    public static NoticeDto of(Notice notice){
        return modelMapper.map(notice, NoticeDto.class);
    }

     */



    public static NoticeDto from(Notice notice) {
        return NoticeDto.builder()
                .Id(notice.getId())
                .memberId(notice.getMemberId())
                .subject(notice.getSubject())
                .description(notice.getDescription())
                .regTime(notice.getCreatedAt())
                .updateTime(notice.getUpdateTime())
                .reply(notice.getReply())
                .status(notice.getStatus())
                .build();
    }


    private List<Notice> noticeList = new ArrayList<>();



}
