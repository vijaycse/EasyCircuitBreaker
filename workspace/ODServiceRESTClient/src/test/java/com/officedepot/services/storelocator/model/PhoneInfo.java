package com.officedepot.services.storelocator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.officedepot.util.EnumHelper;
import com.officedepot.util.SafeConvert;

/**
 * Immutable Serializable transfer object for a Phone number
 */
public class PhoneInfo implements Serializable {

	private static final long serialVersionUID = 5057786244400557223L;

	private static final Logger logger = Logger.getLogger(PhoneInfo.class);

	private static final String DEFAULT_COUNTRYCODE = "1";

	private ContactPointInfo contactPointInfo;

	/**
	 * Enumeration of different types of phones that a contact can have: They
	 * represent: FAX: Fax Line GEN: General Phone Line MOBILE: Mobile Phone
	 * Line PAGER: Pager Phone Line UNDEFINED: The user did not define the type
	 * of phone this number represents
	 */
	public enum PhoneLineType {
		FAX, GEN, MOBILE, PAGER, UNDEFINED;

		public boolean equals(PhoneLineType value) {
			return EnumHelper.equalsFix(this, value);
		}

	}

	public enum PhoneType {
		HOME_FAX(0, "HFX", "HOME FAX"), HOME_PHONE(1, "HPH", "HOME PHONE"), HOME_MOBILE(
				2, "HMB", "HOME MOBILE"), HOME_PAGER(3, "HPG", "HOME PAGER"), WORK_FAX(
				4, "WFX", "WORK FAX"), WORK_PHONE(5, "WPH", "WORK PHONE"), WORK_MOBILE(
				6, "WMB", "WORK MOBILE"), WORK_PAGER(7, "WPG", "WORK PAGER"), TXT_MSG(
				8, "TXT", "TEXT MESSAGING"), UNDEFINED(-1, "UND", "Undefined");
		public boolean equals(PhoneType value) {
			return EnumHelper.equalsFix(this, value);
		}

		private int numId;

		private String strId;

		private String description;

		private PhoneType(int numId, String strId, String description) {
			this.numId = numId;
			this.strId = strId;
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public String getStrId() {
			return strId;
		}

		public int getNumId() {
			return numId;
		}

		public static PhoneType findByNumId(int numId) {

			for (PhoneType t : PhoneType.values()) {
				if (t.getNumId() == numId) {
					return t;
				}
			}

			return PhoneType.UNDEFINED;
		}

		public static PhoneType findByStrId(String strId) {
			if (strId == null) {
				return UNDEFINED;
			}
			for (PhoneType t : PhoneType.values()) {
				if (t.getStrId().equals(strId)) {
					return t;
				}
			}

			return PhoneType.UNDEFINED;
		}

		public boolean isFax() {
			return getStrId().equals("HFX") || getStrId().equals("WFX");
		}
	}

	private PhoneLineType phoneLineType;

	private String countryCode;

	private String areaCode;

	private String number;

	private String extension = "";

	private boolean optOutFlag;

	private PhoneType phoneType;

	public PhoneInfo() {
		this(new ContactPointInfo("",
				ContactPointInfo.ContactPointPurposeType.BUSINESS, true),
				PhoneLineType.UNDEFINED, "", "", "", "", PhoneType.UNDEFINED);
	}

	public PhoneInfo(ContactPointInfo contactPointInfo,
			PhoneLineType phoneLineType, String countryCode, String areaCode,
			String number, String extension, PhoneType phoneType) {
		this.contactPointInfo = contactPointInfo;
		this.phoneLineType = phoneLineType;
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.number = number;
		this.extension = extension;
		if (this.extension == null) {
			this.extension = "";
		}
		this.optOutFlag = false;
		this.phoneType = phoneType;
	}

	public static PhoneInfo getDefaultPrimaryPhone() {
		return new PhoneInfo(new ContactPointInfo("",
				ContactPointInfo.ContactPointPurposeType.BUSINESS, true),
				PhoneInfo.PhoneLineType.GEN, "", "", "", "",
				PhoneType.WORK_PHONE);
	}

	public static PhoneInfo getDefaultSecondaryPhone() {
		return new PhoneInfo(new ContactPointInfo("",
				ContactPointInfo.ContactPointPurposeType.BUSINESS, false),
				PhoneInfo.PhoneLineType.GEN, "", "", "", "",
				PhoneType.HOME_PHONE);
	}

