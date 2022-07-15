package inter;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public interface Command {
	//실행 메소드를 정의
	//request, response 가지고 갈 수 있도록
	
	public String execute(HttpServletRequest request) throws ServerException, IOException;
}
