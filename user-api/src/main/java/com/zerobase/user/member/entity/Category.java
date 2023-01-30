package com.zerobase.user.member.entity;


import com.zerobase.notice.entity.Notice;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "category_id", nullable = false)
    private Long category_id;


    private Long id;

    private String name;
    @Getter
    @OneToMany
    private List<Notice> notices = new ArrayList<>();



}
