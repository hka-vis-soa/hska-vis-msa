package com.hska.eshop.categoryservice.model;

import com.hska.eshop.categoryservice.controller.CategoryController;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

@Entity
@Table(name = "categories", schema="categoryservice")
public class Category {

	private final Logger logger = LoggerFactory.getLogger(Category.class);

	private Long id;
	private String name;
	@Transient
	private InetAddress address;

	/**
	 * Don't delete hibernate empty constructor
	 */
	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Category category = (Category) o;
		if(!Objects.equals(id, category.id)) return false;
		return Objects.equals(name, category.name);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Category{" +
				"name='" + name + '\'' +
				'}';
	}
}
