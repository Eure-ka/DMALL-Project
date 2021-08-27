package Dteam_Project.userlogin.controller.naverlogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ApiExamMemberProfile{
	
    	
    	@GetMapping("naver_confirm")
    	public String confirm_naver(HttpServletRequest request, HttpSession session,Model model) throws Exception{
    		
    		callbackcontroller cb =new callbackcontroller();

    		String temp_tokken=cb.tokken_final;
        	
        	
            String token = temp_tokken;
            String header = "Bearer " + token; // Bearer 다음에 공백 추가


            String apiURL = "https://openapi.naver.com/v1/nid/me";


            Map<String, String> requestHeaders = new HashMap();
            requestHeaders.put("Authorization", header);
            String responseBody = get(apiURL,requestHeaders);

            
            // 네이버 사용자 이름을 가져 오기 위해서 이름만 정제 과정
            String [] array=responseBody.split(",");
            
            String [] ar2=array[5].split("\"");
            
            String uni_name=ar2[3];
            
            
            System.out.println(uni_name);
            
            // 미리 만들어둔 decode 함수를 이용하여 디코딩
            String final_name=decode(uni_name);
            
            session.setAttribute("username", final_name);
            
            return "redirect:/main/home";
    	}
    	

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    
    // 네이버에서 가져온 프로필이 유니코드로 나오기 때문에 디코드 하기위해서
    private static String decode(String unicode)throws Exception {
    	StringBuffer str = new StringBuffer();
    	
    	char ch = 0;
    	for( int i= unicode.indexOf("\\u"); i > -1; i = unicode.indexOf("\\u") ){
    	ch = (char)Integer.parseInt( unicode.substring( i + 2, i + 6 ) ,16);
    	str.append( unicode.substring(0, i) );
    	str.append( String.valueOf(ch) );
    	unicode = unicode.substring(i + 6);
    	}
    	str.append( unicode );
    	
    	return str.toString();
    	}
    
}


	



