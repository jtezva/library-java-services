package mx.cpuatx.library.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbook")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String author;
	private String categoryid;
	private String editorid;
	private String statusid;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getCategoryid() {
		return categoryid;
	}
	
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	
	public String getEditorid() {
		return editorid;
	}
	
	public void setEditorid(String editorid) {
		this.editorid = editorid;
	}
	
	public String getStatusid() {
		return statusid;
	}
	
	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}
	
	@Override
	public String toString() {
		return this.name + ", " + this.author;
	}
}