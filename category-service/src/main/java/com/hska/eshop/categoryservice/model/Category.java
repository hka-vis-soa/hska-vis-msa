package com.hska.eshop.categoryservice.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "categories", schema="categoryservice")
public class Category {
	/**
	 * Don't delete hibernate empty constructor
	 */
	public Category() {
	}

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}

	private Long id;
	private String name;
	private String description;

	public void setId(Long id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Category category = (Category) o;
		if(!Objects.equals(id, category.id)) return false;
		if(!Objects.equals(name, category.name)) return false;
		return Objects.equals(description, category.description);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
