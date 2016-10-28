package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.util.List;

import trainning.broad.database.dao.GenericDAO;

public class GenericDAOImpl<T> implements GenericDAO<T> {

	protected String tableName;
	protected Connection con;

	public GenericDAOImpl(String tableName, Connection con) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.tableName = tableName;
		this.con = con;
	}

	@Override
	public void delete(T object) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("delete success");

	}

	@Override
	public T findById(T object) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<T> listAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void save(T object) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update(T object) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
