package br.com.softblue.bluebank.domain.user;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.UUID;

import java.util.List;

import br.com.softblue.bluebank.domain.account.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = UUID)
	private String id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String cpf;
	
	@OneToMany(mappedBy = "user", fetch = EAGER)
	private List<Account> accounts = List.of();

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
}
