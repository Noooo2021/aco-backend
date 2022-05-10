package tacos;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "tacos")
public class Taco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	@ManyToMany(targetEntity = Ingredient.class)
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<String> ingredients;

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
	
}