package inter;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public interface Command {
	//���� �޼ҵ带 ����
	//request, response ������ �� �� �ֵ���
	
	public String execute(HttpServletRequest request) throws ServerException, IOException;
}
