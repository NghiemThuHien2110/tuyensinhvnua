package vn.edu.vnua.dse.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import vn.edu.vnua.dse.dao.CategoriesDAO;
import vn.edu.vnua.dse.entity.Categories;
import vn.edu.vnua.dse.service.CategoriesService;

@Service("categoriesService")
public class CategoriesServiceImpl implements CategoriesService {

	private static final Logger logger = Logger.getLogger(CategoriesServiceImpl.class);

	@Autowired
	private CategoriesDAO categoriesDao;

	/**
	 * Phuong thuc lay ra tat cac chuyen muc
	 */
	@Override
	public List<Categories> getAllCategories(int startIndex) {
		List<Categories> listCategories = new ArrayList<Categories>();
		try {
			logger.debug("GET ALL Categories Start");
			listCategories = categoriesDao.getAllCategorires(startIndex);
			logger.debug("GET ALL Categories End");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return listCategories;
	}

	/**
	 * Phuong thuc service tra ve tat ca chuyen muc dang duoc kich hoat
	 * 
	 * @see vn.edu.vnua.dse.service.CategoriesService#getAllCategories()
	 */
	@Override
	public void addCategories(Categories categories) {
		try {
			logger.debug("Add Categories Start");
			categoriesDao.addCategories(categories);
			logger.debug("Add Categories End");
		} catch (Exception e) {
			logger.error(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	/*
	 * (non-Javadoc) Update mot chuyen muc vao db
	 * 
	 * @see
	 * vn.edu.vnua.dse.service.CategoriesService#updateCategories(vn.edu.vnua.dse.
	 * entity.Categories)
	 */
	@Override
	public void updateCategories(Categories categories) {
		try {
			logger.debug("Update Categories Start");
			categoriesDao.updateCategories(categories);
			logger.debug("Update Categories End");
		} catch (Exception e) {
			logger.error(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	/*
	 * (non-Javadoc) Xóa 1 chuyên mục trong bảng categories
	 * 
	 * @see
	 * vn.edu.vnua.dse.service.CategoriesService#deleteCategories(vn.edu.vnua.dse.
	 * entity.Categories)
	 */
	@Override
	public void deleteCategories(int categoriesId) {
		try {
			logger.debug("Delete Categories Start");
			categoriesDao.deleteCategories(categoriesId);
			logger.debug("Delete Categories End");
		} catch (Exception e) {
			logger.error(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	/*
	 * (non-Javadoc) Cap nhat trang thai cua categories
	 * 
	 * @see vn.edu.vnua.dse.service.CategoriesService#changeStatusCategories(int,
	 * boolean)
	 */
	@Override
	public void changeStatusCategories(int categoriesId, boolean status, String updateByUser) {
		try {
			logger.debug("Update Categories Start");
			categoriesDao.changeStatusCategories(categoriesId, status, updateByUser);
			logger.debug("Update Categories End");
		} catch (Exception e) {
			logger.error(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Override
	public List<Categories> getAllCategoriesIsActive() {
		List<Categories> listCategories = new ArrayList<Categories>();
		try {
			logger.debug("getListCategoriesIsActive Start");
			listCategories = categoriesDao.getListCategoriesIsActive();
			logger.debug("getListCategoriesIsActive End");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return listCategories;
	}

	/*
	 * (non-Javadoc) Phuong thuc lay tat ca chuyen muc theo id
	 * 
	 * @see vn.edu.vnua.dse.service.CategoriesService#getCategoriresById(int)
	 */
	@Override
	public Categories getCategoriresById(int id) {
		Categories categories = new Categories();
		try {
			logger.debug("getListCategoriesIsActive Start");
			categories = categoriesDao.getCategoriresById(id);
			logger.debug("getListCategoriesIsActive End");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return categories;
	}
}
