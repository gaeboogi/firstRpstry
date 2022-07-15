package inter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.CateVO;
import review.model.FoodVO;
import review.model.ReviewDAO;
import inter.CommandException;

public class CommandFoodList implements Command{
	private String next;

	public CommandFoodList( String _next ){
		next = _next;
	}
	
	@Override
	public String execute( HttpServletRequest request) {
		try {
		ReviewDAO dao = ReviewDAO.getInstance();
				
		int cateNo = 1;
		List<FoodVO> list = dao.getFoodList(cateNo);
		request.setAttribute("foodlist", list);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("CommandCateList 예외발생:"+e.getMessage());
		}
		return next;
	}

}
