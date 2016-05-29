package cn.designthoughts.sample.axon.sfav.customer.entry;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import cn.designthoughts.sample.axon.sfav.customer.domain.Address;
import cn.designthoughts.sample.axon.sfav.customer.domain.Category;
import cn.designthoughts.sample.axon.sfav.customer.domain.EmailAddress;
import cn.designthoughts.sample.axon.sfav.customer.domain.Role;
import cn.designthoughts.sample.axon.sfav.customer.domain.Status;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class CustomerEntry extends AbstractEntity {

    @Id
    private String id;

    private String nickName;
    
    private String legalName;
    
    private String avatarUrl;
    
    private String personalHomePageUrl;
    
    @Column(unique=true)
    private String mobileNumber;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(unique = true)
	private EmailAddress emailAddress;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customer_id")
	private Set<AddressEntry> addresses = new HashSet<AddressEntry>();
	
	private String creationDate;


}