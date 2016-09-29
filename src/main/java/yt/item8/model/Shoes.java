package yt.item8.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity(
	name = "shoes")
@Indexed
public class Shoes extends BaseObject implements Serializable, EntityInterface {

	private static final long serialVersionUID = 1L;

	private Integer shoesId;

	private String shoesName;

	private String series;

	private String category;

	private int price;

	private Brand brand;

	private int brandId;

	public Shoes() {

	}

	public Shoes(String shoesName) {
		setShoesName(shoesName);
	}

	@Id
	@GeneratedValue(
		strategy = GenerationType.AUTO)
	public Integer getShoesId() {
		return shoesId;
	}

	public Shoes setShoesId(int shoesId) {
		this.shoesId = shoesId;
		return this;
	}

	@Column
	@Field
	public String getShoesName() {
		return shoesName;
	}

	public Shoes setShoesName(String shoesName) {
		this.shoesName = shoesName;
		return this;
	}

	@Column
	@Field
	public String getSeries() {
		return series;
	}

	public Shoes setSeries(String series) {
		this.series = series;
		return this;
	}

	@Column
	@Field
	public String getCategory() {
		return category;
	}

	public Shoes setCategory(String category) {
		this.category = category;
		return this;
	}

	@Column
	@Field
	public int getPrice() {
		return price;
	}

	public Shoes setPrice(int price) {
		this.price = price;
		return this;
	}

	@ManyToOne(
		fetch = FetchType.LAZY,
		cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(
		name = "brandId",
		nullable = false)
	public Brand getBrand() {
		return brand;
	}

	public Shoes setBrand(Brand brand) {
		this.brand = brand;
		return this;
	}

	public Shoes setBrandById(int id) {
		this.brand = new Brand(id);
		return this;
	}

	@Transient
	@Override
	public int getId() {
		return this.shoesId;
	}

	@Override
	public String toString() {
		return shoesId + " " + shoesName + " " + series + " " + price + " " + brand;

	}

	@Override
	public void setForeignClassNull() {
		setBrand(null);

	}

//	@Field
	@Transient
	public int getBrandId() {
		return brandId;
	}

	public Shoes setBrandId(int brandId) {
		this.brandId = brandId;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		Shoes obj = null;
		try {
			obj = this.getClass().cast(o);
		} catch (Exception e) {
			return false;
		}

		if (obj.getShoesId().equals(this.shoesId))
			return true;

		return false;
	}

	@Override
	public int hashCode() {
		return this.shoesName.hashCode() + this.shoesId.hashCode() + 4 * 50;
	}
}
