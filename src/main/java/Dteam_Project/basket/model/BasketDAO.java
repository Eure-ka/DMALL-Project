package Dteam_Project.basket.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper

public interface BasketDAO {
	
	public int insertProduct_info(BasketVO basketVO) throws SQLException;
	
	public List<BasketVO>  getBasket(BasketVO basketVO) throws SQLException;
	
	public int BasketRemove(Integer delBkNum[]) throws SQLException;

	
	
}
