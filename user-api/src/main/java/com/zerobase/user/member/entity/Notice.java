package com.zerobase.user.member.entity;

import com.zerobase.user.base.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Table(name = "notice")
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

//    private List<Member> members = new ArrayList<>();
//
//
//    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    private boolean visibleYn;

    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private String description;
    private LocalDateTime createDate; //
    private LocalDateTime updateDate; //
    private LocalDateTime endTime;

    @ColumnDefault("0")
    private int visit;


    public void createNotice(Member member, Category category, String subject, String description) {
        this.member = member;
        this.category = category;
        this.subject = subject;
        this.description = description;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        this.member.getNotices().add(this);
        this.category.getNotices().add(this);
    }

}
