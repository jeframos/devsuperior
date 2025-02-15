package com.devsuperior.dsevent.entities;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_block")
public class Block {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant beginTime;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant endTime;
	
	@ManyToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;
	
	public Block() {}

	public Block(Integer id, Instant beginTime, Instant endTime, Activity activity) {
		this.id = id;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.activity = activity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getbeginTime() {
		return beginTime;
	}

	public void setbeginTime(Instant beginTime) {
		this.beginTime = beginTime;
	}

	public Instant getendTime() {
		return endTime;
	}

	public void setendTime(Instant endTime) {
		this.endTime = endTime;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		return Objects.equals(id, other.id);
	}

}
