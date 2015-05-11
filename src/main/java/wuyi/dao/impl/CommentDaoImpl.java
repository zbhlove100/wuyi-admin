package wuyi.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.CommentDao;
import wuyi.model.po.Comment;
@Repository
@Transactional
public class CommentDaoImpl extends MyGenericDaoHibernate<Comment, Long> implements CommentDao{

	public CommentDaoImpl() {
		super(Comment.class);
		// TODO Auto-generated constructor stub
	}

}
