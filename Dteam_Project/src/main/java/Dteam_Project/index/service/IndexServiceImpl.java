package Dteam_Project.index.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dteam_Project.index.model.IndexDAO;
import Dteam_Project.index.model.PageObj;
import Dteam_Project.product.model.ProductVO;

@Service
@Transactional
public class IndexServiceImpl implements IndexService{
	
	@Autowired
	IndexDAO indexdao;
	
	@Override
	public List<ProductVO> getProductlist() throws SQLException {
		
		List<ProductVO> dbproductlist=indexdao.getProductlist();
		return dbproductlist;
	}
	
	@Override
	public ProductVO getProductInfo(Integer product_code) throws SQLException {
		
		ProductVO productVo =indexdao.getProductInfo(product_code);
		return productVo;
	}

	@Override
	public List<ProductVO> getBoardListPaging(PageObj pageObj) throws SQLException {
		
		List<ProductVO> productlist = indexdao.getBoardListPaging(pageObj);
		
		return productlist;
	}

	@Override
	public int getCount() throws SQLException {
		return indexdao.getCount();
	}

	@Override
	public List<ProductVO> cate_getBoardListPaging(PageObj pageObj) throws SQLException {
		
		System.out.println("서비스에서 페이지 객체:"+pageObj);
		
		List<ProductVO> productlist = indexdao.cate_getBoardListPaging(pageObj);
		
		return productlist;
	}

	@Override
	public int cate_Count(Integer category_id) throws SQLException {
		int cnt = indexdao.cate_Count(category_id);
		return cnt;
	}

	@Override
	public List<ProductVO> searchname(ProductVO productVO) throws SQLException {
		
		 List<ProductVO> productlist=indexdao.searchname(productVO);
		 
		return productlist;
	}
	
	
}