	public static PhoneInfo getDefaultPrimaryMobile() {
		return new PhoneInfo(new ContactPointInfo("",
				ContactPointInfo.ContactPointPurposeType.BUSINESS, true),
				PhoneInfo.PhoneLineType.MOBILE, "", "", "", "",
				PhoneType.TXT_MSG);
	}

	public static PhoneInfo getDefaultPrimaryFax() {
		return new PhoneInfo(new ContactPointInfo("",
				ContactPointInfo.ContactPointPurposeType.BUSINESS, true),
				PhoneInfo.PhoneLineType.FAX, "", "", "", "", PhoneType.WORK_FAX);
	}

	public static PhoneInfo getDefaultSecondaryFax() {
		return new PhoneInfo(new ContactPointInfo("",
				ContactPointInfo.ContactPointPurposeType.BUSINESS, false),
				PhoneInfo.PhoneLineType.FAX, "", "", "", "", PhoneType.HOME_FAX);
	}

	public ContactPointInfo getContactPointInfo() {
		return contactPointInfo;
	}

	public PhoneLineType getPhoneLineType() {
		return phoneLineType;
	}

	public boolean isFax() {
		if (this.phoneLineType.equals(PhoneLineType.FAX)) {
			return true;
		}

		return false;
	}

	public boolean isOptOutFlag() {
		return optOutFlag;
	}

