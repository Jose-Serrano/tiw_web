package es.uc3m.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the items database table.
 * 
 */
@Embeddable
public class ItemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int iditems;

	@Column(name="email_due�o", insertable=false, updatable=false)
	private String emailDue�o;

	public ItemPK() {
	}
	public int getIditems() {
		return this.iditems;
	}
	public void setIditems(int iditems) {
		this.iditems = iditems;
	}
	public String getEmailDue�o() {
		return this.emailDue�o;
	}
	public void setEmailDue�o(String emailDue�o) {
		this.emailDue�o = emailDue�o;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemPK)) {
			return false;
		}
		ItemPK castOther = (ItemPK)other;
		return 
			(this.iditems == castOther.iditems)
			&& this.emailDue�o.equals(castOther.emailDue�o);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.iditems;
		hash = hash * prime + this.emailDue�o.hashCode();
		
		return hash;
	}
}