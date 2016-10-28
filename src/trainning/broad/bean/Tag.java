package trainning.broad.bean;

import java.io.Serializable;

/**
 * TAG モデルクラス.
 *
 * @author generated by ERMaster
 * @version $Id$
 */
public class Tag implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** tag_id. */
	private Integer tagId;

	/** tag_name. */
	private String tagName;

	/**
	 * コンストラクタ.
	 */
	public Tag() {
	}

	/**
	 * tag_id を設定します.
	 *
	 * @param tagId
	 *            tag_id
	 */
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	/**
	 * tag_id を取得します.
	 *
	 * @return tag_id
	 */
	public Integer getTagId() {
		return this.tagId;
	}

	/**
	 * tag_name を設定します.
	 *
	 * @param tagName
	 *            tag_name
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * tag_name を取得します.
	 *
	 * @return tag_name
	 */
	public String getTagName() {
		return this.tagName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tag other = (Tag) obj;
		if (tagId == null) {
			if (other.tagId != null) {
				return false;
			}
		} else if (!tagId.equals(other.tagId)) {
			return false;
		}
		return true;
	}

}