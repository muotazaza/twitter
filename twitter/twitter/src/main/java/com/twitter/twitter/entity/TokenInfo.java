package com.twitter.twitter.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "sec_token_info")
public class TokenInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(length = 800)
    private String accessToken;

    @NotBlank
    @Column(length = 800)
    private String refreshToken;
    
    private String userAgentText ;
    
    private String localIpAddress;
    
	private String remoteIpAddress;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private AppUser user;


    public TokenInfo(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
