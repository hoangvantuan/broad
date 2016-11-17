package trainning.broad.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * COMMENT モデルクラス.
 *
 * @author generated by ERMaster
 * @version $Id$
 */
public class Comment implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer commentId;

	private String content;

	private Timestamp createAt;

	private Timestamp updateAt;

	private Integer postId;
	private Integer userId;

	public Comment() {
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public Integer getPostId() {
		return this.postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}

