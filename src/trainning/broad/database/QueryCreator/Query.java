package trainning.broad.database.QueryCreator;

public class Query {

	private String query;

	public Query() {
	}

	public Query(String query) {

		this.query = query;
	}

	public String getQuery() {

		return this.query;
	}

	public void setQuery(String query) {

		this.query = query;
	}

	public Query select(String attr) {

		this.query = this.query.concat(" SELECT ").concat(attr);
		return this;
	}

	public Query from(String attr) {

		this.query = this.query.concat(" FROM ").concat(attr);
		return this;
	}
}
