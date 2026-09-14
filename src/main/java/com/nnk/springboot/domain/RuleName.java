package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "rule_name")
public class RuleName {
	
	public RuleName() {
	}
	
	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank(message = "Name is mandatory")
	@Column(name = "name")
	private String name;
	
	@NotBlank(message = "Description is mandatory")
	@Column(name = "description")
	private String description;
	
	@NotBlank(message = "Json is mandatory")
	@Column(name = "json")
	private String json;
	
	@NotBlank(message = "Template is mandatory")
	@Column(name = "template")
	private String template;
	
	@NotBlank(message = "sqlStr is mandatory")
	@Column(name = "sql_str")
	private String sqlStr;
	
	@NotBlank(message = "sqlPart is mandatory")
	@Column(name = "sql_part")
	private String sqlPart;
}
