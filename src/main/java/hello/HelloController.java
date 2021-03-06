package hello;

import com.time.nlp.TimeNormalizer;
import com.time.nlp.TimeUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    public ResponseEntity parse(@RequestBody String text) {
        //String orginText = "明天上午9点到11点把微信小程序的技术学一下";
        URL url = getClass().getResource("/TimeExp.m");

        TimeNormalizer timeNormalizer = new TimeNormalizer(url.toString(), true);
        timeNormalizer.parse(text);

        TimeUnit[] units = timeNormalizer.getTimeUnit();

        ArrayList<TimeAppoint> result = new ArrayList<>();

        for(TimeUnit unit : units) {
            TimeAppoint one = new TimeAppoint();
            one.setAllDay(unit.getIsAllDayTime());
            one.setTimeExpression(unit.Time_Expression);
			// fix now bug
			if (unit.getTime() != null && unit.getTime().getTime() > 0) {
				one.setTime(new Date(unit.getTime().getTime() - 8*3600*1000L));
				one.setTimeNorm(unit.Time_Norm);
			} else {
				one.setTime(new Date(System.currentTimeMillis()));
				//TODO current time string
				one.setTimeNorm("");
			}
            result.add(one);
        }


        return ResponseEntity.ok(result);
    }

}
