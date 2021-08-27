package Dteam_Project.basket.service;

import java.sql.SQLException;
import java.util.List;

import Dteam_Project.basket.model.BasketVO;

public interface BasketService {
	
	public int insertProduct_info(BasketVO basketVO) throws SQLException;
	
	public List<BasketVO>  getBasket(BasketVO basketVO) throws SQLException;

	public int BasketRemove(Integer delBkNum[]) throws SQLException;




}
