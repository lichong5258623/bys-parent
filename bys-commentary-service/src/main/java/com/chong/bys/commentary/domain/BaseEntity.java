package com.chong.bys.commentary.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author licho
 * @date 2019/3/7 23:58
 */
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 22, unique = true)
    protected Long id;

    @CreatedDate
    @Column(name = "create_date", nullable = false)
    protected Date createDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected Date lastModifiedDate;

    @Version
    @Column(name = "version", nullable = false)
    protected Integer version;
}
