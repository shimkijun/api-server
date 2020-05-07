package com.dogvelopers.www.model.entity;

import com.dogvelopers.www.enumclass.SocialProviders;
import com.dogvelopers.www.enumclass.UserRole;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long socialId;

    @NonNull
    private String userId;

    @NonNull
    private String password;

    @NonNull
    private String username;

    @NonNull
    @Enumerated(value= EnumType.STRING)
    private UserRole userRole;

    @Enumerated(value = EnumType.STRING)
    private SocialProviders socialProviders;

    private String profileHref;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

}
