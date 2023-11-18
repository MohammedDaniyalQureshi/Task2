import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class Task2 {

	public static void main(String[] args) {
		sendXML("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>\n<employee id=\"1\">\n</employee>\n</root>");
		sendXML("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>\n<employee id=\"1\">\n</employee>\n");
		sendXML("<message></message>");
		sendXML("<message>");
		sendXML("Hi");
	}
	
	public static void sendXML(Object obj) {
		String data = String.valueOf(obj);
		try{
			String url = "http://localhost:2000/xml";
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty( "Content-Type", "application/xml" );

			OutputStream os = conn.getOutputStream();
			os.write(data.getBytes("utf-8"));
			int code = conn.getResponseCode();
			if(code == 200) {
				System.out.println("Request Successful");
			}else if(code == 400){
				System.out.println("Bad Request: Check if XML valid");
			}else{
				System.out.println("Something Went Wrong");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	  }
}