package com.zerobase.user.member.entity;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Criteria;

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
    @OneToMany(mappedBy = "category")
    private List<Notice> notices = new ArrayList<>();


}
