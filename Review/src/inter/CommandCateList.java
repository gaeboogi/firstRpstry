package inter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.CateVO;
import review.model.FoodVO;
import review.model.ReviewDAO;
import inter.CommandException;

public class CommandCateList implements Command{
	private String next;

	public CommandCateList( String _next ){
		next = _next;
	}
	
	public String execute( HttpServletRequest request) {
		try {
		ReviewDAO dao = ReviewDAO.getInstance();
		List<CateVO> list = dao.getCateList();
		request.setAttribute("catelist", list);
		
		int cateNo = 1;
		String cate = request.getParameter("cate");
		if(cate != null) cateNo = Integer.parseInt(cate);
		List<FoodVO> flist = dao.getFoodList(cateNo);
		request.setAttribute("foodlist", flist);
		System.out.println(flist);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("CommandCateList 예외발생:"+e.getMessage());
		}
		return next;
	}

}
