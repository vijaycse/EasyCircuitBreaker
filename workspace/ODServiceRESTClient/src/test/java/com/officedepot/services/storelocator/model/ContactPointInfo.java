package com.officedepot.services.storelocator.model;

import java.io.Serializable;

import com.officedepot.util.EnumHelper;

/*
 * Immutable Serializable transfer object for a Contact Points
 * Contact Points include Phone, Email and Web
 */
public class ContactPointInfo implements Serializable {

	private static final long serialVersionUID = 2256946047595548508L;

	public ContactPointInfo() {
	}

	private String contactPointId;

	/**
	 * Enumeration of different types of contact point purposes that a contact
	 * can have: They represent: BUSINESS: Business contact point EMERGENCY:
	 * Emergency contact point HOME_BUSINESS: Home Business contact point
	 * PERSONAL: Personal contact point HOMEPAGE: Home page of site contact
	 * point SUPPORTURL: Support URL contact point UNDEFINED: The user did not
	 * define the contact point purpose type this represents
	 */
	public enum ContactPointPurposeType {
		BUSINESS, EMERGENCY, HOME_BUSINESS, PERSONAL, HOMEPAGE, SUPPORTURL, UNDEFINED;
		public boolean equals(ContactPointPurposeType value) {
			return EnumHelper.equalsFix(this, value);
		}
	}

	private ContactPointPurposeType contactPointPurposeType;

	private boolean primary;

	public ContactPointInfo(String contactPointId,
			ContactPointPurposeType contactPointPurposeType, boolean primary) {
		this.contactPointId = contactPointId;
		this.contactPointPurposeType = contactPointPurposeType;
		this.primary = primary;
	}

	public String getContactPointId() {
		return contactPointId;
	}

	public void setContactPointId(String contactPointId) {
		this.contactPointId = contactPointId;
	}

	public ContactPointPurposeType getContactPointPurposeType() {
		return contactPointPurposeType;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public boolean equals(Object other) {
		if (other == null || !this.getClass().isInstance(other)) {
			return false;
		}
		ContactPointInfo otherContactPointInfo = (ContactPointInfo) other;
		if (!this.contactPointId.equals(otherContactPointInfo.contactPointId)) {
			return false;
		}
		return true;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(256);
		sb.append(" Contact Point Id: ");
		sb.append(contactPointId);
		sb.append(" Contact Point Purpose Type: ");
		sb.append(contactPointPurposeType);
		sb.append(" Primary: ");
		sb.append(isPrimary());
		return sb.toString();
	}

	public String toXML() {
		StringBuffer sb = new StringBuffer(256);
		sb.append("<ContactPointId>");
		sb.append(contactPointId);
		sb.append("</ContactPointId>");

		sb.append("<ContactPointPurposeType>");
		sb.append(contactPointPurposeType);
		sb.append("</ContactPointPurposeType>");

		sb.append("<Primary>");
		sb.append(primary);
		sb.append("</Primary>");

		return sb.toString();
	}
}
