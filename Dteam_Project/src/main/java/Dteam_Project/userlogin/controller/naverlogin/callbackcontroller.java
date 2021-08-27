package Dteam_Project.userlogin.controller.naverlogin;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.InputStreamReader;

@Controller
@RequestMapping("login")
public class callbackcontroller {
	
	// 토큰 값 최종적으로 받을 변수
	static String tokken_final;
	
	@GetMapping("/naver/callback")
	public String go_callback(HttpServletRequest request) throws Exception{
		String clientId = "azV2eOudlrnOyKRyNVow";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "pOhQtuZTA4";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = URLEncoder.encode("http://localhost:8080/login/naver/callback", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    System.out.println("apiURL="+apiURL);
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      System.out.println("responseCode="+responseCode);
	      BufferedReader br;
	      if(responseCode==200) { // 정상 호출
	    	  br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        
	      }
	      
	      String inputLine;
	      
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      if(responseCode==200) {
		    	  // 원하는 토큰 값만 빼기 위해서 정제
		    	 System.out.println(res.toString());
		    	 String temp_res=res.substring(0,res.length());
		    	 String[] spl_res=temp_res.split("\"");
		    	 tokken_final=spl_res[3];
		      }
		      
		    } catch (Exception e) {
			      System.out.println(e);
			 
		    }
	    
	    return "redirect:/naver_confirm";
	}
}
