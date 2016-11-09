package trainning.broad.bean;

import java.util.List;

public class PostUserTag {

	public PostUserTag() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	Post post;
	User user;
	List<Tag> tags;

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
