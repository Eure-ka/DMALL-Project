package Dteam_Project.basket.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dteam_Project.basket.model.BasketDAO;
import Dteam_Project.basket.model.BasketVO;
@Service
@Transactional
public class BasketServiceImpl implements BasketService{

	@Autowired
	BasketDAO basketDAO;
	
	@Override
	public int insertProduct_info(BasketVO basketVO) throws SQLException {
		int cnt=basketDAO.insertProduct_info(basketVO);
		return cnt;
	}

	@Override
	public List<BasketVO> getBasket(BasketVO basketVO) throws SQLException {
		List<BasketVO> basketList = basketDAO.getBasket(basketVO);
		return basketList;
	}

	@Override
	public int BasketRemove(Integer delBkNum[]) throws SQLException {
		System.out.println("delBkNum:"+delBkNum.length);
		int cnt = basketDAO.BasketRemove(delBkNum);
		return cnt;
	}

	@Override
	public int updateqnt(BasketVO basketVO) throws SQLException {
		
		int cnt =basketDAO.updateqnt(basketVO);
		
		return cnt;
	}

}
