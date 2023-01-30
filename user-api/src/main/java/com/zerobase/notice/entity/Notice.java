package com.zerobase.notice.entity;

import com.zerobase.notice.dto.EditAdminNoticeRequest;
import com.zerobase.notice.dto.NoticeCommentRequest;
import com.zerobase.notice.dto.NoticeCommentResponse;
import com.zerobase.notice.dto.NoticeCommentUpdateRequest;
import com.zerobase.notice.dto.NoticeCommentUpdateResponse;
import com.zerobase.notice.dto.NoticeDto;
import com.zerobase.notice.NoticeStatus;
import com.zerobase.notice.dto.SaveNoticeRequest;
import com.zerobase.type.MemberRole;
import com.zerobase.user.base.entity.BaseEntity;
import com.zerobase.user.member.entity.Member;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import org.hibernate.envers.AuditOverride;

@Entity
@Getter
@Setter
@ToString
@Table(name="notice")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
//@AuditOverride(forClass = BaseEntity.class)
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "notice_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;

    private Long categoryId;

    private Long parentId; // comment parents => noticeId

//    private List<Member> members = new ArrayList<>();
//
//
//    private List<Notice> notices = new ArrayList<>();
//
//    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
//    List<Comment> comments = new ArrayList<>();


    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private String description;
    private LocalDateTime createdAt;

    private LocalDateTime updateTime;

    private String reply;

    @Enumerated(EnumType.STRING) //Enum 타입 매칭
    private com.zerobase.common.type.MemberRole memberRole;

    @Enumerated(EnumType.STRING) //Enum 타입 매칭
    private NoticeStatus status;

    protected Notice() {}

    public Notice(NoticeCommentRequest noticeCommentRequest) {
        this.parentId = noticeCommentRequest.getParentId();
        this.description = noticeCommentRequest.getDescription();
    }

    public Notice(Member member, SaveNoticeRequest request) {
        this.memberId = member;
        this.description = request.getDescription();
    }
    public void addMember(Member member) {
        this.memberId = member;
    }



    public Notice updateNotice(EditAdminNoticeRequest request) {
        this.description = request.getDescription();
        return this;
    }


    public Notice updateComment(NoticeCommentUpdateRequest noticeCommentUpdateRequest) {
        this.description = noticeCommentUpdateRequest.getDescription();
        return this;
    }

    public NoticeCommentResponse toNoticeCommentResponse(Notice notice) {
        return new NoticeCommentResponse(notice);

    }

    public NoticeCommentUpdateResponse toNoticeCommentUpdateResponse(Notice notice) {
        return new NoticeCommentUpdateResponse(notice);
    }













//
//    public void saveNotice(NoticeDto noticeDto, MemberRole memberRole) {
//        this.memberRole = noticeDto.getMemberRole();
//        this.status = noticeDto.getStatus();
//    }
//
//
//    public static void createNotice(Long memberId, Long categoryId, String subject,
//            String description) {
//        Notice  notice =  new Notice();
//        notice.setMemberId(memberId);
//        notice.setCategoryId(categoryId);
//        notice.setSubject(subject);
//        notice.setDescription(description);
//    }
//
//    public Notice(Long id, Long memberId, Long categoryId, NoticeStatus status, String subject,
//            String description, LocalDateTime regTime, LocalDateTime updateTime, String reply
//            ) {
//        this.id = id;
//        this.memberId = memberId;
//        this.categoryId = categoryId;
//        this.status = status;
//        this.subject = subject;
//        this.description = description;
//        this.regTime = regTime;
//        this.updateTime = updateTime;
//        this.reply = reply;
//    }


}
