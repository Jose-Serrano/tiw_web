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

	@Column(name="email_dueño", insertable=false, updatable=false)
	private String emailDueño;

	public ItemPK() {
	}
	public int getIditems() {
		return this.iditems;
	}
	public void setIditems(int iditems) {
		this.iditems = iditems;
	}
	public String getEmailDueño() {
		return this.emailDueño;
	}
	public void setEmailDueño(String emailDueño) {
		this.emailDueño = emailDueño;
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
			&& this.emailDueño.equals(castOther.emailDueño);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.iditems;
		hash = hash * prime + this.emailDueño.hashCode();
		
		return hash;
	}
}