import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijiacheng
 * @Date 2021/4/25
 */
//@SpringBootTest(classes = EmergencyApplication.class)
//@RunWith(SpringRunner.class)
//@Slf4j
public class AA {
//   @Autowired
//  @Qualifier("emJdbcTemplate")
//   JdbcTemplate emJdbcTemplate;
//
//
//    @Autowired
//     @Qualifier("h3JdbcTemplate")
//    JdbcTemplate h3JdbcTemplate;
//
//@Autowired
//EntInfoService entInfoService;
//    @Autowired
//    CountryService countryService;


//@Autowired
//MailService mailService;
@Autowired

   /* @Test
    public void test(){
        String sql=" SELECT e.enterprise_name,e.mailing_address,e.belong_industry,e.enterprise_property,e.ent_social_credit_code,e.ent_legal,e.enterprise_contact,e.contact_mailbox,e.enterprise_contact_way,w.unit_name " +
                "from wp_enterprise e LEFT JOIN wp_unit_park p on e.park_id=p.id LEFT JOIN  wp_unit_administrative_area w on p.pid=w.id ";
        List<EntInfo> list2 = new LinkedList<>();
        List<Map<String, Object>> list = h3JdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            EntInfo entInfo = new EntInfo();
           entInfo.setEntName((String) map.get("enterprise_name"));
            entInfo.setEntCategory((String) map.get("enterprise_property"));
           entInfo.setEntIndustryCategory((String) map.get("belong_industry"));
            entInfo.setEntAddress((String) map.get("mailing_address"));
//            entInfo.setPostCode();
            entInfo.setSocialCreditCode((String) map.get("ent_social_credit_code"));
            entInfo.setLegalPerson((String) map.get("ent_legal"));
            entInfo.setContactName((String) map.get("enterprise_contact"));
            entInfo.setContactNumber((String) map.get("enterprise_contact_way"));
            entInfo.setContactEmail((String) map.get("contact_mailbox"));
           entInfo.setBelongCountry((String) map.get("unit_name"));
            list2.add(entInfo);

        }


//        entInfoService.saveOrUpdateBatch(list2);
//        for (EntInfo entInfo : list2) {
//            QueryWrapper<EntInfo> qw = new QueryWrapper<EntInfo>().eq("ent_name", entInfo.getEntName());
//            entInfoService.saveOrUpdate(entInfo,qw);
//        }



    }*/

   /* @Test
    public void test2(){
//        mailService.sendSimpleMail("455429918@qq.com","验证","你好");
    AtomicInteger count= new AtomicInteger();
    List<Country> list = countryService.list();
    List<EntInfo> entInfos = entInfoService.list();
    List<EntInfo> collect = entInfos.stream().filter(en -> en.getCountryId() == null).collect(Collectors.toList());
    collect.forEach(entInfo -> {
        count.getAndIncrement();
        String belongCountry = entInfo.getBelongCountry();
          Country countryQ=null;
        for (Country country : list) {
            if (belongCountry.equals(country.getUnitName())){
                countryQ=country;
                break;
            }
        }
        if (countryQ==null){
            throw  new MyRuntimeException(belongCountry+"====="+entInfo.getId());
        }
        EntInfo entInfoSave = new EntInfo();
        entInfoSave.setId(entInfo.getId());
        entInfoSave.setCountryId(countryQ.getId());
        entInfoService.updateById(entInfoSave);
    });
    System.out.println(count.get());



}*/

    @Test
    public void test(){
    List<Integer> list = new LinkedList();
    for (int i = 0; i < 10; i++) {
        list.add(i);
    }
    JSONArray objects = new JSONArray();
    objects.add(20);
    List<BigDecimal> bigDecimals = objects.toJavaList(BigDecimal.class);
    System.out.println(bigDecimals);
}


}
