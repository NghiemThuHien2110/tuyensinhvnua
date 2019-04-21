package vn.edu.vnua.dse.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.vnua.dse.common.CommonConst;
import vn.edu.vnua.dse.common.CommonUtils;
import vn.edu.vnua.dse.dao.PostDAO;
import vn.edu.vnua.dse.entity.Post;
import vn.edu.vnua.dse.service.Impl.CategoriesServiceImpl;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class PostDaoImpl implements PostDAO {

	private static final Logger logger = Logger.getLogger(CategoriesServiceImpl.class);

	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc) Tao moi bai viet
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#createdPost(vn.edu.vnua.dse.entity.Post)
	 */
	@Override
	public void createdPost(Post post) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sql = CommonUtils.readSqlFile(CommonConst.SqlFileName.CREATE_POST);
			Query query = session.createSQLQuery(sql);
			query.setParameter("shortContent", post.getShortContent());
			query.setParameter("title", post.getTitle());
			query.setParameter("url", post.getUrl());
			query.setParameter("avatarPost", post.getAvatarPost());
			query.setParameter("content", post.getContent());
			query.setParameter("categoriesId", post.getCategories().getId());
			query.setParameter("editor", post.getEditor());
			query.setParameter("author", post.getAuthor());
			query.setParameter("publishedDate", post.getPublishedDate());
			query.executeUpdate();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc) Sua bai viet
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#updatePost(vn.edu.vnua.dse.entity.Post)
	 */
	@Override
	public void updatePost(Post post) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sql = CommonUtils.readSqlFile(CommonConst.SqlFileName.UPDATE_POST);
			Query query = session.createSQLQuery(sql);
			query.setParameter("shortContent", post.getShortContent());
			query.setParameter("title", post.getTitle());
			query.setParameter("url", post.getUrl());
			query.setParameter("avatarPost", post.getAvatarPost());
			query.setParameter("content", post.getContent());
			query.setParameter("categoriesId", post.getCategories().getId());
			query.setParameter("publishedDate", post.getPublishedDate());
			query.setParameter("updatedUser", post.getUpdatedDate());
			query.setParameter("id", post.getId());
			query.executeUpdate();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc) Xoa bai viet
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#deletePost(int)
	 */
	@Override
	public void deletePost(int id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sql = CommonUtils.readSqlFile(CommonConst.SqlFileName.DELETE_POST_BY_ID);
			Query query = session.createSQLQuery(sql);
			query.setParameter("id", id);
			query.executeUpdate();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc) Lay danh sach tat ca bai viet
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#getAllPost()
	 */
	@Override
	public List<Post> getAllPost() {
		List<Post> listResult = new ArrayList<Post>();

		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sql = CommonUtils.readSqlFile(CommonConst.SqlFileName.GET_ALL_POST);
			Query query = session.createSQLQuery(sql).addEntity(Post.class);
			listResult = query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listResult;
	}

	/*
	 * (non-Javadoc) Lay danh sach 10 bai viet
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#getLimitedPost(int)
	 */
	@Override
	public List<Post> getLimitedPost(int startIndex) {
		List<Post> listResultLimit = new ArrayList<Post>();

		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Post");
			query.setFirstResult(startIndex);
			query.setMaxResults(10);

			listResultLimit = (List<Post>) query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listResultLimit;
	}

	/*
	 * (non-Javadoc) Lay danh sach bai viet da xuat ban
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#getPostPublished()
	 */
	@Override
	public List<Post> getPostPublished() {
		List<Post> listResult = new ArrayList<Post>();

		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sql = CommonUtils.readSqlFile(CommonConst.SqlFileName.GET_ALL_PUBLISHED_POST);
			Query query = session.createSQLQuery(sql).addEntity(Post.class);
			listResult = query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listResult;
	}

	/*
	 * (non-Javadoc) Lay danh sach bai viet dang cho duyet
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#getPendingPost()
	 */
	@Override
	public List<Post> getPendingPost() {
		List<Post> listResult = new ArrayList<Post>();

		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sql = CommonUtils.readSqlFile(CommonConst.SqlFileName.GET_ALL_PENDING_POST);
			Query query = session.createSQLQuery(sql).addEntity(Post.class);
			listResult = query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listResult;
	}

	/*
	 * (non-Javadoc) Lay danh sach bai viet da duyet
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#getApprovedPost()
	 */
	@Override
	public List<Post> getApprovedPost() {
		List<Post> listResult = new ArrayList<Post>();

		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sql = CommonUtils.readSqlFile(CommonConst.SqlFileName.GET_ALL_APPROVED_POST);
			Query query = session.createSQLQuery(sql).addEntity(Post.class);
			listResult = query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listResult;
	}

	/*
	 * (non-Javadoc) Lay danh sach bai viet da xuat ban theo chuyen muc
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#getPostPublishedByCategoriesId(int)
	 */
	@Override
	public List<Post> getPostPublishedByCategoriesId(int categoriesId) {
		List<Post> listResult = new ArrayList<Post>();

		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sql = CommonUtils.readSqlFile(CommonConst.SqlFileName.GET_ALL_PUBLISHED_POST_BY_CATEGORIES_ID);
			Query query = session.createSQLQuery(sql).addEntity(Post.class);
			query.setParameter("categoriesId", categoriesId);
			listResult = query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listResult;
	}

	/*
	 * (non-Javadoc) Lay danh sach 10 bai viet da xuat ban theo chuyen muc
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#getPostPublishedByCategoriesIdLimit(int,
	 * int)
	 */
	@Override
	public List<Post> getPostPublishedByCategoriesIdLimit(int categoriesId, int startIndex) {
		List<Post> listResultLimit = new ArrayList<Post>();

		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Post where categoriesId = :categoriesId and status = 2");
			query.setFirstResult(startIndex);
			query.setMaxResults(10);

			listResultLimit = (List<Post>) query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listResultLimit;
	}

	/*
	 * (non-Javadoc) Lay danh sach 10 bai viet da xuat ban
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#getLimitPostPublished(int)
	 */
	@Override
	public List<Post> getLimitPostPublished(int startIndex) {
		List<Post> listResult = new ArrayList<Post>();

		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session
					.createQuery("FROM Post WHERE status = 1 and publishedDate >= now() ORDER BY publishedDate desc");
			query.setFirstResult(startIndex);
			query.setMaxResults(10);
			listResult = (List<Post>) query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listResult;
	}

	/*
	 * (non-Javadoc) Lay danh sach 10 bai viet dang cho duyet
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#getLimitPendingPost(int)
	 */
	@Override
	public List<Post> getLimitPendingPost(int startIndex) {
		List<Post> listResult = new ArrayList<Post>();

		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Post WHERE status = 0 ORDER BY createdDate desc");
			query.setFirstResult(startIndex);
			query.setMaxResults(10);
			listResult = (List<Post>) query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listResult;
	}

	/*
	 * (non-Javadoc) Lay danh sach 10 bai viet da duyet
	 * 
	 * @see vn.edu.vnua.dse.dao.PostDAO#getLimitApprovedPost(int)
	 */
	@Override
	public List<Post> getLimitApprovedPost(int startIndex) {
		List<Post> listResult = new ArrayList<Post>();

		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Post WHERE status = 1 and publishedDate < now()");
			query.setFirstResult(startIndex);
			query.setMaxResults(10);
			listResult = (List<Post>) query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listResult;
	}

}