	/**
	 * If the phoneSeqId is blank, we need to ADD the new phone.
	 */
	public String determineActionMethod() {
		/*
		 * if (phoneSeqId.equals("")) { return "A"; } else { return "C"; }
		 */
		// Apparently we should always send a "C" to cdap, reguardless of
		// adding(A) or modifiying(C) a phone on a contact.
		return "C";

	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getNumber() {
		return number;
	}

	public String getExtension() {
		return extension;
	}

	/**
	 * the method's name is inadequate
	 * 
	 * @return first 3 digits of a ph # : 123xxxx
	 */
	public String getPrefix() {
		return (number != null && number.length() == 7) ? number
				.substring(0, 3) : "";
	}

	/**
	 * the method's name is inadequate
	 * 
	 * @return last 4 digits of a ph # : xxx1234
	 */
	public String getSuffix() {
		return (number != null && number.length() == 7) ? number
				.substring(3, 7) : "";
	}

	/**
	 * the method's name is inadequate
	 * 
	 * @return last 4 digits of a ph # : xxx1234
	 */
	public String getLocalNumber() {
		return (number != null && number.length() == 7) ? number
				.substring(3, 7) : "";
	}

	public String getPhoneNumberString() {
		if (this.getExtension() != null && this.getExtension().length() > 0) {
			return this.getNumber() + "-" + this.getExtension();
		} else {
			return this.getNumber();
		}
	}

	public boolean equals(Object other) {
		if (other == null || !this.getClass().isInstance(other)) {
			return false;
		}
		PhoneInfo otherPhoneInfo = (PhoneInfo) other;
		return (super.equals(other)
				&& this.phoneLineType == otherPhoneInfo.phoneLineType
				&& this.countryCode == otherPhoneInfo.countryCode
				&& this.areaCode == otherPhoneInfo.areaCode
				&& this.number.equalsIgnoreCase(otherPhoneInfo.number) && this.extension
					.equalsIgnoreCase(otherPhoneInfo.extension));
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(256);
		sb.append(contactPointInfo.toString());
		sb.append(" Phone Line Type: ");
		sb.append(phoneLineType);
		sb.append(" Country Code: ");
		sb.append(countryCode);
		sb.append(" Area Code: ");
		sb.append(areaCode);
		sb.append(" Number: ");
		sb.append(number);
		sb.append(" Extension: ");
		sb.append(extension);
		return sb.toString();
	}

	public String toXML() {
		StringBuffer sb = new StringBuffer(256);
		sb.append("<Phone>");
		sb.append(contactPointInfo.toXML());

		sb.append("<PhoneLineType>");
		sb.append(phoneLineType);
		sb.append("</PhoneLineType>");

		sb.append("<CountryCode>");
		sb.append(countryCode);
		sb.append("</CountryCode>");

		sb.append("<AreaCode>");
		sb.append(areaCode);
		sb.append("</AreaCode>");

		sb.append("<Number>");
		sb.append(number);
		sb.append("</Number>");

		sb.append("<Extension>");
		sb.append(extension);
		sb.append("</Extension>");

		sb.append("</Phone>");
		return sb.toString();
	}

	public void setPhoneLineType(PhoneLineType phoneLineType) {
		this.phoneLineType = phoneLineType;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setOptOutFlag(boolean optOutFlag) {
		this.optOutFlag = optOutFlag;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public boolean isPhoneType() {
		logger.debug("isPhoneType:Value=" + phoneLineType.name());
		logger.debug("isPhoneType:GEN:@=" + PhoneInfo.PhoneLineType.GEN);
		logger.debug("isPhoneType:MOBILE:@=" + PhoneInfo.PhoneLineType.MOBILE);
		logger.debug("isPhoneType:GEN:Value="
				+ PhoneInfo.PhoneLineType.GEN.name());
		logger.debug("isPhoneType:MOBILE:Value="
				+ PhoneInfo.PhoneLineType.MOBILE.name());
		logger.debug("isPhoneType:check="
				+ (phoneLineType.equals(PhoneInfo.PhoneLineType.GEN) || phoneLineType
						.equals(PhoneInfo.PhoneLineType.MOBILE)));
		logger.debug("isPhoneType:check2="
				+ ((phoneLineType.ordinal() == PhoneInfo.PhoneLineType.GEN
						.ordinal()) || (phoneLineType.ordinal() == PhoneInfo.PhoneLineType.MOBILE
						.ordinal())));

		if ((phoneLineType.ordinal() == PhoneInfo.PhoneLineType.GEN.ordinal())
				|| (phoneLineType.ordinal() == PhoneInfo.PhoneLineType.MOBILE
						.ordinal())) {
			return true;
		} else {
			return false;
		}
	}

	public static PhoneInfo createPhone(PhoneLineType type,
			String parsableNumber, String ext) {
		String cleanNumber = cleanupPhoneMember(parsableNumber);

		if (cleanNumber.length() == 10) {
			return new PhoneInfo(new ContactPointInfo("",
					ContactPointInfo.ContactPointPurposeType.UNDEFINED, true),
					type, DEFAULT_COUNTRYCODE, cleanNumber.substring(0, 3),
					cleanNumber.substring(3), ext, PhoneType.UNDEFINED);
		} else if (cleanNumber.length() == 7) {
			return new PhoneInfo(new ContactPointInfo("",
					ContactPointInfo.ContactPointPurposeType.UNDEFINED, true),
					type, DEFAULT_COUNTRYCODE, "", cleanNumber.substring(6),
					ext, PhoneType.UNDEFINED);
		} else {
			return new PhoneInfo(new ContactPointInfo("",
					ContactPointInfo.ContactPointPurposeType.UNDEFINED, true),
					type, DEFAULT_COUNTRYCODE, "", cleanNumber, ext,
					PhoneType.UNDEFINED);
		}
	}

	/**
	 * This method strips the String phone member of any white spaces,
	 * open/close parenthesis, dashes, open/close brackets and should return the
	 * phone member as a numeric string
	 * 
	 * Creation date: (06/05/00 2:36:01 PM)
	 * 
	 * @author: Adrian Prezioso
	 */
	public static String cleanupPhoneMember(String member) {
		if (member == null) {
			return "";
		}
		StringBuffer newMember = new StringBuffer();
		StringTokenizer st = new StringTokenizer(member, "()[]- ");
		while (st.hasMoreTokens()) {
			newMember.append(st.nextToken());
		}
		return newMember.toString();
	}

	public String getBaseNumber() {
		return number + extension;
	}

	public String getFullNumber() {
		return areaCode + number + extension;
	}

	private String getNumberWithHypen() {
		if (number.length() == 7) {
			return number.substring(0, 3) + "-" + number.substring(3, 7);
		} else {
			return number;
		}
	}

	public String getAreaCodeAndNumberWithHyphen() {
		if (SafeConvert.isEmpty(areaCode)
				&& SafeConvert.isEmpty(getNumberWithHypen())) {
			return "";
		} else {
			if (SafeConvert.isEmpty(areaCode)) {
				return getNumberWithHypen();
			} else {
				return areaCode + "-" + getNumberWithHypen();
			}
		}
	}

	public String getAreaCodeAndNumberWithBracket() {
		return SafeConvert.isEmpty(areaCode) ? getNumberWithHypen() : "("
				+ areaCode + ") " + getNumberWithHypen();
	}

	public String getAreaCodeAndNumber() {
		return areaCode + number;
	}

	public void setPhoneType(PhoneInfo.PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public PhoneInfo.PhoneType getPhoneType() {
		return phoneType;
	}

	public boolean hasValidExtension() {

		char temp;
		for (int i = 0; i < extension.length(); i++) {
			temp = extension.charAt(i);
			if ((temp < '\u0030') || (temp > '\u0039')) {
				return false;
			}
		}

		return true;
	}

	public boolean isValid() {

		String phoneNumber = areaCode + number;

		int length = phoneNumber.length();
		if (length != 10) {
			return false;
		}

		char temp;
		for (int i = 0; i < length; i++) {
			temp = phoneNumber.charAt(i);
			if ((temp < '\u0030') || (temp > '\u0039')) {
				return false;
			}
		}

		return true;
	}

	public boolean requiresUpdate() {
		return !getContactPointInfo().getContactPointId().equals("")
				|| !number.equals("");

	}

	public void setPhoneInfo(PhoneInfo phoneInfo) {
		this.contactPointInfo = phoneInfo.getContactPointInfo();
		this.phoneLineType = phoneInfo.getPhoneLineType();
		this.countryCode = phoneInfo.getCountryCode();
		this.areaCode = phoneInfo.getAreaCode();
		this.number = phoneInfo.getNumber();
		this.extension = phoneInfo.getExtension();
		this.optOutFlag = phoneInfo.isOptOutFlag();
		this.phoneType = phoneInfo.getPhoneType();
	}

	/**
	 * Currently used by address framework for splitting up a phone number
	 * 425-3433 becomes 425 and 3433 stored in an AarrayList
	 * 
	 * @param firstPartMaxSize
	 * @return ArrayList of phone parts
	 */
	public List<String> parsePhoneNumber(int firstPartMaxSize) {
		List<String> phoneParts = new ArrayList<String>();
		if (getNumber() == null || getNumber().equals("")) {
			phoneParts.add("");
			phoneParts.add("");
		} else {
			String[] phonePartsCheck = getNumber().split("-");
			phoneParts.add(phonePartsCheck[0].substring(0,
					Math.min(firstPartMaxSize, phonePartsCheck[0].length())));
			if (phonePartsCheck.length == 2) {
				phoneParts.add(phonePartsCheck[1]);
			} else {
				if (phonePartsCheck[0].length() > firstPartMaxSize) {
					phoneParts.add(phonePartsCheck[0]
							.substring(firstPartMaxSize));
				} else {
					phoneParts.add("");
				}
			}
		}
		return phoneParts;
	}

	/**
	 * input: 1(561)2322352 ext.2342
	 * 
	 * @param phoneString
	 * @return
	 */
	public static PhoneInfo parseNAphoneNumber(String phoneString) {
		PhoneInfo phone = new PhoneInfo();

		if (phoneString != null) {
			phoneString = phoneString.toLowerCase();
			if (phoneString.indexOf('(') != -1) {
				phone.setAreaCode(phoneString.substring(
						phoneString.indexOf("(") + 1, phoneString.indexOf(')')));
			}

			if (phoneString.indexOf(')') != -1) {
				String phoneNumber = phoneString.substring(phoneString
						.indexOf(')') + 1);
				if (phoneNumber.indexOf("ext.") != -1) {
					phoneNumber = phoneNumber.substring(0,
							phoneNumber.indexOf("ext."));
				}

				phone.setNumber(phoneNumber);
			}

			if (phoneString.indexOf("ext.") != -1) {
				phone.setExtension(phoneString.substring(phoneString
						.indexOf("ext.") + "ext.".length()));
			}
		}

		return phone;
	}

	public void setContactPointInfo(ContactPointInfo contactPointInfo) {
		this.contactPointInfo = contactPointInfo;
	}

}
