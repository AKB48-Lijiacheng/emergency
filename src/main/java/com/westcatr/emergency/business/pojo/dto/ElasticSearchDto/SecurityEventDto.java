package com.westcatr.emergency.business.pojo.dto.ElasticSearchDto;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName="security_event_2021.02",type="_doc",refreshInterval="-1")
public class SecurityEventDto {
    private String id;
    private String org_log_id;
    private String event_category_id;
    private String event_technique_type;
    private String event_description;
    private String src_ip_long;
    private String alarm_first_time;
    private String dst_ip;
    private String src_ip;
    private String protocol;
    private String event_type;
    private String intelligence_id;
    private String strategy_category_id;
    private String intelligence_type;
    private String event_nums;
    private String event_category_label;
    private String event_status;
    private String severity;
    private String create_time;
    private String strategy_category_name;
    private String rule_name;
    private String risk_score;
    private String data_center;
    private String baseline;
    private String sop_id;
    private String event_device_type;
    private String limit_time;
    private String rule_id;
    private String strategy_info_total;
    private String policy_type;
    private String abnormal;
    private String src_country;
    private String strategy_category;
    private String internal_event;
    private String limit_unit;
    private String event_name;
    private String dst_ip_long;
    private String event_model_source;
    private String alarm_last_time;
    private String src_province;





}
