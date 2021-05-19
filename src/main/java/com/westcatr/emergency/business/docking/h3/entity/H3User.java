package com.westcatr.emergency.business.docking.h3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ls
 * @since 2021-04-26
 */
@Data
public class H3User extends Model<H3User> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ObjectID", type = IdType.ASSIGN_ID)
    private String ObjectID;

    @TableField("user_id")
    private String userId;


    @TableField("BankName")
    private String BankName;

    @TableField("ParentID")
    private String ParentID;

    @TableField("Appellation")
    private String Appellation;

    @TableField("OfficePhone")
    private String OfficePhone;

    @TableField("Email")
    private String Email;

    @TableField("CreatedTime")
    private Date CreatedTime;

    @TableField("Birthday")
    private Date Birthday;

    @TableField("Gender")
    private Integer Gender;

    @TableField("IsSystemUser")
    private Integer IsSystemUser;

    @TableField("ParentPropertyName")
    private String ParentPropertyName;

    @TableField("DingTalkAccount")
    private String DingTalkAccount;

    @TableField("Name")
    private String Name;

    @TableField("DepartureDate")
    private Date DepartureDate;

    @TableField("ParentIndex")
    private Integer ParentIndex;

    @TableField("ParentObjectID")
    private String ParentObjectID;

    @TableField("QQ")
    private String qq;

    @TableField("NotifyType")
    private Integer NotifyType;

    @TableField("CalendarID")
    private String CalendarID;

    @TableField("JPushID")
    private String JPushID;

    @TableField("ImageUrl")
    private String ImageUrl;

    @TableField("MobileToken")
    private String MobileToken;

    @TableField("RelationUserID")
    private String RelationUserID;

    @TableField("Code")
    private String Code;

    @TableField("SID")
    private String sid;

    @TableField("IsVirtualUser")
    private Integer IsVirtualUser;

    @TableField("State")
    private Integer State;

    @TableField("BankProvince")
    private String BankProvince;

    @TableField("WeChatAccount")
    private String WeChatAccount;

    @TableField("ImageID")
    private String ImageID;

    @TableField("Visibility")
    private Integer Visibility;

    @TableField("DoLock")
    private Integer DoLock;

    @TableField("Description")
    private String Description;

    @TableField("PrivacyLevel")
    private Integer PrivacyLevel;

    @TableField("FacsimileTelephoneNumber")
    private String FacsimileTelephoneNumber;

    @TableField("PostalCode")
    private String PostalCode;

    @TableField("SortKey")
    private Integer SortKey;

    @TableField("IsAdministrator")
    private Integer IsAdministrator;

    @TableField("Extend1")
    private String Extend1;

    @TableField("PostOfficeBox")
    private String PostOfficeBox;

    @TableField("CostCenter")
    private String CostCenter;

    @TableField("ModifiedTime")
    private Date ModifiedTime;

    @TableField("RTX")
    private String rtx;

    @TableField("IsConsoleUser")
    private Integer IsConsoleUser;

    @TableField("Extend2")
    private String Extend2;

    @TableField("ManagerID")
    private String ManagerID;

    @TableField("Password")
    private String Password;

    @TableField("EmployeeRank")
    private Integer EmployeeRank;

    @TableField("IDNumber")
    private String IDNumber;

    @TableField("ServiceState")
    private Integer ServiceState;

    @TableField("DefaultLanguage")
    private String DefaultLanguage;

    @TableField("BankCity")
    private String BankCity;

    @TableField("SourceID")
    private String SourceID;

    @TableField("EmployeeNumber")
    private String EmployeeNumber;

    @TableField("BankAccount")
    private String BankAccount;

    @TableField("Mobile")
    private String Mobile;

    @TableField("Skype")
    private String Skype;

    @TableField("EntryDate")
    private Date EntryDate;

    @TableField("MobileType")
    private Integer MobileType;

    @TableField("modifier")
    private String modifier;


    @Override
    protected Serializable pkVal() {
        return this.ObjectID;
    }

}
