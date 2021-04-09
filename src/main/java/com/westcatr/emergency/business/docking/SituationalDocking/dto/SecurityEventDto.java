package com.westcatr.emergency.business.docking.SituationalDocking.dto;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName="security_event_2021.02",type="_doc",refreshInterval="-1")
public class SecurityEventDto {
    private String id;//唯一id
    private String org_log_id;  //原始日志的ID
    private Integer event_category_id;//告警类型id
    private String event_technique_type;//告警技术分类
    private String event_description;//告警详细描述
    private String src_ip_long;//源ip地址long
    private String alarm_first_time;//原始日志首次时间
    private String dst_ip;//目的地址
    private String src_ip;//来源IP地址
    private String protocol;//协议
    private Integer event_type;//事件类型
    private String intelligence_id;//情报ID
    private String strategy_category_id;//策略分类id
    private String intelligence_type;//类型
    private Integer event_nums;//原始日志条数
    private String event_category_label;//威胁类型
    private String event_status;//告警状态
    private String severity;//等级
    private String create_time;//创建时间
    private String strategy_category_name;// 策略分类名称
    private String rule_name;//规则名称
    private Integer risk_score;//风险分值
    private String data_center;//数据中心
    private String baseline;//基线数据
    private String sop_id;//SOP
    private String event_device_type;//告警设备类型
    private String limit_time;//时效时间
    private Integer rule_id;//规则ID
    private String strategy_info_total;// #rule_id|event_model_source|event_name  字段组合
    private String policy_type;// 策略类型
    private String abnormal;//异常对象
    private String src_country;//来源IP地址所属国家
    private String strategy_category;//策略分类字段名称
    private String internal_event;//内部告警定义
    private Integer limit_unit;//时效单位
    private String event_name;//事件名
    private String dst_ip_long;//目的地址long
    private String event_model_source;//告警来源
    private String alarm_last_time;//原始日志最后时间
    private String src_province;//来源IP地址所属省份

}
