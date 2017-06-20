package us.codecraft.webmagic.samples;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.PriorityScheduler;
import us.codecraft.webmagic.selector.Selectable;

//import us.codecraft.webmagic.samples.CrawlerResponse;


/**
 * @author code4crafer@gmail.com
 */
public class AlexanderMcqueenGoodsProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(0);

    public static final String URL_LIST = "https://www\\.adafruit\\.com/.*";
    public static final String URL_POST = "https://www\\.adafruit\\.com/.*";
    
    
    
    @Override
    public void process(Page page) {
    	try{
//    	PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
//    	System.setOut(out);
    	
        if (page.getUrl().regex(URL_POST).match()) {

        	
        	if (page.getResultItems().get("ProductName") == null) {
                page.setSkip(true);
            }
            
            Selectable s = page.getHtml().xpath("//div[@id='productListing']//div[@class='row product-listing']//div[@class='product-listing-right col-lg-9 col-md-9 col-sm-9 col-xs-6']");
            List<String> l = s.all();
            
            List<Selectable> ls = s.nodes();
            for(int i=0;i<ls.size();i++){
            
                Selectable stemp = ls.get(i);
                Selectable s1 = stemp.xpath("//div[@class='product-listing-text-wrapper']//h1//a[@class='ec_click_product']/tidyText()");
                Selectable s2 = stemp.xpath("////div//div[@class='stock']//span[@itemprop='availability']/tidyText()");
                
                if(ifCorrectFormat(s2.toString())){

                	System.out.println("l1  "+s1.toString());
                    //out.println(s1.toString());

                    System.out.println("l2  "+s2.toString());
                    //out.println(s2.toString());
                    
                }
                
                
            }
            
          
//            
        } else {
            page.addTargetRequests(page.getHtml().links().regex(URL_POST).all(), 1000);
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all(), 1);
        }
        
//        out.close();
    	}catch(Exception e){
    		
    	}
    	
    }
    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args) {
    
        Spider.create(new AlexanderMcqueenGoodsProcessor()).setScheduler(new PriorityScheduler())
        .addUrl("https://www.adafruit.com").thread(5).run();
        
        
        
    }
    
    public boolean ifCorrectFormat(String s){
        if(s == null|| s.equals(""))
            return false;
        Pattern p = Pattern.compile("\\d+\\sIN\\sSTOCK");
        Matcher m = p.matcher(s);
        
        return m.find();
        
    }
//	public CrawlerResponse getCrawlerResponse() {
//		// TODO Auto-generated method stub
//		
//		
//		
//		
//		return null;
//	}
    
    
    
    
    
}