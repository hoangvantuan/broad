package trainning.broad.bean;

import java.util.List;

public class InfoPostHomepage {

	public InfoPostHomepage() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	Post post;
	User user;
	List<Tag> tags;
	List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
